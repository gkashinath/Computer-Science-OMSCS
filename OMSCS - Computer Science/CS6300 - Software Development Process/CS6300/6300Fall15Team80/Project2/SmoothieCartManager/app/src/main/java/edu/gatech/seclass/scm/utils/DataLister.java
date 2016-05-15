package edu.gatech.seclass.scm.utils;

/**
 * This interface is used to dynamically list items as they are being processed.
 */
public interface DataLister<T> {

    void push(T item);
}