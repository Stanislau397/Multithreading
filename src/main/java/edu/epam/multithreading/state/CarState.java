package edu.epam.multithreading.state;

public interface CarState<T> {

    void load(T t);
    void unload(T t);
    void arrive(T t);
}
