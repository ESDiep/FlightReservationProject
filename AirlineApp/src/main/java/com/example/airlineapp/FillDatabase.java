package com.example.airlineapp;




import java.text.ParseException;
import java.sql.SQLException;
public class FillDatabase {
    SQLExecutor executor;

    FillDatabase() throws SQLException {
        executor= new SQLExecutor();
    }

    public void createDB(){
        executor.executeFile("AirlineApp/src/main/resources/sqlScript/AirlineApp.sql");
    }
    public void fillDatabase() throws SQLException, ParseException {
        executor.generateAndInsertFlights(100);
    }

}
