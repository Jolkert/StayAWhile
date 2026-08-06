package dev.jolkert.stayawhile.neoforge;

import dev.jolkert.stayawhile.DropType;
import dev.jolkert.stayawhile.StayAWhile;
import dev.jolkert.stayawhile.StayAWhileCommonLts;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;


@Mod(StayAWhile.MOD_ID)
@EventBusSubscriber
public class StayAWhileNeoforge
{
	public StayAWhileNeoforge()
	{
		StayAWhileCommonLts.init(item -> {
			Level world = item.level();
			if (!world.isClientSide())
			{
				// i kinda dont like this function call? it feels weird to call a function that depends on the interface
				// from the function going into the function -morgan 2026-08-05
				item.lifespan = StayAWhile.maxItemAge(DropType.PLAYER_DEATH_DROP, (ServerLevel) world);
			}
		});
	}

	@SubscribeEvent
	static void onItemExpire(ItemExpireEvent event)
	{
		if (!(event.getEntity().level() instanceof ServerLevel world))
		{
			return;
		}

		ItemEntity entity = event.getEntity();

		int maxAge = StayAWhile.maxItemAge(DropType.from(entity), world);

		if (maxAge < 0)
		{
			entity.setUnlimitedLifetime();
		}
		else if (entity.getAge() < maxAge)
		{
			event.addExtraLife(maxAge - entity.getAge());
		}
	}
}
