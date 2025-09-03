package entities;

import java.awt.Graphics;
import java.awt.Color;

public class StrongBrick extends Brick {
    private int hitsRemaining = 2;

    public StrongBrick() {
        this.points = 25;
    }

    @Override
    public void draw(Graphics g) {
        if (!alive)
            return;
        if (hitsRemaining == 2)
            g.setColor(new Color(178, 34, 34)); // dark red
        else
            g.setColor(new Color(255, 99, 71)); // lighter
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void hit() {
        hitsRemaining--;
        if (hitsRemaining <= 0)
            alive = false;
    }
}
