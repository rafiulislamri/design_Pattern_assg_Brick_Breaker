package entities;

import java.awt.Graphics;
import java.awt.Color;

public abstract class Brick implements Cloneable {
    protected int x, y, width = 80, height = 24;
    protected boolean alive = true;
    protected int points = 10;

    public abstract void draw(Graphics g);

    public abstract void hit();

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (Exception e) {
            return null;
        }
    }

    // common setters/getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean a) {
        this.alive = a;
    }

    public int getPoints() {
        return points;
    }
}
