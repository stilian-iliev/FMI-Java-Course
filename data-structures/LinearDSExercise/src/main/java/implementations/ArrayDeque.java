package implementations;

import interfaces.Deque;

import java.util.Iterator;

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
        addFirst(element);
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

    }

    @Override
    public void set(int index, E element) {

    }

    @Override
    public E peek() {
        return get(0);
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E get(int index) {
        ensureIndexInBounds(index);
        return getAtRealIndex(this.head + index);
    }

    @SuppressWarnings("unchecked")
    private E getAtRealIndex(int index) {
        return (E)this.elements[index];
    }

    private void ensureIndexInBounds(int index) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s.",index, size()));
    }

    @Override
    public E get(Object object) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E remove(Object object) {
        return null;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();
        E element = get(0);
        this.elements[head++] = null;
        return element;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();
        E element = get(size());
        this.elements[tail--] = null;
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
                return i < size() ;
            }

            @Override
            public E next() {
                return get(i++);
            }
        };
    }
}
