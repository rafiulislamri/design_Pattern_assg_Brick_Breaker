package patterns;

import entities.*;

public class BrickFactory {
    public static Brick createBrick(String type) {
        switch (type.toLowerCase()) {
            case "normal":
                return new NormalBrick();
            case "strong":
                return new StrongBrick();
            case "bonus":
                return new BonusBrick();
            default:
                return new NormalBrick();
        }
    }
}
