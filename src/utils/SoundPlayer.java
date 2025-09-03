package utils;

// Simulated external mp3 player class -- imagine it's from a library we must adapt
class ExternalMP3Player {
    public void playFile(String file) {
        // In real life you'd call library code. We simulate with print.
        System.out.println("[ExternalMP3Player] Playing: " + file);
    }
}

// Adapter wrapper used by our game
public class SoundPlayer {
    private ExternalMP3Player player = new ExternalMP3Player();

    public void playSound(String event) {
        // map events to actual files
        String file;
        switch (event) {
            case "brick_hit":
                file = "sounds/hit.mp3";
                break;
            case "paddle_hit":
                file = "sounds/paddle.mp3";
                break;
            default:
                file = "sounds/none.mp3";
                break;
        }
        player.playFile(file);
    }
}
