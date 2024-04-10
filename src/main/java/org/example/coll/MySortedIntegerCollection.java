package org.example.coll;

public class MySortedIntegerCollection extends MyCollection<Integer> {
    public int getIndexWhenNumberLarger(int number) {
        for (int i = 0; i < _pointer; i++) {
            if (get(i) >= number) {
                return i;
            }
        }
        return size() - 1;
    }

    public Integer[] toArray() {
        return super.toArray(Integer.class);
    }


    public MyCollection<Integer> subList(int start, int end) {
        return super.subList(start, end);
    }

    @Override
    public void remove(Integer e) {
        for (int i = 0; i < _pointer; i++) {
            if (_data[i] == e) {
                if (i != _pointer - 1) {
                    for (int j = i; j < _pointer - 1; j++) {
                        _data[j] = _data[j + 1];
                    }
                }
                _pointer--;
            }
        }
    }
}
