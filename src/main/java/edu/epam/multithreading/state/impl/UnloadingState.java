package edu.epam.multithreading.state.impl;

import edu.epam.multithreading.entity.Car;
import edu.epam.multithreading.entity.RiverFerry;
import edu.epam.multithreading.state.CarState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnloadingState implements CarState<Car> {

    private static final Logger logger = LogManager.getLogger(UnloadingState.class);

    @Override
    public void load(Car car) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unload(Car car) {
        RiverFerry riverFerry = RiverFerry.getInstance();
        riverFerry.transportCars();
        logger.log(Level.INFO, "{} has been unloaded from river ferry", car);
    }

    @Override
    public void arrive(Car car) {
        throw new UnsupportedOperationException();
    }
}
