package blatt3;

import org.example.coll.tuple.Tuple;
import org.example.ub3.three.ProbeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProbeManagerTest {
    private ProbeManager probeManager;

    @BeforeEach
    public void setUp() {
        probeManager = new ProbeManager(3); // Set max values to 3 for testing
    }

    @Test
    public void testAdd() {
        probeManager.add(LocalTime.of(10, 0), 25);
        probeManager.add(LocalTime.of(11, 0), 28);
        probeManager.add(LocalTime.of(12, 0), 30);
        assertEquals(3, probeManager.getAll().size());

        // Adding more values should remove the oldest one
        probeManager.add(LocalTime.of(13, 0), 32);
        assertEquals(3, probeManager.getAll().size());

        // Check if the oldest value is removed
        boolean containsOldValue = false;
        for (Tuple<LocalTime, Integer> tuple : probeManager.getAll()) {
            if (tuple.getFirst().equals(LocalTime.of(10, 0))) {
                containsOldValue = true;
                break;
            }
        }
        assertFalse(containsOldValue);
    }

    @Test
    public void testGetAverageTemperature() {
        probeManager.add(LocalTime.of(10, 0), 25);
        probeManager.add(LocalTime.of(11, 0), 28);
        probeManager.add(LocalTime.of(12, 0), 30);
        assertEquals(28.0, probeManager.getAverageTemperature(LocalTime.of(10, 0), LocalTime.of(12, 0)));
    }

    @Test
    public void testClear() {
        probeManager.add(LocalTime.of(10, 0), 25);
        probeManager.add(LocalTime.of(11, 0), 28);
        probeManager.add(LocalTime.of(12, 0), 30);
        probeManager.clear();
        assertEquals(0, probeManager.getAll().size());
    }
}
