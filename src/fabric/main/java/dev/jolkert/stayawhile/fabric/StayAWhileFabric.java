package dev.jolkert.stayawhile.fabric;

import dev.jolkert.stayawhile.StayAWhile;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class StayAWhileFabric implements ModInitializer
{
	@Override
	public void onInitialize()
	{
		StayAWhile.MAX_ITEM_AGE = GameRuleRegistry.register(
				"maxItemAge",
				GameRules.Category.DROPS,
				GameRuleFactory.createIntRule(6000)
		);

		StayAWhile.MAX_PLAYER_THROWN_ITEM_AGE = GameRuleRegistry.register(
				"maxPlayerThrownItemAge",
				GameRules.Category.DROPS,
				GameRuleFactory.createIntRule(6000)
		);

		StayAWhile.MAX_PLAYER_DEATH_ITEM_AGE = GameRuleRegistry.register(
				"maxPlayerDeathItemAge",
				GameRules.Category.DROPS,
				GameRuleFactory.createIntRule(-1)
		);

		StayAWhile.SCATTER_DEATH_ITEMS = GameRuleRegistry.register(
				"scatterDeathItems",
				GameRules.Category.DROPS,
				GameRuleFactory.createBooleanRule(false)
		);
	}
}
