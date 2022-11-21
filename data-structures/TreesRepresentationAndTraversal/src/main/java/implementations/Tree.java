package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... children) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            child.parent = this;
            this.children.add(child);
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        if (this.value == null) return result;

        Queue<Tree<E>> treesToTraverse = new ArrayDeque<>();
        treesToTraverse.add(this);

        while (!treesToTraverse.isEmpty()) {
            Tree<E> current = treesToTraverse.poll();
            result.add(current.value);
            treesToTraverse.addAll(current.children);
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        if (this.value == null) return result;
        dfsRecursive(this, result);
        return result;
    }

    private void dfsRecursive(Tree<E> node, List<E> result) {
        for (Tree<E> child : node.children) {
            dfsRecursive(child, result);
        }
        result.add(node.value);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> parent = find(this, parentKey);
        if (parent == null) throw new IllegalArgumentException();

        parent.children.add(child);
    }

    private Tree<E> find(Tree<E> node, E nodeKey) {
        if (node.value.equals(nodeKey)) return node;
        for (Tree<E> child : node.children) {
            Tree<E> search = find(child, nodeKey);
            if (search != null) return search;
        }
        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> nodeToRemove = find(this, nodeKey);
        if (nodeToRemove == null) throw new IllegalArgumentException();
        if (nodeToRemove.parent != null) {
            nodeToRemove.parent.children.remove(nodeToRemove);
        }
        nodeToRemove.value = null;
        nodeToRemove.children.forEach(c -> c.parent = null);
        nodeToRemove.children.clear();
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = find(this, firstKey);
        Tree<E> secondNode = find(this, secondKey);

        if (firstNode == null || secondNode == null) throw new IllegalArgumentException();

        if (firstNode.parent == null) {
            setRoot(secondNode);
            return;
        }
        if (secondNode.parent == null) {
            setRoot(firstNode);
            return;
        }

        int firstIndex = firstNode.parent.children.indexOf(firstNode);
        int secondIndex = secondNode.parent.children.indexOf(secondNode);

        firstNode.parent.children.set(firstIndex, secondNode);
        secondNode.parent.children.set(secondIndex, firstNode);
    }

    private void setRoot(Tree<E> node) {
        this.value = node.value;
        this.parent = null;
        this.children = node.children;
    }
}



