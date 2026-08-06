package dev.jolkert.stayawhile;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;

public interface DropChecker
{
	int maxItemAge(DropType dropType, ServerLevel world);
	boolean scatterDrops(ServerLevel world);
	void onDeath(ItemEntity item);
}
