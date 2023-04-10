package io.github.jolkert.stayawhile.mixin;

import io.github.jolkert.stayawhile.StayAWhile;
import io.github.jolkert.stayawhile.access.ItemEntityAccessor;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ItemEntity.class)
public class ItemEntityMixin implements ItemEntityAccessor
{
	@Unique
	private boolean isDeathDrop;

	@Override
	public void setIsDeathDrop(boolean isDeathDrop)
	{
		this.isDeathDrop = isDeathDrop;
	}

	@Override
	public boolean isDeathDrop()
	{
		return isDeathDrop;
	}

	@ModifyConstant(method = "tick", constant = @Constant(intValue = 6000))
	private int modifyDespawnAge(int oldAge)
	{
		ItemEntity self = (ItemEntity) (Object) this;
		return self.getWorld().getGameRules()
				.getInt(isDeathDrop ? StayAWhile.DEATH_ITEM_DESPAWN_AGE : StayAWhile.ITEM_DESPAWN_AGE);
	}


	@Redirect(method = "tick",
			slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/entity/ItemEntity;itemAge:I")),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;discard()V"))
	public void preventDespawnIfApplicable(ItemEntity instance)
	{
		boolean canDespawn = !(((ItemEntityAccessor) instance).isDeathDrop() ?
				instance.getWorld().getGameRules().getInt(StayAWhile.DEATH_ITEM_DESPAWN_AGE) < 0 :
				instance.getWorld().getGameRules().getInt(StayAWhile.ITEM_DESPAWN_AGE) < 0);
		if (canDespawn)
			instance.discard();
	}
}
