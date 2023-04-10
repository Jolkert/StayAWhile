package io.github.jolkert.stayawhile.mixin;

import io.github.jolkert.stayawhile.StayAWhile;
import io.github.jolkert.stayawhile.access.ItemEntityInterface;
import io.github.jolkert.stayawhile.data.DropType;
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
	public ItemEntity markAsDeathDrop(PlayerEntity player, ItemStack itemStack, boolean throwRandomly, boolean retainOwnership)
	{
		ItemEntity itemEntity = player.dropItem(itemStack, throwRandomly, retainOwnership);
		if (itemEntity == null)
			return null;

		((ItemEntityInterface) itemEntity).setDropType(DropType.PLAYER_DEATH_DROP);
		// we have to explicitly set it to zero in the case that itemDespawnAge is negative and the constructor set it to min -morgan 2023-04-10
		short age = itemEntity.getWorld().getGameRules().getInt(StayAWhile.MAX_PLAYER_DEATH_ITEM_AGE) < 0 ? Short.MIN_VALUE : 0;
		((ItemEntityAccessor) itemEntity).setItemAge(age);

		return itemEntity;
	}
}
