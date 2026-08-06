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

}
