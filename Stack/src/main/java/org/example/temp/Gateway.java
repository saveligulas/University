package org.example.temp;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Gateway extends Observable implements Observer {
    private final int _threads;
    private final List<Sensor> _sensors;
    private final Hub _hub;
    public static AtomicInteger _COUNTER;
    private int _maxTemps;

    public Gateway(int amount) {
        _threads = amount;
        _sensors = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Sensor sensor = new Sensor();
            sensor.addObserver(this);
            _sensors.add(sensor);
            addObserver(sensor);
        }
        _hub = new Hub();
        addObserver(_hub);
        _COUNTER = new AtomicInteger(0);
        _maxTemps = 6;
    }

    public void run(int amountOfTemps) {
        if (amountOfTemps < 5) {
            amountOfTemps = 5;
        }
        _maxTemps = amountOfTemps - 4;
        _COUNTER = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(_threads);

        // Submit sensor runnables to the thread pool
        for (Sensor sensor : _sensors) {
            executor.submit(sensor);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        _hub.update(o, arg);
        System.out.println(o + ": " + arg);
        System.out.println(_COUNTER);
        if (_COUNTER.get() == _maxTemps) {
            setChanged();
            notifyObservers();
        }
    }

    public Hub getHub() {
        return _hub;
    }
}
