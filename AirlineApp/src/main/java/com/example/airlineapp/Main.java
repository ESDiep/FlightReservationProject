package com.example.airlineapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
	Parent root= FXMLLoader.load(getClass().getResource("search-flight.fxml"));
	stage.setTitle("Hello! Welcome to the Airline App!");
	stage.setScene(new Scene(root));
	stage.show();
	stage.toFront();
    }

    public static void main(String[] args) throws SQLException, ParseException {
	//This following 3 lines of code together with FillDatabase.java and SQLExecutor.java are used for
	//automatically randomly generating 100 flights between 2023-12-25 and 2023-12-31 between 3 cities
	//calgary toronto montreal
	//if you don't want to overwrite your data every time the system is launched, just disable/comment-out the
	//following 3 lines of code
	FillDatabase newDataBase=new FillDatabase();
	newDataBase.createDB();
	newDataBase.fillDatabase();

	//start the Airline APP
	launch();
    }
}