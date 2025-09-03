package entities;

import java.awt.Graphics;
import java.awt.Color;

public class Paddle {
    protected int x, y, width, height;
    protected int speed = 8;
    protected int dx = 0;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
    }

    public void moveLeft() {
        dx = -speed;
    }

    public void moveRight() {
        dx = speed;
    }

    public void stop() {
        dx = 0;
    }

    public void update(int panelWidth) {
        x += dx;
        if (x < 0)
            x = 0;
        if (x + width > panelWidth)
            x = panelWidth - width;
    }

    // getters/setters
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

    public void setX(int x) {
        this.x = x;
    }

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
    }
}
