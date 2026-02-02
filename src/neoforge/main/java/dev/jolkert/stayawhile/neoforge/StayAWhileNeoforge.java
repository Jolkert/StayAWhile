package dev.jolkert.stayawhile.neoforge;

import dev.jolkert.stayawhile.DropType;
import dev.jolkert.stayawhile.StayAWhile;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;

@Mod("stay_a_while")
@EventBusSubscriber
public class StayAWhileNeoforge
{
	public StayAWhileNeoforge()
	{
		StayAWhile.MAX_ITEM_AGE = GameRules.register(
				"maxItemAge",
				GameRules.Category.DROPS,
				GameRules.IntegerValue.create(6000)
		);

		StayAWhile.MAX_PLAYER_THROWN_ITEM_AGE = GameRules.register(
				"maxPlayerThrownItemAge",
				GameRules.Category.DROPS,
				GameRules.IntegerValue.create(6000)
		);

		StayAWhile.MAX_PLAYER_DEATH_ITEM_AGE = GameRules.register(
				"maxPlayerDeathItemAge",
				GameRules.Category.DROPS,
				GameRules.IntegerValue.create(-1)
		);

		StayAWhile.SCATTER_DEATH_ITEMS = GameRules.register(
				"scatterDeathItems",
				GameRules.Category.DROPS,
				GameRules.BooleanValue.create(false)
		);
	}

	@SubscribeEvent
	static void onItemExpire(ItemExpireEvent event)
	{
		ItemEntity entity = event.getEntity();
		int maxAge = DropType.from(entity).maxAge(entity.level());

		if (maxAge < 0) {
			entity.setUnlimitedLifetime();
		}
		else if (entity.getAge() < maxAge)
		{
			event.addExtraLife(maxAge - entity.getAge());
		}
	}
}

