package patterns;

import entities.Paddle;

public class SpeedBoost extends PaddleDecorator {
    public SpeedBoost(Paddle p) {
        super(p);
        // increase speed by wrapping moves: change underlying speed
        try {
            java.lang.reflect.Field sp = Paddle.class.getDeclaredField("speed");
            sp.setAccessible(true);
            int curr = sp.getInt(decorated);
            sp.setInt(decorated, curr + 6); // boost
        } catch (Exception e) {
            // ignore
        }
    }

    // after some time you might revert boost; for demo, boost remains
}
