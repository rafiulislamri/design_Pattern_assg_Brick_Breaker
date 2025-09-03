package entities;

import java.awt.Graphics;
import java.awt.Color;

public class BonusBrick extends Brick {
    public BonusBrick() {
        this.points = 50;
    }

    @Override
    public void draw(Graphics g) {
        if (!alive)
            return;
        g.setColor(new Color(60, 179, 113)); // green
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void hit() {
        alive = false;
        // Extra behavior can be handled by collision handler (power-up)
    }
}
