package core;

import java.util.List;
import entities.Ball;
import entities.Paddle;
import entities.Brick;

import java.awt.Rectangle;

public class BrickCollision extends CollisionHandler {
    private main.GamePanel panel;

    public BrickCollision(main.GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void handle(Ball ball, Paddle paddle, List<Brick> bricks) {
        Rectangle ballRect = new Rectangle((int) (ball.getX() - ball.getRadius()),
                (int) (ball.getY() - ball.getRadius()), ball.getRadius() * 2, ball.getRadius() * 2);
        for (Brick b : bricks) {
            if (!b.isAlive())
                continue;
            Rectangle br = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            if (ballRect.intersects(br)) {
                // simple collision response: invert dy
                ball.setDy(-ball.getDy());
                b.hit();
                // if bonus brick, give a power-up: decorate paddle
                if (b instanceof entities.BonusBrick) {
                    Paddle p = panel.getPaddle();
                    panel.getPaddle(); // we will not reassign here; GamePanel key 'B' can add boost
                }
                return; // handled
            }
        }
        super.handle(ball, paddle, bricks);
    }
}
