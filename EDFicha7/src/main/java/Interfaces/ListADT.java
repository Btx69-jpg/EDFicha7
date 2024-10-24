package Interfaces;

import java.util.Iterator;

public interface ListADT<T> extends Iterable<T>{

    /**
     * Remove the first element of the list
     * */
    public T removeFirst();

    /**
     * Remove the last element of the list
     * */
    public T removeLast();

    /**
     * Remove a determined element of the list
     * @param element element to remove
     * */
    public T remove(T element);

    /**
     * @return the first object of the list
     * */
    public T first();

    /**
     * @return the last object of the list
     * */
    public T last();

    /**
     * Returns true if this list contains the specified target
     * element.
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     */
    public boolean contains(T target);

    /**
     * @return true if is empty and false if it isn't
     * */
    public boolean isEmpty();

    /**
     * @return the size of the list
     * */
    public int size();

    /**
     * @return the list iterator
     * */
    public Iterator<T> iterator();

    /**
     * @return a string with all elements of the list
     * */
    @Override
    public String toString();
}
