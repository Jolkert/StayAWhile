package io.github.jolkert.stayawhile.mixin;

import io.github.jolkert.stayawhile.StayAWhile;
import io.github.jolkert.stayawhile.access.ItemEntityInterface;
import io.github.jolkert.stayawhile.data.DropType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin implements ItemEntityInterface
{
	@Shadow private int itemAge;

	@Unique	private DropType dropType = DropType.DEFAULT;
	@Override public void setDropType(DropType dropType)
	{
		this.dropType = dropType;
	}
	@Override public DropType getDropType()
	{
		return dropType;
	}

	@Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V", at = @At("TAIL"))
	public void setMinimumAgeIfNecessary(EntityType<? extends ItemEntity> entityType, World world, CallbackInfo ci)
	{
		setDropType(DropType.DEFAULT);
		if (world.getGameRules().getInt(StayAWhile.MAX_ITEM_AGE) < 0)
			this.itemAge = Short.MIN_VALUE;
	}

	@ModifyConstant(method = "tick", constant = @Constant(intValue = 6000))
	private int modifyDespawnAge(int oldAge)
	{
		ItemEntity self = (ItemEntity) (Object) this;
		return dropType.getMaximumAge(self.getWorld());
	}
}
