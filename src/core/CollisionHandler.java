package core;

import java.util.List;
import entities.Ball;
import entities.Paddle;
import entities.Brick;

public abstract class CollisionHandler {
    protected CollisionHandler next;

    public void setNext(CollisionHandler n) {
        this.next = n;
    }

    // handle returns true if handled (optional); contract: call next if not handled
    public void handle(Ball ball, Paddle paddle, List<Brick> bricks) {
        if (next != null)
            next.handle(ball, paddle, bricks);
    }
}
