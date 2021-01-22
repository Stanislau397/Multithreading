package edu.epam.multithreading.main;
import edu.epam.multithreading.entity.Car;
import edu.epam.multithreading.exception.EmptyFileException;
import edu.epam.multithreading.parser.CarParser;
import edu.epam.multithreading.reader.DataReader;

import java.util.List;

public class Main {

    public static void main(String[] args) throws EmptyFileException {
        DataReader dataReader = new DataReader();
        List<String> data = dataReader.readDataFromFile("file");
        CarParser carParser = new CarParser();
        List<Car> cars = carParser.parseCars(data);
        for (Car car : cars) {
            new Thread(car).start();
        }
    }
}

