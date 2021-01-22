package edu.epam.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RiverFerry {

    public static final Logger logger = LogManager.getLogger(RiverFerry.class);
    private static final Lock locker = new ReentrantLock();
    private static final int FERRY_SQUARE_CAPACITY = 55;
    private static final int FERRY_WEIGHT_CAPACITY = 250;
    private static final int TIME_TO_TRANSPORT_CARS = 2;
    private static final int MIN_CAR_WEIGHT = 0;
    private static final int MAX_CAR_SQUARE = 0;
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean();
    private AtomicInteger currentCarWeight = new AtomicInteger();
    private AtomicInteger currentFerrySquare = new AtomicInteger();
    private static RiverFerry instance;
    private Condition condition = locker.newCondition();

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
        locker.lock();
        int carWeight = car.getWeight();
        int carSquare = car.getSquare();
        if (isFerryFull(car)) {
                setCurrentFerrySquare(carSquare);
                setCurrentCarWeight(carWeight);
        } else {
            logger.log(Level.WARN, "River ferry is full.");
            try {
                TimeUnit.SECONDS.sleep(2);
                transportCars();
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
}
