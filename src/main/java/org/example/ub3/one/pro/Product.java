package org.example.ub3.one.pro;

import org.example.ub1.rect.Point;

public class Product {
    public final ProductType TYPE;

    public Product(ProductType type) {
        TYPE = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product product)) {
            return false;
        }
        return this.TYPE == product.TYPE;
    }
}
