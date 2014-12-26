
package com.github.tilastokeskus.matertis.core;

import java.util.HashSet;
import java.util.Observer;
import java.util.Set;

/**
 *
 * @author tilastokeskus
 */
public abstract class ObservableGameHandler extends AbstractGameHandler {
    
    private final Set<Observer> observers;

    public ObservableGameHandler(Game game, ScoreHandler scoreHandler) {
        super(game, scoreHandler);
        this.observers = new HashSet<>();
    }
    
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }
    
    public void notifyObservers() {
        this.notifyObservers(null);
    }
    
    public void notifyObservers(Object arg) {
        for (Observer observer : this.observers) {
            observer.update(null, arg);
        }
    }

}
