package org.example.ub3.one;

import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;
import org.example.ub1.rect.Point;

import java.util.Optional;

public record CompletedTrainJob(MyDictionary<Point, Integer> pathMap) {
}
