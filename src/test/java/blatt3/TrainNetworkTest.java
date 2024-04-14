package blatt3;

import org.example.ub1.rect.Point;
import org.example.ub3.one.TrainNetwork;
import org.example.ub3.one.pro.ProductType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TrainNetworkTest {

    @Test
    public void testGetProduct() {
        TrainNetwork trainNetwork = new TrainNetwork(5, 5);
        trainNetwork.fillWithRandomProducts();

        Point insidePoint = new Point(2, 3);
        Point outsidePoint = new Point(6, 6);
        trainNetwork.setPosition(new Point(2, 3), new Product(ProductType.IRON));

        assertTrue(trainNetwork.getProducts(insidePoint).isPresent());
        assertFalse(trainNetwork.getProducts(outsidePoint).isPresent());
    }

    @Test
    public void testClearPosition() {
        TrainNetwork trainNetwork = new TrainNetwork(5, 5);
        trainNetwork.fillWithRandomProducts();

        Point point = new Point(2, 3);
        trainNetwork.clearPosition(point);

        assertFalse(trainNetwork.getProducts(point).isPresent());
    }

    @Test
    public void testFillWithRandomProducts() {
        TrainNetwork trainNetwork = new TrainNetwork(5, 5);
        trainNetwork.fillWithRandomProducts();

        int productCount = 0;
        for (int row = 0; row < trainNetwork.DIMENSIONS.getVerticalLength() + 1; row++) {
            for (int col = 0; col < trainNetwork.DIMENSIONS.getHorizontalLength() + 1; col++) {
                Point p = new Point(row, col);
                if (trainNetwork.getProducts(p).isPresent()) {
                    productCount++;
                }
            }
        }

        assertTrue(productCount > 0);
    }

    @Test
    public void testSetPosition() {
        TrainNetwork trainNetwork = new TrainNetwork(5, 5);
        Point position = new Point(2, 3); // Assuming we set a product at position (2, 3)
        Product product = new Product(ProductType.IRON); // Assuming we set an IRON product at position (2, 3)

        // Set the product at the position
        trainNetwork.setPosition(position, product);

        // Retrieve the product from the same position
        Optional<Product> retrievedProduct = trainNetwork.getProducts(position);

        // Assert that the retrieved product is present and matches the product we set
        assertTrue(retrievedProduct.isPresent());
        assertEquals(product, retrievedProduct.get());
    }
}
