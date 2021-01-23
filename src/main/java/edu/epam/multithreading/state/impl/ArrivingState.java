package edu.epam.multithreading.state.impl;

import edu.epam.multithreading.entity.Car;
import edu.epam.multithreading.state.CarState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ArrivingState implements CarState<Car> {

    private static final Logger logger = LogManager.getLogger(ArrivingState.class);

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
        int seconds = 1;
        logger.log(Level.INFO, "{} arrived to Ferry river", car);
        try {
            TimeUnit.SECONDS.sleep(seconds);
            car.setState(new LoadingState());
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}
