## workshop16b

Modify Workshop 16 to be a web application.

Functions:
- Add game by entire json string
- Delete game by id

Only accepts "Backgammon" in the json string format
```
{"name":"Backgammon","player_count":2,"pieces":{"rulebook":{"total_count":1,"file":"rulebook-backgammon.pdf"},"board":{"total_count":1,"points":{"total_count":24,"color_1_points":12,"color_2_points":12}},"counters":{"total_count":30,"color_1_counters":15,"color_2_counters":15},"dice":{"total_count":3,"regular_dice":{"dice_count":4,"faces":[1,2,3,4,5,6]},"doubling_cube_dice":{"dice_count":1,"faces":[2,4,8,16,32,64]}},"dice_shaker":{"total_count":2}}}
```