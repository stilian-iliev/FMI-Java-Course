package implementations;

import interfaces.Deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Deque<E> {
    private final int INITIAL_CAPACITY = 3;
    private Object[] elements;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.head = this.tail = INITIAL_CAPACITY / 2;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void offer(E element) {
        addLast(element);
    }

    @Override
    public void addFirst(E element) {
        if (head == 0) {
            this.elements = grow();
        }
        if (size() == 0)
            this.head++;
        this.elements[--this.head] = element;
        this.size++;
    }

    @Override
    public void addLast(E element) {
        if (tail == capacity() - 1) {
            this.elements = grow();
        }
        if (size() == 0)
            this.tail--;
        this.elements[++this.tail] = element;
        this.size++;
    }

    private Object[] grow() {
        Object[] newElements = new Object[capacity() * 2 + 1];
        int newHead = capacity() - (size() / 2);

        int index = newHead;
        for (Object element : this) {
            newElements[index++] = element;
        }
        this.head = newHead;
        this.tail = newHead + size() - 1;
        return newElements;
    }

    @Override
    public void push(E element) {
        addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        ensureIndexInBounds(index);
        if (size() == capacity()) this.elements = grow();

        int realIndex = this.head + index;

        if (index == 0){
            addFirst(element);
            return;
        } else if (index == size() - 1){
            addLast(element);
            return;
        }
        if (realIndex - this.head < this.tail - realIndex) {
            outwardShiftLeft(realIndex);
            this.head--;
        } else {
            outwardShiftRight(realIndex);
            this.tail++;
        }
        this.size++;
        this.elements[realIndex] = element;
    }

    private void outwardShiftLeft(int realIndex) {
        for (int i = this.head - 1; i < realIndex; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private void outwardShiftRight(int realIndex) {
        for (int i = this.tail; i >= realIndex; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    @Override
    public void set(int index, E element) {
        ensureIndexInBounds(index);
        this.elements[this.head + index] = element;
    }

    @Override
    public E peek() {
        return get(0);
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public E get(int index) {
        ensureIndexInBounds(index);
        return getAtRealIndex(this.head + index);
    }

    @SuppressWarnings("unchecked")
    private E getAtRealIndex(int index) {
        return (E) this.elements[index];
    }

    private void ensureIndexInBounds(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s.", index, size()));
    }

    @Override
    public E get(Object object) {
        E element = null;
        for (E e : this) {
            if (e.equals(object)){
                element = e;
                break;
            }
        }
        return element;
    }

    @Override
    public E remove(int index) {
        ensureIndexInBounds(index);
        int realIndex = this.head + index;
        E element = get(index);

        if (index == 0) {
            removeFirst();
            return element;
        } else if (index == size() - 1) {
            removeLast();
            return element;
        }
        if (realIndex - this.head < this.tail - realIndex) {
            inwardShiftRight(realIndex);
            removeFirst();
            this.head++;
        } else {
            inwardShiftLeft(realIndex);
            removeLast();
            this.tail--;
        }

        return element;
    }

    private void inwardShiftLeft(int realIndex) {
        for (int i = realIndex; i <= this.tail; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private void inwardShiftRight(int realIndex) {
        for (int i = realIndex; i > this.head; i--) {
            this.elements[i] = this.elements[i - 1];
        }
    }

    @Override
    public E remove(Object object) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(object)){
                index = i;
                break;
            }
        }
        if (index == -1) return null;
        return remove(index);
    }


    @Override
    public E removeFirst() {
        ensureNotEmpty();
        E element = get(0);
        this.elements[head++] = null;
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();
        E element = get(size()-1);
        this.elements[tail--] = null;
        size--;
        return element;
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        Object[] newElements = new Object[size()];

        for (int i = 0; i < newElements.length; i++) {
            newElements[i] = get(i);
        }
        this.elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    //2:56
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size();
            }

            @Override
            public E next() {
                return get(i++);
            }
        };
    }
}
