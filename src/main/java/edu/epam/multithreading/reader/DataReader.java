package edu.epam.multithreading.reader;

import edu.epam.multithreading.exception.EmptyFileException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {

    private static final Logger logger = LogManager.getLogger(DataReader.class);

        public List<String> readDataFromFile(String path) {
            List<String> linesList = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                linesList = br.lines().collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                logger.log(Level.ERROR, e.getMessage());
            } catch (IOException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
            return linesList;
        }
}
