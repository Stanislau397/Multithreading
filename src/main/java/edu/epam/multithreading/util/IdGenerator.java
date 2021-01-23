package edu.epam.multithreading.util;

public class IdGenerator {

    public static final long MIN_ID = 1;
    public static final long MAX_ID = 100_000;
    private static long counter;

    public static long generateId() {
        counter = counter + 1;
        if (MIN_ID + counter >= MAX_ID) {
            counter = 0;
        }
        return counter;
    }
}
