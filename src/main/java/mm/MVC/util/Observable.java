package mm.MVC.util;

import java.util.ArrayList;
import java.util.List;


/**
 * A class representing an observable object, which maintains a list of observers
 * and notifies them of any changes or updates.
 */
public class Observable {

    private List<Observer> observers = new ArrayList<>();


    /**
     * Registers a new observer to be notified of updates.
     *
     * @param observer The observer to add.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes a previously registered observer.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Removes all registered observers.
     */
    public void clearObservers() {
        observers.clear();
    }


    /**
     * Notifies all registered observers by calling their update method.
     */
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }
}


