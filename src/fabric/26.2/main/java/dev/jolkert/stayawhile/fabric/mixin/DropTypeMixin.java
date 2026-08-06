package dev.jolkert.stayawhile.fabric.mixin;


import dev.jolkert.stayawhile.duck.ItemEntityDuck;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ItemEntity.class)
public class DropTypeMixin implements ItemEntityDuck
{
	@Shadow
	@Nullable
	private EntityReference<Entity> thrower;

	@Unique
	private boolean stayawhile$playerDropped = false;

	@Override
	public boolean stayawhile$isDeathDrop()
	{
		return this.stayawhile$playerDropped;
	}

	@Override
	public void stayawhile$setDeathDrop(boolean deathDrop)
	{
		this.stayawhile$playerDropped = deathDrop;
	}

	@Override
	public boolean stayawhile$wasThrown()
	{
		return this.thrower != null;
	}


	@Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
	void addDeathDropStatus(ValueOutput output, CallbackInfo ci)
	{
		output.putBoolean("DeathDrop", stayawhile$playerDropped);
	}


	@Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
	void readDeathDropStatus(ValueInput input, CallbackInfo ci)
	{
		var self = (ItemEntityDuck) (ItemEntity) (Object) this;
		self.stayawhile$setDeathDrop(input.getBooleanOr("DeathDrop", false));
	}
}
