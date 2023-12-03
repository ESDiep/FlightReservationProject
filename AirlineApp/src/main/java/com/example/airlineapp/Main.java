package com.example.airlineapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {

    /**
     * This method is called when the application is launched.
     * It loads the FXML file for the GUI, sets the title of the stage,
     * creates a new scene with the loaded FXML file as the root,
     * shows the stage, and brings it to the front.
     *
     * @param stage the primary stage for this application
     * @throws IOException if an error occurs while loading the FXML file
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("search-flight.fxml"));
        stage.setTitle("Hello! Welcome to the Airline App!");
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    /**
     * The entry point of the application.
     * It calls the launch() method to start the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}