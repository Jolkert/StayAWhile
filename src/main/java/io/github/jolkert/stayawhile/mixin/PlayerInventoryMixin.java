package io.github.jolkert.stayawhile.mixin;

import io.github.jolkert.stayawhile.access.ItemEntityAccessor;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin
{

	@Redirect(method = "dropAll",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;"))
	public ItemEntity setAgeToMinimumIfApplicable(PlayerEntity player, ItemStack itemStack, boolean throwRandomly, boolean retainOwnership)
	{
		ItemEntity itemEntity = player.dropItem(itemStack, throwRandomly, retainOwnership);
		if (itemEntity != null)
			((ItemEntityAccessor) itemEntity).setIsDeathDrop(true);

		return itemEntity;
	}
}
