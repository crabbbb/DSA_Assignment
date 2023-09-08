package adt;

import java.util.Iterator;

public interface List<T> {

    // utility
    void clear();

    int size();

    boolean isEmpty();

    int indexOf(T entry);

    boolean contains(T entry);

    Object[] asArray();

    void sort();

    //T getEntry(int position);
    boolean isFull();

    Iterator<T> iterator();

    // create
    boolean add(T newEntry);

    boolean add(int position, T newEntry);

    boolean addFirst(T newEntry);

    boolean addLast(T newEntry);

    // display
    T peekFirst();

    T peekLast();

    // boolean search(T entry);
    T get(int position);

    // remove
    T removeFirst();

    T removeLast();

    T remove(int position);

    T remove(T entry);

    // update
    boolean replace(int position, T newEntry);

}
