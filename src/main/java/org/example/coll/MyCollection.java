package org.example.coll;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyCollection<T> implements Iterable<T> {
    protected Object[] _data;
    protected int _pointer;

    public MyCollection() {
        this(1);
    }

    public MyCollection(int size) {
        _data = new Object[size];
        _pointer = 0;
    }

    public MyCollection(MyCollection<T> collection) {
        _data = collection._data;
        _pointer = collection._pointer;
    }

    public MyCollection(T[] array) {
        _data = array.clone();
        _pointer = array.length;
    }

    protected void increaseSize() {
        int size;
        if (_data.length < 100) {
            size = _data.length * 2;
        } else {
            size = _data.length + (int) Math.sqrt(_data.length) + 1;
        }
        Object[] newData = new Object[size];
        System.arraycopy(_data, 0, newData, 0, _data.length);
        _data = newData;
    }

    public void increaseSize(int size) {
        Object[] newData = new Object[size + _pointer];
        if (newData.length < _data.length) {
            return;
        }
        System.arraycopy(_data, 0, newData, 0, _data.length);
        _data = newData;
    }

    public void add(T e) {
        if (_pointer == _data.length) {
            increaseSize();
        }
        _data[_pointer] = e;
        _pointer++;
    }

    public void add(MyCollection<T> collection) {
        for (T e : collection) {
            this.add(e);
        }
    }

    @SuppressWarnings({"all"})
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        try {
            return (T) _data[index];
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings({"unchecked"})
    public T get(T e) {
        for (int i = 0; i < _pointer; i++) {
            if (((T) _data[i]).equals(e)) {
                return get(i);
            }
        }
        return null;
    }

    public void set(int index, T value) {
        _data[index] = value;
    }

    public void fill(int amount, T value) {
        for (int i = 0; i < amount; i++) {
            add(value);
        }
    }

    public int size() {
        return _pointer;
    }

    public void removeIndex(int index) {
        if (index < 0 || index >= _pointer) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index; i < _pointer - 1; i++) {
            _data[i] = _data[i + 1];
        }
        _pointer--;
    }

    public void remove(T e) {
        for (int i = 0; i < _pointer; i++) {
            if (_data[i].equals(e)) {
                if (i != _pointer - 1) {
                    for (int j = i; j < _pointer - 1; j++) {
                        _data[j] = _data[j + 1];
                    }
                }
                _pointer--;
            }
        }
    }

    public void removeFromBehind(T e) {
        for (int i = _pointer - 1; i >= 0; i--) {
            if (_data[i].equals(e)) {
                for (int j = i; j < _pointer - 1; j++) {
                    _data[j] = _data[j + 1];
                }
                _pointer--;
            }
        }
    }

    public boolean contains(T e) {
        for (int i = 0; i < _pointer; i++) {
            if (_data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        _data = new Object[1];
        _pointer = 0;
    }

    public MyCollection<T> subList(int end) {
        return subList(0, end);
    }

    public MyCollection<T> subList(int start, int end) {
        if (start < 0 || end < 0) {
            throw new IllegalArgumentException("start must be larger than 0");
        }
        if (end < start) {
            throw new IllegalArgumentException("end cant be smaller than start");
        }
        MyCollection<T> result = new MyCollection<>();
        for (int i = start; i < end; i++) {
            result.add(get(i));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(Class<T> clazz) {
        T[] newArray = (T[]) Array.newInstance(clazz, _pointer);
        for (int i = 0; i < _pointer; i++) {
            newArray[i] = get(i);
        }
        return newArray;
    }

    public void reverse() {
        Object[] newArray = new Object[_pointer];
        int index = 0;
        for (int i = _pointer - 1; i >= 0; i--) {
            newArray[index] = get(i);
            index++;
        }
        _data = newArray;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < _pointer;
            }

            @Override
            public T next() {
                return get(currentIndex++);
            }
        };
    }
}
