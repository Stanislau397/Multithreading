package edu.epam.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RiverFerry {

    public static final Logger logger = LogManager.getLogger(RiverFerry.class);
    private static final Lock locker = new ReentrantLock();
    private static final int FERRY_SQUARE_CAPACITY = 150;
    private static final int FERRY_WEIGHT_CAPACITY = 250;
    private static final int TIME_TO_TRANSPORT_CARS = 2;
    private static final int MIN_CAR_WEIGHT = 0;
    private static final int MAX_CAR_SQUARE = 0;
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean();
    private AtomicInteger currentCarWeight = new AtomicInteger();
    private AtomicInteger currentFerrySquare = new AtomicInteger();
    private static RiverFerry instance;

    private RiverFerry() {

    }

    public static RiverFerry getInstance() {
        if (!(INSTANCE_CREATED.get())) {
            locker.lock();
            if (instance == null) {
                instance = new RiverFerry();
                INSTANCE_CREATED.set(true);
            }
            locker.unlock();
        }
        return instance;
    }

    public void loadCar(Car car) {
        int carWeight = car.getWeight();
        int carSquare = car.getSquare();
        int secondsToSleep = 2;
        locker.lock();
        if (isFerryFull(car)) {
            setCurrentFerrySquare(carSquare);
            setCurrentCarWeight(carWeight);
        } else {
            logger.log(Level.WARN, "River ferry is full. Remaining cars are waiting.");
            try {
                TimeUnit.SECONDS.sleep(secondsToSleep);
                transportCars();
                TimeUnit.SECONDS.sleep(secondsToSleep);
                loadCar(car);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        locker.unlock();

    }

    public void transportCars() {
        try {
            TimeUnit.SECONDS.sleep(TIME_TO_TRANSPORT_CARS);
            setCurrentCarWeight(MIN_CAR_WEIGHT);
            setCurrentFerrySquare(MAX_CAR_SQUARE);
            TimeUnit.SECONDS.sleep(TIME_TO_TRANSPORT_CARS);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    public boolean isFerryFull(Car car) {
        int totalWeight = car.getWeight() + getCurrentCarWeight();
        int totalSquare = car.getSquare() + getCurrentFerrySquare();
        return (totalWeight <= FERRY_WEIGHT_CAPACITY) && (totalSquare <= FERRY_SQUARE_CAPACITY);
    }

    public int getCurrentCarWeight() {
        return currentCarWeight.intValue();
    }

    public void setCurrentCarWeight(int currentCarWeight) {
        this.currentCarWeight = new AtomicInteger(currentCarWeight);
    }

    public int getCurrentFerrySquare() {
        return currentFerrySquare.intValue();
    }

    public void setCurrentFerrySquare(int currentFerrySquare) {
        this.currentFerrySquare = new AtomicInteger(currentFerrySquare);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RiverFerry that = (RiverFerry) o;

        if (currentCarWeight != null ? !currentCarWeight.equals(that.currentCarWeight) : that.currentCarWeight != null)
            return false;
        return currentFerrySquare != null ? currentFerrySquare.equals(that.currentFerrySquare) : that.currentFerrySquare == null;
    }

    @Override
    public int hashCode() {
        int result = currentCarWeight != null ? currentCarWeight.hashCode() : 0;
        result = 31 * result + (currentFerrySquare != null ? currentFerrySquare.hashCode() : 0);
        return result;
    }
}
