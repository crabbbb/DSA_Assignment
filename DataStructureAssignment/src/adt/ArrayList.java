package adt;

import entity.TutorialGroup;
import java.io.Serializable;

public class ArrayList<T> implements ListInterface<T>, Serializable {

    private T[] array;
    private int numberOfEntries;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int size) {
        numberOfEntries = 0;
        array = (T[]) new Object[size];
    }

    @Override
    public boolean add(T newEntry) {
        if (isFull()) {
            expandArray();
        }
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int position, T newEntry) {
        boolean yes = false;
        if (position >= 0 && position < numberOfEntries) {
            if (isFull()) {
                expandArray();
            }
            createRoom(position);
            array[position] = newEntry;
            numberOfEntries++;
            yes = true;
        }
        return yes;
    }

    @Override
    public T remove(int position) {
        T result = null;
        if (position >= 0 && position < numberOfEntries) {
            result = array[position];
            removeGap(position);
            numberOfEntries--;
        }
        return result;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int position, T newEntry) {
        boolean yes = false;
        if (position >= 0 && position < numberOfEntries) {
            array[position] = newEntry;
            yes = true;
        }
        return yes;
    }

    @Override
    public T get(int position) {
        T result = null;
        if (position >= 0 && position < numberOfEntries) {
            result = array[position];
        }
        return result;
    }

    @Override
    public boolean contain(T entry) {
        boolean yes = false;
        if (!isEmpty()) {
            for (int i = 0; i < numberOfEntries; i++) {
                if (entry.equals(array[i])) {
                    yes = true;
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (numberOfEntries == 0);
    }

    @Override
    public boolean isFull() {
        return (numberOfEntries == array.length);
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public void sort() {
        if (isEmpty()) {
            //Currently Don't have Data
        } else if (array[0] instanceof TutorialGroup) {
            TutorialGroup.sort(array, numberOfEntries);
        }
    }

    public void expandArray() {
        T[] oldArray = array;
        array = (T[]) new Object[oldArray.length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
        }
    }

    public void removeGap(int position) {
        for (int i = position; i < (numberOfEntries - 1); i++) {
            array[i] = array[i + 1];
        }
    }

    public void createRoom(int position) {
        for (int i = (numberOfEntries - 1); i >= position; i++) {
            array[i + 1] = array[i];
        }
    }

}
