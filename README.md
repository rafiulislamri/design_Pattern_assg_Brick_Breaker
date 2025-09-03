## Brick Breaker - Java (Design Pattern Base GAME)

- File Structure -
  BrickBreakerGame/
└─ src/
   ├─ main/
   │  ├─ GameLauncher.java
   │  ├─ GameFrame.java
   │  └─ GamePanel.java
   ├─ core/
   │  ├─ GameManager.java
   │  ├─ Level.java
   │  ├─ LevelBuilder.java
   │  ├─ CollisionHandler.java
   │  ├─ PaddleCollision.java
   │  ├─ BrickCollision.java
   │  └─ WallCollision.java
   ├─ entities/
   │  ├─ Paddle.java
   │  ├─ Ball.java
   │  ├─ Brick.java
   │  ├─ NormalBrick.java
   │  ├─ StrongBrick.java
   │  └─ BonusBrick.java
   ├─ patterns/
   │  ├─ BrickFactory.java
   │  ├─ PaddleDecorator.java
   │  ├─ SpeedBoost.java
   │  ├─ Observer.java
   │  ├─ Subject.java
   │  └─ ScoreBoard.java
   └─ utils/
      └─ SoundPlayer.java


## Terminal Commands to EXECUTE

cd "File_Path_Leading_to_'src'_file"
javac main/*.java core/*.java entities/*.java patterns/*.java utils/*.java
java main.GameLauncher


