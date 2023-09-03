package adt;

public interface ListInterface<T> {

    boolean add(T newEntry);

    boolean add(int position, T newEntry);

    T remove(int position);

    void clear();

    boolean replace(int position, T newEntry);

    T get(int position);

    boolean contain(T entry);

    boolean isEmpty();

    boolean isFull();

    int size();

    void sort();
}
