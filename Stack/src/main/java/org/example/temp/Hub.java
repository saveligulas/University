package org.example.temp;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Hub implements Observer {
    private final List<Double> _temps = new ArrayList<>();

    public void addTemp(double temp) {
        _temps.add(temp);
    }

    public List<Double> getTemps() {
        return _temps;
    }

    @Override
    public void update(Observable o, Object arg) {
        _temps.add((Double) arg);
    }
}
