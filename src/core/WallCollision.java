package core;

import java.util.List;
import entities.Ball;
import entities.Paddle;
import entities.Brick;

public class WallCollision extends CollisionHandler {
    private main.GamePanel panel;

    public WallCollision(main.GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void handle(Ball ball, Paddle paddle, List<Brick> bricks) {
        // left and right walls
        if (ball.getX() - ball.getRadius() <= 0) {
            ball.setDx(Math.abs(ball.getDx()));
            return;
        } else if (ball.getX() + ball.getRadius() >= panel.getWidth()) {
            ball.setDx(-Math.abs(ball.getDx()));
            return;
        }
        // top wall
        if (ball.getY() - ball.getRadius() <= 0) {
            ball.setDy(Math.abs(ball.getDy()));
            return;
        }
        // else pass to next (none)
        super.handle(ball, paddle, bricks);
    }
}
