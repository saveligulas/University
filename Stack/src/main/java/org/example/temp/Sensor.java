package org.example.temp;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Sensor extends Observable implements Observer, Runnable {
    private boolean _active = true;

    public void run() {
        Random rand = new Random();
        while (_active) {
            try {
                Thread.sleep(rand.nextInt(1000, 25000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            double temp = rand.nextDouble(-255, 80);
            Gateway._COUNTER.incrementAndGet();
            setChanged();
            notifyObservers(temp);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        _active = false;
    }
}
