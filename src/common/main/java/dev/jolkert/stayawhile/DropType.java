package dev.jolkert.stayawhile;

import dev.jolkert.stayawhile.duck.ItemEntityDuck;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;

public enum DropType
{
	DEFAULT,
	PLAYER_THROWN,
	PLAYER_DEATH_DROP;

	public static DropType from(ItemEntity item)
	{
		if (((ItemEntityDuck) item).stayawhile$wasThrown())
		{
			return DropType.PLAYER_THROWN;
		}
		else if (((ItemEntityDuck) item).stayawhile$isDeathDrop())
		{
			return DropType.PLAYER_DEATH_DROP;
		}
		else
		{
			return DropType.DEFAULT;
		}
	}

	public int maxAge(Level world)
	{
		return world.getGameRules().getInt(switch (this)
		{
			case DEFAULT -> StayAWhile.MAX_ITEM_AGE;
			case PLAYER_THROWN -> StayAWhile.MAX_PLAYER_THROWN_ITEM_AGE;
			case PLAYER_DEATH_DROP -> StayAWhile.MAX_PLAYER_DEATH_ITEM_AGE;
		});
	}
}
