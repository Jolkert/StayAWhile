package dev.jolkert.stayawhile.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import dev.jolkert.stayawhile.StayAWhile;
import dev.jolkert.stayawhile.duck.ItemEntityDuck;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Inventory.class)
public class AddDeathDropTagMixin
{
	@WrapOperation(
		method = "dropAll",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/player/Player;drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;"
		)
	)
	ItemEntity addDeathDropTag(
		Player instance,
		ItemStack droppedItem,
		boolean dropAround,
		boolean includeThrowerName,
		Operation<ItemEntity> original
	)
	{
		ItemEntity entity = original.call(instance, droppedItem, dropAround, includeThrowerName);
		((ItemEntityDuck) entity).stayawhile$setDeathDrop(true);
		StayAWhile.postDeath(entity);
		if (entity.level() instanceof ServerLevel serverLevel && !StayAWhile.scatterDrops(serverLevel))
		{
			entity.setDeltaMovement(Vec3.ZERO);
		}

		return entity;
	}
}
