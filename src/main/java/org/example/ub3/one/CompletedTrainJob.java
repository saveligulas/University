package org.example.ub3.one;

import org.example.coll.MyDictionary;
import org.example.ub1.rect.Point;
import org.example.ub3.one.pro.Product;

import java.util.Optional;

public record CompletedTrainJob(MyDictionary<Point, Optional<Product>> pathMap) {
}
