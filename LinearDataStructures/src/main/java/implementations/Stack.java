package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> top;
    private int size;
    private static class Node<E> {
        private E value;
        private Node<E> previous;

        public Node(E value) {
            this.value = value;
        }
    }
    @Override
    public void push(E element) {
        if (top == null) {
            top = new Node<>(element);
        } else {
            Node<E> newNode = new Node<>(element);
            newNode.previous = top;
            top = newNode;
        }
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new IllegalStateException();
        E tmp = top.value;
        top = top.previous;
        size--;
        return tmp;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new IllegalStateException();
        return top.value;
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
            private Node<E> current = top;
            @Override
            public boolean hasNext() {
                return current.previous != null;
            }

            @Override
            public E next() {
                E tmp = current.value;
                current = current.previous;
                return tmp;
            }
        };
    }
}
