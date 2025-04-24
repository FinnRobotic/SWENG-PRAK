package mm.MVC.util;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private List<Observer> observers = new ArrayList<>();


    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void clearObservers() {
        observers.clear();
    }

    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }
}


