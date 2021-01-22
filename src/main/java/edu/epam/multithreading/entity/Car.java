package edu.epam.multithreading.entity;

import edu.epam.multithreading.state.CarState;
import edu.epam.multithreading.state.impl.ArrivingState;
import edu.epam.multithreading.util.IdGenerator;

public class Car implements Runnable {

    private double carId;
    private CarType carType;
    private int weight;
    private int square;
    private CarState<Car> state;

    public Car(CarType type, int weight, int square) {
        this.carId = IdGenerator.generateId();
        this.weight = weight;
        this.square = square;
        this.carType = type;
        this.state = new ArrivingState();
    }

    public Car() {

    }

    @Override
    public void run() {
        state.arrive(this);
        state.load(this);
        state.unload(this);
    }

    public double getCarId() {
        return carId;
    }

    public void setCarId(double carId) {
        this.carId = carId;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public CarState<Car> getState() {
        return state;
    }

    public void setState(CarState<Car> state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (Double.compare(car.carId, carId) != 0) return false;
        if (weight != car.weight) return false;
        if (square != car.square) return false;
        if (carType != car.carType) return false;
        return state != null ? state.equals(car.state) : car.state == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(carId);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (carType != null ? carType.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + square;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(carType).append(" ").append("car");
        return sb.toString();
    }
}
