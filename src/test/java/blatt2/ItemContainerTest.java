package blatt2;

import org.example.coll.MyCollection;
import org.example.ub2.container.Item;
import org.example.ub2.container.ItemContainer;
import org.example.ub2.container.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemContainerTest {
    private final Item PENCIL = new Item(ItemType.PENCIL, 1);
    private final Item LAPTOP = new Item(ItemType.LAPTOP, 15);
    private final Item BOTTLE = new Item(ItemType.BOTTLE, 3);

    @Test
    public void testIsFull() {
        ItemContainer container = new ItemContainer(2, 3);
        container.add(new Item(PENCIL));
        container.add(new Item(PENCIL));
        assertTrue(container.isFull());
        container.remove(new Item(PENCIL));
        container.add(new Item(BOTTLE));
        assertTrue(container.isFull());
    }

    @Test
    public void testAdd() {
        ItemContainer container = new ItemContainer(5, 17);
        container.add(new Item(LAPTOP));
        container.add(new Item(BOTTLE));
        assertEquals(1, container.size());
        container.add(new Item(PENCIL));
        container.add(new Item(PENCIL));
        assertTrue(container.isFull());
        assertEquals(3, container.size());
    }

    @Test
    public void testAddMyCollection() {
        MyCollection<Item> collection = new MyCollection<>();
    }
}
