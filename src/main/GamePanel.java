package main;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import core.*;
import entities.*;
import patterns.*;
import utils.SoundPlayer;

public class GamePanel extends JPanel {
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private Timer timer;
    private CollisionHandler collisionChain;
    private ScoreBoard scoreboard;
    private SoundPlayer sound;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));

        // Game objects
        paddle = new Paddle(340, 520, 120, 12);
        ball = new Ball(400, 500, 8);
        // Decorator example: apply speed boost powerup if needed (not active by
        // default)
        // paddle = new SpeedBoost(paddle); // uncomment to start with boost

        // Build level
        Level lvl = new Level.LevelBuilder()
                .rows(5)
                .cols(8)
                .brickWidth(80)
                .brickHeight(24)
                .gap(6)
                .build();
        bricks = lvl.getBricks();
        // place bricks in grid
        int startX = 20, startY = 40;
        int idx = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 8; c++) {
                if (idx >= bricks.size())
                    continue;
                Brick b = bricks.get(idx++);
                b.setPosition(startX + c * (b.getWidth() + lvl.getGap()), startY + r * (b.getHeight() + lvl.getGap()));
            }
        }

        // Scoreboard (observer)
        scoreboard = new ScoreBoard();
        GameManager.getInstance().addObserver(scoreboard);

        // Collision chain
        WallCollision wall = new WallCollision(this);
        BrickCollision brick = new BrickCollision(this);
        PaddleCollision paddleHandler = new PaddleCollision(this);

        // chain order: paddle -> brick -> wall (paddle first so paddle bounces before
        // bricks maybe)
        paddleHandler.setNext(brick);
        brick.setNext(wall);
        collisionChain = paddleHandler;

        // sound adapter
        sound = new SoundPlayer();

        // keyboard
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_LEFT)
                    paddle.moveLeft();
                else if (k == KeyEvent.VK_RIGHT)
                    paddle.moveRight();
                else if (k == KeyEvent.VK_SPACE) {
                    if (!ball.isLaunched()) {
                        ball.launch();
                    } else {
                        // pause/resume could be implemented
                    }
                } else if (k == KeyEvent.VK_B) {
                    // debug: give speed boost
                    paddle = new SpeedBoost(paddle);
                } else if (k == KeyEvent.VK_R) {
                    reset();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_RIGHT)
                    paddle.stop();
            }
        });

        // game loop
        timer = new Timer(12, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    public void reset() {
        GameManager.getInstance().reset();
        ball.reset(400, 500);
        paddle.setX(340);
        // reset bricks
        for (Brick b : bricks) {
            b.setAlive(true);
        }
    }

    private void update() {
        paddle.update(getWidth());
        ball.update();
        // handle collisions via chain
        collisionChain.handle(ball, paddle, bricks);
        // remove dead bricks and award score
        List<Brick> toRemove = new ArrayList<>();
        for (Brick b : bricks) {
            if (!b.isAlive()) {
                toRemove.add(b);
            }
        }
        if (!toRemove.isEmpty()) {
            for (Brick b : toRemove) {
                GameManager.getInstance().addScore(b.getPoints());
                // play sound
                sound.playSound("brick_hit");
                bricks.remove(b);
            }
            GameManager.getInstance().notifyObservers();
        }
        // ball loses below paddle
        if (ball.getY() > getHeight()) {
            // reset ball and paddle position
            ball.reset(400, 500);
            paddle.setX(340);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // draw objects
        paddle.draw(g);
        ball.draw(g);
        for (Brick b : bricks) {
            if (b.isAlive())
                b.draw(g);
        }

        // draw UI
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString("Score: " + GameManager.getInstance().getScore(), 20, 20);
        g.drawString("Space: launch | Left/Right: move | B: boost | R: reset", 260, 20);
        // draw scoreboard observer text if needed
        String s = scoreboard.getText();
        if (s != null) {
            g.drawString(s, 20, 40);
        }
    }

    // accessors for handlers
    public List<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }
}
