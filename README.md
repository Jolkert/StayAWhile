# Stay a While

Stay a While allows you to customize the amount of time it takes for items on the ground to despawn, and allows for
customization based on how the item was dropped. It makes items dropped on death not randomly scatter on the ground.

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

## Works Fully Serverside

In multiplayer, the mod **only needs to be installed on the server to function**, and players without the mod installed are able to connect to servers with the mod installed.

## Version Policy

As with most of my mods, the plan is to keep Stay a While on 1.21.1 Neoforge & Fabric
for as long as 1.21.1 is a prominent version for modpacks, and continue pushing
updates for the version of Minecraft on Fabric.

As the "long-term-support" version, 1.21.1 is the only Minecraft version
older than Latest Release which will be receiving new versions of the mod.

## Bug Reports

If you find any bugs, please report them to the [codeberg repo](https://codeberg.org/jolkert/stay-a-while/issues)

## Modpacks

As with all of my mods, you may include Stay a While in any modpack.
