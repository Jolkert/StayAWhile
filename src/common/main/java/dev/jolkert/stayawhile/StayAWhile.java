package dev.jolkert.stayawhile;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.GameRules;

import java.util.function.Consumer;

public class StayAWhile
{
	public static GameRules.Key<GameRules.IntegerValue> MAX_ITEM_AGE;
	public static GameRules.Key<GameRules.IntegerValue> MAX_PLAYER_THROWN_ITEM_AGE;
	public static GameRules.Key<GameRules.IntegerValue> MAX_PLAYER_DEATH_ITEM_AGE;
	public static GameRules.Key<GameRules.BooleanValue> SCATTER_DEATH_ITEMS;

	public static Consumer<ItemEntity> POST_DEATH = (__) -> {
	};
}
