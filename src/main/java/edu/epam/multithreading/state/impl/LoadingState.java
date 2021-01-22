package edu.epam.multithreading.state.impl;

import edu.epam.multithreading.entity.Car;
import edu.epam.multithreading.entity.RiverFerry;
import edu.epam.multithreading.state.CarState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class LoadingState implements CarState<Car> {

    private static final Logger logger = LogManager.getLogger(LoadingState.class);

    @Override
    public void load(Car car) {
        RiverFerry riverFerry = RiverFerry.getInstance();
        double carId = car.getCarId();
        try {
            TimeUnit.SECONDS.sleep(1);
            riverFerry.loadCar(car);
            logger.log(Level.INFO, carId + " has been loaded on ferry river");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        car.setState(new UnloadingState());
    }

    @Override
    public void unload(Car car) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void arrive(Car car) {
        throw new UnsupportedOperationException();
    }
}
