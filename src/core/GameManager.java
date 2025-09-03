package core;

import java.util.ArrayList;
import java.util.List;
import patterns.Observer;
import patterns.Subject;

public class GameManager implements Subject {
    private static GameManager instance;
    private int score = 0;
    private List<Observer> observers = new ArrayList<>();

    private GameManager() {
    }

    public static GameManager getInstance() {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    public void addScore(int p) {
        score += p;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        score = 0;
    }

    // Subject methods
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(score);
        }
    }
}
