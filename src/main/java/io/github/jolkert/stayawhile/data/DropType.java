package io.github.jolkert.stayawhile.data;

import io.github.jolkert.stayawhile.StayAWhile;
import net.minecraft.world.World;

public enum DropType
{
	DEFAULT,
	PLAYER_DROPPED,
	PLAYER_DEATH_DROP;

	public boolean isPlayerDropped()
	{
		return this == PLAYER_DROPPED || this == PLAYER_DEATH_DROP;
	}
	public boolean isDeathDrop()
	{
		return this == PLAYER_DEATH_DROP;
	}

	public int getMaximumAge(World world)
	{
		return world.getGameRules().getInt(switch (this)
				{
					case DEFAULT -> StayAWhile.MAX_ITEM_AGE;
					case PLAYER_DROPPED -> StayAWhile.MAX_PLAYER_THROWN_ITEM_AGE;
					case PLAYER_DEATH_DROP -> StayAWhile.MAX_PLAYER_DEATH_ITEM_AGE;
				});
	}
}
