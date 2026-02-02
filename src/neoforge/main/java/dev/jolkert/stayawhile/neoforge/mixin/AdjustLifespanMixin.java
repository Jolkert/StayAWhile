package dev.jolkert.stayawhile.neoforge.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.jolkert.stayawhile.DropType;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEntity.class)
public class AdjustLifespanMixin
{
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
}
