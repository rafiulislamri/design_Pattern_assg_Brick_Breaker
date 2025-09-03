package patterns;

public class ScoreBoard implements Observer {
    private int score = 0;
    private String text;

    @Override
    public void update(int score) {
        this.score = score;
        this.text = "Score updated: " + score;
        // could log or show a popup; UI reads getText()
    }

    public String getText() {
        return text;
    }
}
