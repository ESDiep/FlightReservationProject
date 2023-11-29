package com.example.airlineapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//	FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
//	Scene scene = new Scene(fxmlLoader.load());
//	stage.setTitle("Hello!");
//	stage.setScene(scene);
//	stage.show();
//	stage.toFront();

//	Parent root= FXMLLoader.load(getClass().getResource("login-view.fxml"));
	Parent root= FXMLLoader.load(getClass().getResource("search-flight.fxml"));
	stage.setTitle("Hello!");
	stage.setScene(new Scene(root));
	stage.show();
	stage.toFront();
    }

    public static void main(String[] args) {
	launch();
    }
}