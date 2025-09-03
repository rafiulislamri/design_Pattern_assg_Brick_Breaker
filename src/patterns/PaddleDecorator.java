package patterns;

import entities.Paddle;

public abstract class PaddleDecorator extends Paddle {
    protected Paddle decorated;

    public PaddleDecorator(Paddle p) {
        super(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        this.decorated = p;
    }

    @Override
    public void draw(java.awt.Graphics g) {
        decorated.draw(g);
    }

    @Override
    public void moveLeft() {
        decorated.moveLeft();
    }

    @Override
    public void moveRight() {
        decorated.moveRight();
    }

    @Override
    public void stop() {
        decorated.stop();
    }

    @Override
    public void update(int panelWidth) {
        decorated.update(panelWidth);
    }

    // delegate getters/setters
    @Override
    public int getX() {
        return decorated.getX();
    }

    @Override
    public int getY() {
        return decorated.getY();
    }

    @Override
    public int getWidth() {
        return decorated.getWidth();
    }

    @Override
    public int getHeight() {
        return decorated.getHeight();
    }

    @Override
    public void setX(int x) {
        decorated.setX(x);
    }

    @Override
    public void setSize(int w, int h) {
        decorated.setSize(w, h);
    }
}
