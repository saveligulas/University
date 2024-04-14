package org.example.ub3.one;

import org.example.coll.MyCollection;
import org.example.coll.MyCollectionArray;
import org.example.coll.MyDictionary;
import org.example.ub1.rect.Point;

public class TrainManager {
    private final TrainNetwork _network;
    private final MyCollection<Trolley> _trolleys;
    private final MyDictionary<Point, TrainHub> _pointHubs;

    TrainManager(TrainNetwork network, MyCollection<Trolley> trolleys, MyDictionary<Point, TrainHub> pointHubs) {
        _network = network;
        _trolleys = trolleys;
        _pointHubs = pointHubs;
    }

    static class TrainHub {
        TrainHub() {
            //TODO
        }
    }
}
