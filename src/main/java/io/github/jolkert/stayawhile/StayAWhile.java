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
	public void onInitialize()
	{
		LOGGER.info("Successfully initialized!");
	}

	public static final GameRules.Key<GameRules.IntRule> MAX_ITEM_AGE =
			GameRuleRegistry.register(
					"maxItemAge",
					GameRules.Category.MISC,
					GameRuleFactory.createIntRule(6000)
			);
	public static final GameRules.Key<GameRules.IntRule> MAX_PLAYER_THROWN_ITEM_AGE =
			GameRuleRegistry.register(
					"maxPlayerThrownItemAge",
					GameRules.Category.MISC,
					GameRuleFactory.createIntRule(6000)
			);
	public static final GameRules.Key<GameRules.IntRule> MAX_PLAYER_DEATH_ITEM_AGE =
			GameRuleRegistry.register(
					"maxPlayerDeathItemAge",
					GameRules.Category.MISC,
					GameRuleFactory.createIntRule(6000)
			);

	public static final GameRules.Key<GameRules.IntRule> VILLAGER_CONVERT_PERCENT =
			GameRuleRegistry.register(
				"villagerConvertPercent",
				GameRules.Category.MOBS,
				GameRuleFactory.createIntRule(100)
			);
}
