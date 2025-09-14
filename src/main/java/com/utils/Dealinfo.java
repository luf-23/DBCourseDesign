package com.utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Dealinfo {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> void saveToFile(String filePath, List<T> objects) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T obj : objects) {
                writer.write(mapper.writeValueAsString(obj));
                writer.newLine();
            }
        }
    }
    public static <T> List<T> readFromFile(String filePath, Class<T> clazz) throws IOException {
        List<T> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T obj = mapper.readValue(line, clazz);
                objects.add(obj);
            }
        }
        return objects;
    }
    public static <T> void RefreshFile(String filePath, List<T> objects) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T obj : objects) {
                writer.write(mapper.writeValueAsString(obj));
                writer.newLine();
            }
        }
    }
}
