package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> bottom;
    private int size;
    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }
    @Override
    public void offer(E element) {
        if (bottom == null) {
            bottom = new Node<>(element);
        } else {
            Node<E> current = bottom;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(element);
        }
        size++;

    }

    @Override
    public E poll() {
        if (isEmpty()) throw new IllegalStateException();
        E tmp = bottom.value;
        bottom = bottom.next;
        size--;
        return tmp;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new IllegalStateException();
        return bottom.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = bottom;
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public E next() {
                E tmp = current.value;
                current = current.next;
                return tmp;
            }
        };
    }
}
