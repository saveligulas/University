package blatt1;

import org.example.ub1.cheese.CheeseHoleFinder;
import org.example.ub1.cheese.MyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheeseHoleTest {
    private CheeseHoleFinder _finder;
    private char[][] _grid;
    private MyCollection<Integer> _result;

    @BeforeEach
    public void setupFinder() {
        _finder.clear();
        _grid = null;
        _result = null;
    }

    private void find() {
        _result = _finder.getHoles(_grid);
    }

    private int getHoles() {
        return _result.size();
    }

    private int getLargestHole() {
        int max = 0;
        for (Integer hole : _result) {
            if (hole > max) {
                max = hole;
            }
        }
        return max;
    }

    @Test
    public void testGrid() {
        _grid = new char[][] {
                {'+', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '+'},
                {'|', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', ' ', ' ', '|'},
                {'|', ' ', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'+', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '+'}
        };
    }
}
