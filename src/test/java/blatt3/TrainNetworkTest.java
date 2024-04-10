package blatt3;

import org.example.ub1.rect.Point;
import org.example.ub3.one.TrainNetwork;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainNetworkTest {

    @Test
    public void testGetProduct() {
        TrainNetwork trainNetwork = new TrainNetwork(5, 5);
        trainNetwork.fillWithRandomProducts();

        Point insidePoint = new Point(2, 3);
        Point outsidePoint = new Point(6, 6);

        assertTrue(trainNetwork.getProduct(insidePoint).isPresent());
        assertFalse(trainNetwork.getProduct(outsidePoint).isPresent());
    }

    @Test
    public void testClearPosition() {
        TrainNetwork trainNetwork = new TrainNetwork(5, 5);
        trainNetwork.fillWithRandomProducts();

        Point point = new Point(2, 3);
        trainNetwork.clearPosition(point);

        assertFalse(trainNetwork.getProduct(point).isPresent());
    }

    @Test
    public void testFillWithRandomProducts() {
        TrainNetwork trainNetwork = new TrainNetwork(5, 5);
        trainNetwork.fillWithRandomProducts();

        int productCount = 0;
        for (int row = 0; row < trainNetwork.DIMENSIONS.getVerticalLength() + 1; row++) {
            for (int col = 0; col < trainNetwork.DIMENSIONS.getHorizontalLength() + 1; col++) {
                Point p = new Point(row, col);
                if (trainNetwork.getProduct(p).isPresent()) {
                    productCount++;
                }
            }
        }

        assertTrue(productCount > 0);
    }
}
