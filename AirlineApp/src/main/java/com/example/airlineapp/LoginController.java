package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button button_login;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private Button button_signup;
    @FXML
    private Button button_goback;
    @FXML
    private Button button_logout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event,tf_username.getText(),tf_password.getText());
            }
        });

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"sign-up.fxml","Welcome!",null);
//                DBUtils.changeScene(event,"sign-up.fxml","Welcome!",null,null);
            }
        });

        button_goback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneBasic(event,"search-flight.fxml","Welcome!");
            }
        });

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Booking.isUserLoggedIn()){
                    Booking.setUserLoggedIn(false);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Logged Out");
                    alert.setHeaderText("You just Logged Out!"); // Optional: You can set a header text if needed
                    alert.setContentText("You just Logged Out!");
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("You have not Logged in Yet!");
                    alert.setHeaderText("You have not Logged in Yet!"); // Optional: You can set a header text if needed
                    alert.setContentText("You have not Logged in Yet!");
                    alert.showAndWait();
                }
            }
        });
    }
}