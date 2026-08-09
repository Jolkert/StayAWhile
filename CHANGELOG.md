# Stay A While Changelog
# Version 3.0.0
## Changes
- **BREAKING:** renamed all gamerules on 1.21.1 to be more intuitively named. You will have to reset your gamerules on update.
	- `maxItemage` -> `itemDespawnTime`
	- `maxPlayerThrownItemAge` -> `thrownItemDespawnTime`
	- `maxPlayerThrownItemAge` -> `deathDropDespawnTime`
	- `scatterDeathItems` -> `scatterDeathDrops`
## Additions
- Now available on 26.2 (Fabric)!
- All gamerules named differently on 26.2 for consistency with vanilla gamerule names
	- `itemDespawnTime` == `stay_a_while:item_despawn_time` 
	- `thrownItemDespawnTime` == `stay_a_while:thrown_item_despawn_time`
	- `deathDropDespawnTime` == `stay_a_while:thrown_item_despawn_time`
	- `scatterDeathDrops` == `stay_a_while:scatter_death_drops`
# Version 2.0.0
## Additions
- Added `scatterDeathItems` gamerule. Controls whether items dropped on player death scatter randomly (default: `false`)
- Now available on Neoforge!
## Removals
- Removed `villagerConversionPercent` gamerule. Will be spun off into its own mod.
## Bugfixes
- **Fixed:** Items lose their thrown/death-specific despawn timers upon chunk reload.

## Version 1.1.1
### Bugfixes
- Fixed a bug where translation strings for gamerules were not being shown properly

## Version 1.1.0
### Additions
- Added `maxPlayerThrownItemAge` gamerule. Controls number of ticks until items thrown on the by players (using the `Q` key by default) despawn (default: 6000)

### Changes
- Renamed gamerule `itemDespawnAge` to `maxItemAge`
- Renamed gamerule `deathItemDespawnAge` to `maxPlayerDeathItemAge`

### Technical Changes
- Negative value despawn prevention is now implemented by setting the `itemAge` in the `ItemEntity` to the minimum signed 16-bit integer limit (-32768), taking advantage of vanilla behavior

## Version 1.0.0
- Added `itemDespawnAge` gamerule. Controls number of ticks until items on the ground despawn (default: 6000)
- Added `deathItemDespawnAge` gamerule. Controls number of ticks until items dropped by players upon death despawn (default: 6000)
- Negative values for either gamerule cause items in the category to never despawn
