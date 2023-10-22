package io.github.jolkert.stayawhile.mixin;

import io.github.jolkert.stayawhile.access.ItemEntityInterface;
import io.github.jolkert.stayawhile.data.DropType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
	@Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("RETURN"), cancellable = true)
	public void setDropType(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir)
	{
		ItemEntity itemEntity = cir.getReturnValue();
		if (itemEntity != null)
		{
			((ItemEntityInterface) itemEntity).stayAWhile$setDropType(DropType.PLAYER_DROPPED);
			if (DropType.PLAYER_DROPPED.getMaximumAge(itemEntity.getWorld()) < 0)
				((ItemEntityAccessor) itemEntity).setItemAge(Short.MIN_VALUE);
		}

		cir.setReturnValue(itemEntity);
	}
}
