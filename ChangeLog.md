ChangeLog
=========
Version 5.0
-----------
- (RC.4) The GUI of the game is successfully implemented.

- (B.2) The model has been modified a little in order to fit the new GUI related needs of the controller.

- (B.1) The turns' system was re implemented in order to fit with the GUI.

Version 4.0
-----------
- (RC.4) The controller has been implemented and tested
with a 100% of coverage in classes, methods and branches. 
Lines have 99% of coverage due to a try-catch statement.

- (B.2) The model has been modified a little in order to fit the controller.

- (B.1) The turns' system of the game has been moved from the model to
the controller.



Version 3.0
-----------
- (RC.3) The model has been tested with a 100% of coverage in classes, methods, lines and branches.
- (B.2) All the tests for the previous changes were created. 
- (B.1) The previous equipWeapon implementation was replaced by a double dispatch


Version 2.0
-----------
- (RC.2) The model was modified in order to respect S.O.L.I.D. principles.
- (B.4) Created a new equipweapon methon that considers the character class restrictions. 
- (B.3) Implemented all types of weapons as a specific java class.
- (B.2) Implemented all playable character classes as a specific java class.
- (B.1) Created two different waitTurn methods for the enemy class and the player.

Version 1.0
-----------
- (RC.1) Implemented missing tests
- (B.5) Updated License
- (B.4) Implementation and testing of Enemy class (ensured 100% branch coverage)
- (B.3) Created .gitignore
- (B.2) Implementation of most base elements of the model
- (B.1) Created project

A note on the version naming
----------------------------
- B.n: Version ``x.y`` _beta x_, alternative to ``x.y-b.n``.
  For example: ``v1.0-b.3``.
- RC.n: Release candidate x, alternative to ``x.y-rc.n``.
  For example: ``v1.0-rc.2``.
