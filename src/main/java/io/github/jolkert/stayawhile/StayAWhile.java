package io.github.jolkert.stayawhile;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StayAWhile implements ModInitializer
{
	public static final Logger LOGGER = LoggerFactory.getLogger("stay_a_while");

	@Override
	public void onInitialize() {}

	public static final GameRules.Key<GameRules.IntRule> ITEM_DESPAWN_AGE =
			GameRuleRegistry.register(
					"itemDespawnAge",
					GameRules.Category.MISC,
					GameRuleFactory.createIntRule(6000));
	public static final GameRules.Key<GameRules.IntRule> DEATH_ITEM_DESPAWN_AGE =
			GameRuleRegistry.register(
					"deathItemDespawnAge",
					GameRules.Category.MISC,
					GameRuleFactory.createIntRule(6000));
}
