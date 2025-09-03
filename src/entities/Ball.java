package entities;

import java.awt.Graphics;
import java.awt.Color;

public class Ball {
    private double x, y;
    private double dx = 0, dy = 0;
    private int radius;
    private boolean launched = false;

    public Ball(double x, double y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.dx = 2;
        this.dy = -3;
    }

    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
    }

    public void update() {
        if (!launched)
            return;
        x += dx;
        y += dy;
    }

    public void launch() {
        launched = true;
    }

    public boolean isLaunched() {
        return launched;
    }

    public void reset(double nx, double ny) {
        x = nx;
        y = ny;
        dx = 2;
        dy = -3;
        launched = false;
    }

    // getters/setters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
