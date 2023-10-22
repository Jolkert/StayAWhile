# Stay A While
Have you ever thought that your items disappear too quickly? Perhaps the normal timer is just fine, but you wish you had just a little more time to collect your items after you die. Or maybe you want your items to despawn faster to give death that little extra stress factor. Perhaps you also want villagers to always convert zombies. 
Whichever type of player you are, Stay A While is the mod for you

# What does it do?
Stay A While adds four new gamerules
* `maxPlayerThrownItemAge` the amount of ticks an item that was **thrown by a player (using `Q` key by default)** takes to despawn (default: 6000)
* `maxPlayerDeathItemAge` the amount of ticks an item that was **dropped by a player upon death** takes to despawn (default: 6000)
* `maxItemAge` the amount of ticks all other dropped items take to despawn (default: 6000)
* `villagerConvertPercent` the percentage chance that a villager is converted to a zombie villager when killed by a zombie

In case you are unfamiliar with them, **20 ticks == 1 second**  
Any value less than 0 (i.e. negative values) on the item-based gamerules are interpreted to mean "never despawn"

# Other questions you might want answers to
## Does this work on servers?
Yes! Just install on the server and you're good to go. No players need to install!

## What if I change the value while items are still on the ground?
Any items dropped on the ground should retain the maximum age they had from the time they were dropped

## If you've added more than just the one item gamerule shouldn't you rename this?
Yes, yes I should. I'll get back to you when I think of something better

## Will you add more gamerules?
Absolutely. If I encounter a situation in vanilla play where I think "hey this should really just be a gamerule." updating this mod will be my goto solution. If you have any specific requests for gamerules you would like to see added, post a feature request on the [GitHub issues page](https://github.com/jolkert/stayawhile/issues) (it's not just for bugs!)