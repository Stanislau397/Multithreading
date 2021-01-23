package edu.epam.multithreading.main;

import edu.epam.multithreading.entity.Car;
import edu.epam.multithreading.exception.EmptyFileException;
import edu.epam.multithreading.parser.CarParser;
import edu.epam.multithreading.reader.DataReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        List<String> data = new ArrayList<>();
        try {
            data = dataReader.readDataFromFile("data/file");
        } catch (EmptyFileException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CarParser carParser = new CarParser();
        List<Car> cars = carParser.parseCars(data);
        for (Car car : cars) {
            new Thread(car).start();
        }
    }
}

