# Stay A While Changelog
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