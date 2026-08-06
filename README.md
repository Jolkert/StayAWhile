# Stay A While

Have you ever thought that your items disappear too quickly? Perhaps the normal timer is just fine, but you wish you had
just a little more time to collect your items after you die. Or maybe you want your items to despawn faster to give death
that little extra stress factor. You might also hate it when you die and your items scatter everywhere.
If any of these things sound like you, Stay A While can help with that.

# What does it do?

By default, Stay A While makes items dropped upon death never despawn and not scatter randomly.
Additionally, it adds 4 new gamerules to customize behavior:

- `thrownItemDespawnTime` (1.21.1)/`stay_a_while:thrown_item_despawn_time` (26.2) - the amount of ticks an item that was **thrown by a player (using `Q` key
  by default)** takes to despawn (default: `6000`)
- `deathDropDespawnTime` (1.21.1)/`stay_a_while:death_drop_despawn_time` (26.2) - the amount of ticks an item that was **dropped by a player upon death**
  takes to despawn (default: `-1`; vanilla behavior: `6000`)
- `itemDespawnTime` (1.21.1)/`stay_a_while:item_despawn_time` - the amount of ticks all other dropped items take to despawn (default: `6000`)
- `scatterDeathDrops` (1.21.1)/`stay_a_while:scatter_death_drops` (26.2) - whether or not items dropped upon death scatter randomly (default: `false`,
  vanilla behavior: `true`)

In case you are unfamiliar with them, **20 ticks == 1 second** : 1200 ticks == 1 minute : 6000 ticks == 5 minutes  
Values less than 0 (i.e. negative values) on the despawn time gamerules are make their category of items never despawn

# Other questions you might want answers to

## Does this work on servers?

Yes! Just install on the server and you're good to go. No players need to install!

## What if I change the value while items are still on the ground?

If the max age is **raised** while the item is still alive, it will **always follow the new lifespan**, extending the life of the item.  
If the max age is **lowered** while the item is still alive, the behavior currently **differs between modloaders**:

- On Fabric: items begin checking against the new age immediately, so the item's lifespan is **reduced** upon changing
  the gamerule
- On Neoforge: items only begin checking against the new age if they attempt to despawn, so the item's lifespan is **unaffected**,
  continuing to follow the previous timer
