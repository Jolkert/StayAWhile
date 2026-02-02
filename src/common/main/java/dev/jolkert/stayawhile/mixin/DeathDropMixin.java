package dev.jolkert.stayawhile.mixin;

import dev.jolkert.stayawhile.duck.ItemEntityDuck;
import net.minecraft.nbt.CompoundTag;
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
public class DeathDropMixin implements ItemEntityDuck
{
	@Shadow
	@Nullable
	private UUID thrower;

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
	void addDeathDropStatus(CompoundTag compound, CallbackInfo ci)
	{
		compound.putBoolean("DeathDrop", stayawhile$playerDropped);
	}

	@Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
	void readDeathDropStatus(CompoundTag compound, CallbackInfo ci)
	{
		if (compound.contains("DeathDrop"))
		{
			this.stayawhile$setDeathDrop(compound.getBoolean("DeathDrop"));
		}
	}
}
