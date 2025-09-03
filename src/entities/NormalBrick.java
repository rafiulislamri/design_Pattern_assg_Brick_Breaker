package entities;

import java.awt.Graphics;
import java.awt.Color;

public class NormalBrick extends Brick {
    public NormalBrick() {
        this.points = 10;
    }

    @Override
    public void draw(Graphics g) {
        if (!alive)
            return;
        g.setColor(new Color(70, 130, 180));
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void hit() {
        alive = false;
    }
}
