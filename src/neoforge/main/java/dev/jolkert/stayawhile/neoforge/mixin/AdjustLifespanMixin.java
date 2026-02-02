package dev.jolkert.stayawhile.neoforge.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.jolkert.stayawhile.DropType;
import net.minecraft.nbt.CompoundTag;
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
		if (newValue == 6000)
		{
			self.lifespan = DropType.from(self).maxAge(self.level());
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
		if (this.thrower != null)
		{
			this.lifespan = DropType.PLAYER_THROWN.maxAge(((Entity) (Object) this).level());
		}
	}
}
