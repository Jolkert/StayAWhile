package dev.jolkert.stayawhile.fabric;

import dev.jolkert.stayawhile.DropChecker;
import dev.jolkert.stayawhile.DropType;
import dev.jolkert.stayawhile.StayAWhile;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class StayAWhileFabric implements ModInitializer
{
	public static GameRule<Integer> MAX_ITEM_AGE;
	public static GameRule<Integer> MAX_PLAYER_THROWN_ITEM_AGE;
	public static GameRule<Integer> MAX_PLAYER_DEATH_ITEM_AGE;
	public static GameRule<Boolean> SCATTER_DEATH_ITEMS;

	public static Identifier identifier(String path)
	{
		return Identifier.fromNamespaceAndPath(StayAWhile.MOD_ID, path);
	}

	@Override
	public void onInitialize()
	{
		StayAWhile.init(new DropChecker()
		{
			@Override
			public int maxItemAge(DropType dropType, ServerLevel world)
			{
				return StayAWhileFabric.getMaxItemAge(dropType, world);
			}

			@Override
			public boolean scatterDrops(ServerLevel world)
			{
				return world.getGameRules().get(SCATTER_DEATH_ITEMS);
			}

			@Override
			public void onDeath(ItemEntity item)
			{
			}
		});

		StayAWhileFabric.MAX_ITEM_AGE = GameRuleBuilder
			.forInteger(6000)
			.category(GameRuleCategory.DROPS)
			.buildAndRegister(StayAWhileFabric.identifier("item_despawn_time"));
		StayAWhileFabric.MAX_PLAYER_THROWN_ITEM_AGE = GameRuleBuilder
			.forInteger(6000)
			.category(GameRuleCategory.DROPS)
			.buildAndRegister(StayAWhileFabric.identifier("thrown_item_despawn_time"));
		StayAWhileFabric.MAX_PLAYER_DEATH_ITEM_AGE = GameRuleBuilder
			.forInteger(-1)
			.category(GameRuleCategory.DROPS)
			.buildAndRegister(StayAWhileFabric.identifier("death_drop_despawn_time"));
		StayAWhileFabric.SCATTER_DEATH_ITEMS = GameRuleBuilder
			.forBoolean(false)
			.category(GameRuleCategory.DROPS)
			.buildAndRegister(StayAWhileFabric.identifier("scatter_death_drops"));
	}

	public static int getMaxItemAge(DropType dropType, ServerLevel world)
	{
		return world.getGameRules().get(switch (dropType)
		{
			case DropType.DEFAULT -> StayAWhileFabric.MAX_ITEM_AGE;
			case DropType.PLAYER_THROWN -> StayAWhileFabric.MAX_PLAYER_THROWN_ITEM_AGE;
			case DropType.PLAYER_DEATH_DROP -> StayAWhileFabric.MAX_PLAYER_DEATH_ITEM_AGE;
		});
	}
}
