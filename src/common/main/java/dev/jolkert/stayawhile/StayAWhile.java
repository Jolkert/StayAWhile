package dev.jolkert.stayawhile;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiFunction;

public class StayAWhile
{
	public static final String MOD_ID = "stay_a_while";
	public static final Logger LOGGER = LoggerFactory.getLogger(StayAWhile.MOD_ID);

	private static DropChecker CHECKER;

	// TODO: again, great @Expect/@Actual candidate once we get that working -morgan 2026-08-04
	public static void init(DropChecker checker)
	{
		StayAWhile.LOGGER.info("Initializing {}...", StayAWhile.MOD_ID);
		StayAWhile.CHECKER = checker;
	}

	private static BiFunction<DropType, ServerLevel, Integer> GET_MAX_ITEM_AGE;

	public static int maxItemAge(DropType dropType, ServerLevel world)
	{
		return StayAWhile.CHECKER.maxItemAge(dropType, world);
	}

	public static boolean scatterDrops(ServerLevel world)
	{
		return StayAWhile.CHECKER.scatterDrops(world);
	}

	public static void postDeath(ItemEntity item)
	{
		StayAWhile.CHECKER.onDeath(item);
	}
}
