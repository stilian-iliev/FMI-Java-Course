package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    public SinglyLinkedList() {
        size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        if (!isEmpty()) newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E element) {
        if (isEmpty()) {
            head = new Node<>(element);
        } else {
            Node<E> current = head;

            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(element);
        }
        size++;
    }

    @Override
    public E removeFirst() {
        E tmp = head.value;
        head = head.next;
        size--;
        return tmp;
    }

    @Override
    public E removeLast() {
        Node<E> preCurrent = head;
        Node<E> current = head.next;
        while (current.next != null) {
            preCurrent = current;
            current = current.next;
        }
        E tmp = current.value;
        preCurrent.next = null;
        size--;
        return tmp;
    }

    @Override
    public E getFirst() {
        return head.value;
    }

    @Override
    public E getLast() {
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.value;
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
            private Node<E> current = head;
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
