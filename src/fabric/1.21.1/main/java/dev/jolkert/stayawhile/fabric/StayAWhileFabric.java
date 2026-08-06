package dev.jolkert.stayawhile.fabric;

import dev.jolkert.stayawhile.StayAWhileCommonLts;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class StayAWhileFabric implements ModInitializer
{
	@Override
	public void onInitialize()
	{
		StayAWhileCommonLts.init((__) -> {
		});

		StayAWhileCommonLts.MAX_ITEM_AGE = GameRuleRegistry.register(
			"itemDespawnTime",
			GameRules.Category.DROPS,
			GameRuleFactory.createIntRule(6000)
		);
		StayAWhileCommonLts.MAX_PLAYER_THROWN_ITEM_AGE = GameRuleRegistry.register(
			"thrownItemDespawnTime",
			GameRules.Category.DROPS,
			GameRuleFactory.createIntRule(6000)
		);
		StayAWhileCommonLts.MAX_PLAYER_DEATH_ITEM_AGE = GameRuleRegistry.register(
			"deathDropDespawnTime",
			GameRules.Category.DROPS,
			GameRuleFactory.createIntRule(-1)
		);
		StayAWhileCommonLts.SCATTER_DEATH_ITEMS = GameRuleRegistry.register(
			"scatterDeathDrops",
			GameRules.Category.DROPS,
			GameRuleFactory.createBooleanRule(false)
		);
	}
}
