package dev.jolkert.stayawhile.neoforge.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.jolkert.stayawhile.DropType;
import dev.jolkert.stayawhile.StayAWhile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.UUID;

@Mixin(ItemEntity.class)
public class AdjustLifespanMixin
{
	@Shadow
	public int lifespan;

	@Shadow
	@Nullable
	private UUID thrower;

	@WrapOperation(
			method = "<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;DDD)V",
			at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/item/ItemEntity;lifespan:I")
	)
	void setProperLifespan(ItemEntity self, int newValue, Operation<Integer> original)
	{
		// dont mess with other mods' stuff if it's already been changed -morgan 2026-02-01
		if (newValue == 6000 && self.level() instanceof ServerLevel world)
		{
			self.lifespan = StayAWhile.maxItemAge(DropType.from(self), world);
		}
	}

	@Inject(method = "setThrower", at = @At("TAIL"))
	void setThrownLifespanOnSet(Entity thrower, CallbackInfo ci)
	{
		stayawhile$setThrownLifespan();
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	void setThrownLifespanOnRead(CompoundTag compound, CallbackInfo ci)
	{
		stayawhile$setThrownLifespan();
	}

	@Unique
	void stayawhile$setThrownLifespan()
	{
		if (this.thrower != null && ((Entity) (Object) this).level() instanceof ServerLevel world)
		{
			this.lifespan = StayAWhile.maxItemAge(DropType.PLAYER_THROWN, world);
		}
	}
}
