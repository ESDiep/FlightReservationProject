package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Button button_logout;

    @FXML
    private Label label_welcome;

    @FXML
    private Label label_airline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"login-view.fxml","Log in!",null,null);
            }
        });
    }

    public void setUserInformation(String username,String favChannel){
        label_welcome.setText("hello~"+username+" !");
        label_airline.setText("the airline channel is called: "+favChannel+" !");
    }


}
