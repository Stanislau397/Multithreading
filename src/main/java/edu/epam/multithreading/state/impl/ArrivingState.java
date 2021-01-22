package edu.epam.multithreading.state.impl;

import edu.epam.multithreading.entity.Car;
import edu.epam.multithreading.state.CarState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrivingState implements CarState<Car> {

    private static final Logger logger = LogManager.getLogger(ArrivingState.class);
    private Lock locker = new ReentrantLock();

    @Override
    public void load(Car car) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unload(Car car) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void arrive(Car car) {
        double carId = car.getCarId();
        logger.log(Level.INFO, "Car: " + carId + " arrived");
        try {
            TimeUnit.SECONDS.sleep(2);
            car.setState(new LoadingState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
