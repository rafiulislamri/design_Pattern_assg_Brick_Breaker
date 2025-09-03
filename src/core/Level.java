package core;

import entities.Brick;
import patterns.BrickFactory;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Brick> bricks;
    private int rows, cols, gap;

    private Level(LevelBuilder b) {
        this.rows = b.rows;
        this.cols = b.cols;
        this.gap = b.gap;
        this.bricks = new ArrayList<>();
        // create bricks using factory + prototype
        for (int i = 0; i < rows * cols; i++) {
            String type;
            // some pattern: every 6th is strong, every 11th is bonus else normal
            if (i % 11 == 0)
                type = "bonus";
            else if (i % 6 == 0)
                type = "strong";
            else
                type = "normal";
            Brick proto = patterns.BrickFactory.createBrick(type);
            Brick clone = (Brick) proto.clone();
            this.bricks.add(clone);
        }
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public int getGap() {
        return gap;
    }

    public static class LevelBuilder {
        private int rows = 3, cols = 6, gap = 4;
        private int brickWidth = 80, brickHeight = 24;

        public LevelBuilder rows(int r) {
            this.rows = r;
            return this;
        }

        public LevelBuilder cols(int c) {
            this.cols = c;
            return this;
        }

        public LevelBuilder gap(int g) {
            this.gap = g;
            return this;
        }

        public LevelBuilder brickWidth(int w) {
            this.brickWidth = w;
            return this;
        }

        public LevelBuilder brickHeight(int h) {
            this.brickHeight = h;
            return this;
        }

        public Level build() {
            Level l = new Level(this);
            // set brick sizes for created bricks
            for (Brick b : l.getBricks()) {
                b.setSize(brickWidth, brickHeight);
            }
            return l;
        }

        // convenience
        public LevelBuilder colsAndRows(int c, int r) {
            cols(c);
            rows(r);
            return this;
        }
    }
}
