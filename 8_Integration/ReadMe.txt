DESIRED IMPROVEMENTS

//VIEW
- Show the active item (item in the player's hand)
- Have small and large images for pickupables so that they can be shown in the
  inventory and on the floor
- Have simple animations working
- Have a score screen, improve look of menu, pause menu etc

//AI
- Haven’t seen enemies move properly in the game yet, so only have done theoretical testing
- Speed of tick may be wrong, so may change this when we have operational enemies in the UI

//GAME/LOGIC/VIEW
- Refactoring the way these libaries communicate to each other. In an attempt to make it easier to understand and better reliability
- Game loop works and user input is detected however, would be nice to see this actually represented in integration testing as we can't render a player as of right now.

//Save/Load
- Overwriting an existing file needs to actually overwrite it, currently does not work.
- Would be really nice to have some sort of comprehensive test suite for save/load however we don't know how to do this with JavaFX. All documentation online is really complex.