package blatt3;

public class TrolleyTest {
//    @Test
//    public void testMoveTo() {
//        TrainNetwork network = new TrainNetwork(5, 5);
//        network.fillWithRandomProducts();
//        Point initialPosition = new Point(0, 0);
//        Trolley trolley = new Trolley(network, initialPosition, 10);
//
//        // Move the trolley to a different point
//        Point targetPosition = new Point(2, 2);
//        trolley.moveTo(targetPosition);
//
//        assertEquals(targetPosition, trolley.getPosition());
//    }
//
//    @Test
//    public void testMoveToWithProduct() {
//        TrainNetwork network = new TrainNetwork(5, 5);
//        Point initialPosition = new Point(0, 0);
//        Product product = new Product(ProductType.IRON);
//        network.setPosition(initialPosition, product);
//
//        Trolley trolley = new Trolley(network, initialPosition, 10);
//        TrainJob job = new TrainJob(ProductType.IRON, 1);
//        Point targetPosition = new Point(2, 0);
//
//        trolley.moveTo(targetPosition, job);
//
//        assertTrue(trolley.getInventory().contains(product));
//        assertFalse(network.getProducts(targetPosition).isPresent());
//    }
//
//    @Test
//    public void testJobTargetReached() {
//        TrainNetwork network = new TrainNetwork(5, 5);
//        Point pos = new Point(0, 0);
//        Product product = new Product(ProductType.CLOTH);
//        for (int i = 0; i < 5; i++) {
//            network.setPosition(pos, product);
//            pos.addVector(new Point(0, 1));
//        }
//
//        Trolley trolley = new Trolley(network, new Point(0, 0), 10);
//        TrainJob job = new TrainJob(ProductType.CLOTH, 2);
//
//        trolley.moveTo(new Point(0, 4), job);
//
//        assertTrue(trolley.getInventory().contains(product));
//        assertEquals(2, trolley.getInventory().size());
//        assertTrue(network.getProducts(new Point(0, 4)).isPresent());
//    }
//
//    @Test
//    public void testNetworkUpdate() {
//        TrainNetwork network = new TrainNetwork(5, 5);
//        Point initialPosition = new Point(0, 0);
//        Product product = new Product(ProductType.CLOTH);
//        network.setPosition(initialPosition, product);
//
//        Trolley trolley = new Trolley(network, initialPosition, 10);
//        TrainJob job = new TrainJob(ProductType.CLOTH, 1);
//        Point targetPosition = new Point(2, 0);
//
//        trolley.moveTo(targetPosition, job);
//
//        assertFalse(network.getProducts(initialPosition).isPresent());
//    }
}
