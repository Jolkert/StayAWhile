package dev.jolkert.stayawhile;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;

import java.util.function.Consumer;

public class StayAWhileCommonLts
{
	public static GameRules.Key<GameRules.IntegerValue> MAX_ITEM_AGE;
	public static GameRules.Key<GameRules.IntegerValue> MAX_PLAYER_THROWN_ITEM_AGE;
	public static GameRules.Key<GameRules.IntegerValue> MAX_PLAYER_DEATH_ITEM_AGE;
	public static GameRules.Key<GameRules.BooleanValue> SCATTER_DEATH_ITEMS;

	public static void init(Consumer<ItemEntity> deathCallback)
	{
		StayAWhile.init(new DropChecker()
		{
			@Override
			public int maxItemAge(DropType dropType, ServerLevel world)
			{
				return StayAWhileCommonLts.maxItemAge(dropType, world);
			}

			@Override
			public boolean scatterDrops(ServerLevel world)
			{
				return world.getGameRules().getBoolean(StayAWhileCommonLts.SCATTER_DEATH_ITEMS);
			}

			@Override
			public void onDeath(ItemEntity item)
			{
				deathCallback.accept(item);
			}
		});
	}

	public static int maxItemAge(DropType dropType, ServerLevel world)
	{
		return world.getGameRules().getInt(switch (dropType)
		{
			case DropType.DEFAULT -> StayAWhileCommonLts.MAX_ITEM_AGE;
			case DropType.PLAYER_THROWN -> StayAWhileCommonLts.MAX_PLAYER_THROWN_ITEM_AGE;
			case DropType.PLAYER_DEATH_DROP -> StayAWhileCommonLts.MAX_PLAYER_DEATH_ITEM_AGE;
		});
	}
}
