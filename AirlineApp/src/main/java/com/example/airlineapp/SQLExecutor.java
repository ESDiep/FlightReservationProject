package com.example.airlineapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.ParseException;

public class SQLExecutor {

    static DatabaseConnection connectionObj;
    Connection connection;
    SQLExecutor() throws SQLException {
        connectionObj = new DatabaseConnection();
        connection = connectionObj.getDBConnection();
    };

    public void executeFile(String path)
    {
        try (FileReader reader = new FileReader(path);
             // Wrap the FileReader in a BufferedReader for
             // efficient reading.
             BufferedReader bufferedReader = new BufferedReader(reader);
             Statement statement = connection.createStatement();) {


            System.out.println("Executing commands at : "
                    + path);

            StringBuilder builder = new StringBuilder();

            String line;
            int lineNumber = 0;
            int count = 0;

            // Read lines from the SQL file until the end of the
            // file is reached.
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber += 1;
                line = line.trim();

                // Skip empty lines and single-line comments.
                if (line.isEmpty() || line.startsWith("--"))
                    continue;

                builder.append(line);
                // If the line ends with a semicolon, it
                // indicates the end of an SQL command.
                if (line.endsWith(";"))
                    try {
                        // Execute the SQL command
                        statement.execute(builder.toString());
                        // Print a success message along with
                        // the first 15 characters of the
                        // executed command.
                        System.out.println(
                                ++count
                                        + " Command successfully executed : "
                                        + builder.substring(
                                        0,
                                        Math.min(builder.length(), 15))
                                        + "...");
                        builder.setLength(0);
                    }
                    catch (SQLException e) {
                        // If an SQLException occurs during
                        // execution, print an error message and
                        // stop further execution.
                        System.err.println(
                                "At line " + lineNumber + " : "
                                        + e.getMessage() + "\n");
                        return;
                    }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateAndInsertFlights(int numberOfFlights) throws SQLException, ParseException {

            String insertQuery = "INSERT INTO flights (flightID, depart_time, arrival_time, origin, destination, flightdate, aircraft) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                for (int i = 1; i <= numberOfFlights; i++) {
                    String flightID = "FL" + String.format("%03d", i);
                    String departTime = generateRandomTime();
                    String arrivalTime = generateRandomTimeAfter(departTime,1);
                    String origin = generateRandomCanadianCity();
                    String destination = generateRandomCanadianCityOtherThan(origin);
                    String flightDate = generateRandomDate();
                    String aircraft = generateRandomAircraft();

                    preparedStatement.setString(1, flightID);
                    preparedStatement.setString(2, departTime);
                    preparedStatement.setString(3, arrivalTime);
                    preparedStatement.setString(4, origin);
                    preparedStatement.setString(5, destination);
                    preparedStatement.setString(6, flightDate);
                    preparedStatement.setString(7, aircraft);

                    preparedStatement.executeUpdate();
                }
            }
    }


    private static String generateRandomCanadianCity() {
        String[] canadianCities = {"Toronto", "Montreal", "Vancouver", "Calgary", "Edmonton", "Ottawa", "Quebec City"};
        return canadianCities[(int) (Math.random() * canadianCities.length)];
    }

    private static String generateRandomCanadianCityOtherThan(String city) {
        String[] canadianCities = {"Toronto", "Montreal", "Vancouver", "Calgary", "Edmonton", "Ottawa", "Quebec City"};
        String randomCity;
        do {
            randomCity = canadianCities[(int) (Math.random() * canadianCities.length)];
        } while (randomCity.equals(city));
        return randomCity;
    }

    private static String generateRandomAircraft() {
        String[] aircraftTypes = {"Boeing 747", "Airbus A321"};
        return aircraftTypes[(int) (Math.random() * aircraftTypes.length)];
    }

    private static String generateRandomTime() {
        int hour = (int) (Math.random() * 24);
        int minute = (int) (Math.random() * 60);
        return String.format("%02d:%02d", hour, minute);
    }

    private static String generateRandomTimeAfter(String startTime, int hoursToAdd) {
        String[] parts = startTime.split(":");
        int startHour = Integer.parseInt(parts[0]);
        int startMinute = Integer.parseInt(parts[1]);

        int totalMinutes = startHour * 60 + startMinute + hoursToAdd * 60;

        int arrivalHour = totalMinutes / 60;
        int arrivalMinute = totalMinutes % 60;

        return String.format("%02d:%02d", arrivalHour, arrivalMinute);
    }
    private static String generateRandomDate(){
        String date="2023-12-";
        int day = (int) (Math.random() * (31 - 25 + 1)) + 25;
        String randomDay = String.valueOf(day);
        date+=randomDay;
        return date;
    }

}
