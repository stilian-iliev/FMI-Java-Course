package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private final int INITIAL_CAPACITY = 4;
    private Object[] arr;
    private int size;


    public ArrayList() {
        arr = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(E element) {
        return add(size, element);
    }

    @Override
    public boolean add(int index, E element) {
        if (size == arr.length) {
            arr = grow();
        }
        ensureInBounds(index, size);
        for (int i = size-1; i >= index; i--) {
            arr[i+1] = arr[i];
        }
        size++;
        arr[index] = element;
        return true;
    }

    private Object[] grow() {
        return Arrays.copyOf(arr, arr.length * 2);
    }

    @Override
    public E get(int index) {
        ensureInBounds(index, size - 1);
        return (E) arr[index];
    }

    private void ensureInBounds(int index, int size) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    }

    @Override
    public E set(int index, E element) {
        ensureInBounds(index, size - 1);
        E tmp = (E) arr[index];
        arr[index] = element;
        return tmp;
    }

    @Override
    public E remove(int index) {
        ensureInBounds(index, size - 1);
        E tmp = (E) arr[index];
        for (int i = index; i <= size; i++) {
            arr[i] = arr[i+1];
        }
        size--;
        return tmp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(element)) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(element)) return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return (E) arr[index++];
            }
        };
    }
}
