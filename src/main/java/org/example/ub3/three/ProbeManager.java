package org.example.ub3.three;

import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;
import org.example.coll.tuple.Tuple;
import java.time.LocalTime;

public class ProbeManager {
    private MyDictionary<LocalTime, Integer> _localTimeTemperatures;
    private int _maxValues;

    public ProbeManager(int max) {
        _localTimeTemperatures = new MyDictionary<>();
        _maxValues = max;
    }

    public void add(LocalTime localTime, Integer temp) {
        if (_localTimeTemperatures.size() < _maxValues || _localTimeTemperatures.getKeys().contains(localTime)) {
            _localTimeTemperatures.put(localTime, temp);
        } else {
            _localTimeTemperatures.remove(_localTimeTemperatures.getKeys().get(0));
        }
    }

    public double getAverageTemperature(LocalTime lower, LocalTime upper) {
        MyCollection<Integer> values = new MyCollection<>();
        for (LocalTime time : _localTimeTemperatures.getKeys()) {
            if (lower.isBefore(time) && upper.isAfter(time)) {
                values.add(_localTimeTemperatures.get(time));
            }
        }
        double result = 0;
        for (Integer value : values) {
            result += value;
        }
        return result / values.size();
    }

    public MyCollection<Tuple<LocalTime, Integer>> getAll() {
        MyCollection<Tuple<LocalTime, Integer>> result = new MyCollection<>();
        for (LocalTime LocalTime : _localTimeTemperatures.getKeys()) {
            result.add(new Tuple<>(LocalTime, _localTimeTemperatures.get(LocalTime)));
        }
        return result;
    }

    public void clear() {
        _localTimeTemperatures.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Tuple<LocalTime, Integer> tuple : getAll()) {
            sb.append(tuple).append("\n");
        }
        return sb.toString();
    }
}
