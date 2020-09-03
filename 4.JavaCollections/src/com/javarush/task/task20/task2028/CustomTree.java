package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("Sergey's tree");
    }

    @Override
    public boolean add(String s) {
        Queue<Entry<String>> treeValues = new LinkedList<>();
        treeValues.add(root);

        while (!treeValues.isEmpty()) {
            Entry<String> currVal = treeValues.poll();
            currVal.checkChildren();

            if (currVal.isAvailableToAddChildren()) {
                if (currVal.availableToAddLeftChildren) {
                    currVal.leftChild = new Entry<>(s);
                    currVal.leftChild.parent = currVal;
                    return true;
                } else if (currVal.availableToAddRightChildren) {
                    currVal.rightChild = new Entry<>(s);
                    currVal.rightChild.parent = currVal;
                    return true;
                }
            } else {
                if (currVal.leftChild != null) {
                    treeValues.offer(currVal.leftChild);
                }
                if (currVal.rightChild != null) {
                    treeValues.offer(currVal.rightChild);
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        Queue<Entry<String>> treeValues = new LinkedList<>();
        treeValues.add(root);
        int size = 0;

        while (!treeValues.isEmpty()) {
            Entry<String> currVal = treeValues.poll();
            size++;

            if (currVal.leftChild != null)
                treeValues.offer(currVal.leftChild);
            if (currVal.rightChild != null)
                treeValues.offer(currVal.rightChild);
        }

        return size - 1;
    }


    public String getParent(String s) {
        Queue<Entry<String>> treeValues = new LinkedList<>();
        treeValues.add(root);

        while (!treeValues.isEmpty()) {
            Entry<String> currVal = treeValues.poll();

            if (currVal.elementName.equals(s)) {
                return currVal.parent.elementName;
            } else {
                if (currVal.leftChild != null)
                    treeValues.offer(currVal.leftChild);
                if (currVal.rightChild != null)
                    treeValues.offer(currVal.rightChild);
            }
        }

        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String))
            throw new UnsupportedOperationException();

        String toDel = (String) o;
        Queue<Entry<String>> treeValues = new LinkedList<>();
        treeValues.add(root);

        while (!treeValues.isEmpty()) {
            Entry<String> currentNode = treeValues.poll();

            if (currentNode.leftChild != null) {
                if (currentNode.leftChild.elementName.equals(toDel)){
                    currentNode.leftChild = null;
                    currentNode.availableToAddLeftChildren = true;
                    return true;
                }

                treeValues.offer(currentNode.leftChild);
            }
            if (currentNode.rightChild != null) {
                if (currentNode.rightChild.elementName.equals(toDel)){
                    currentNode.rightChild = null;
                    currentNode.availableToAddRightChildren = true;
                    return true;
                }

                treeValues.offer(currentNode.rightChild);
            }
        }

        return false;
    }

    static class Entry<T> implements Serializable {
        String elementName;

        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;

        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        void checkChildren() {
            if (leftChild != null)
                availableToAddLeftChildren = false;
            if (rightChild != null)
                availableToAddRightChildren = false;
        }
    }


    // UNSUPPORTED
    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
