package org.example.ub3.one;

import org.example.coll.MyCollection;
import org.example.ub3.one.pro.Product;
import org.example.ub3.one.pro.ProductType;

public record TrainJob(MyCollection<ProductType> types, int target) {
}
