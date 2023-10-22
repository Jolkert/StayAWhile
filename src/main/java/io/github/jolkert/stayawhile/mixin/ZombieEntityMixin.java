package io.github.jolkert.stayawhile.mixin;

import io.github.jolkert.stayawhile.StayAWhile;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin
{
	@Redirect(
			method = "onKilledOther",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;"
			)
	)
	private Difficulty alwaysPassFirstCheck(ServerWorld __)
	{
		System.out.println("bypass gamemode check");
		return Difficulty.NORMAL;
	}

	@Redirect(
			method = "onKilledOther",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextBoolean()Z")
	)
	private boolean shouldConvert(Random random)
	{
		int chance = ((ZombieEntity)(Object)this).getEntityWorld().getGameRules().getInt(StayAWhile.VILLAGER_CONVERT_PERCENT);

		// invert because the check returns on true and spawns on false -morg 2023-10-23
		return !switch (chance)
		{
			case 0 -> false;
			case 100 -> true;
			default ->  random.nextInt(100) < chance;
		};
	}
}
