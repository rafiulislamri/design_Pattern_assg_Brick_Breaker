package core;

import java.util.List;
import entities.Ball;
import entities.Paddle;
import entities.Brick;

import java.awt.Rectangle;

public class PaddleCollision extends CollisionHandler {
    private main.GamePanel panel;

    public PaddleCollision(main.GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void handle(Ball ball, Paddle paddle, List<Brick> bricks) {
        Rectangle ballRect = new Rectangle((int) (ball.getX() - ball.getRadius()),
                (int) (ball.getY() - ball.getRadius()), ball.getRadius() * 2, ball.getRadius() * 2);
        Rectangle paddleRect = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        if (ballRect.intersects(paddleRect) && ball.getDy() > 0) {
            // reflect ball based on where it hit the paddle
            int hitPos = (int) (ball.getX() - paddle.getX());
            double percent = (double) hitPos / paddle.getWidth();
            double angle = (percent - 0.5) * Math.PI / 2; // -45 to +45 degrees
            double speed = Math.sqrt(ball.getDx() * ball.getDx() + ball.getDy() * ball.getDy());
            ball.setDx(speed * Math.sin(angle));
            ball.setDy(-Math.abs(speed * Math.cos(angle)));
            return; // handled; do not pass to next
        } else {
            super.handle(ball, paddle, bricks);
        }
    }
}
