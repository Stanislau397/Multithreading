package edu.epam.multithreading.parser;

import edu.epam.multithreading.entity.Car;
import edu.epam.multithreading.entity.CarType;

import java.util.ArrayList;
import java.util.List;

public class CarParser {

    private static final String DELIMITER_REGEX = ", ";

    public List<Car> parseCars(List<String> lines) {
        List<Car> carsList = new ArrayList<>();
        for (String line : lines) {
            String[] cars = line.split(DELIMITER_REGEX);
            CarType type = CarType.valueOf(cars[0]);
            int weight = Integer.parseInt(cars[1]);
            int square = Integer.parseInt(cars[2]);
            Car car = new Car(type, weight, square);
            carsList.add(car);
        }
        return carsList;
    }
}
