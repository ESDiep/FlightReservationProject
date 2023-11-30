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

public class CancelTicketController implements Initializable {

    @FXML
    private Button button_goback;

    @FXML
    private Button button_cancel;
    @FXML
    private TextField tf_ticketID;
    @FXML
    private TextField tf_paymentID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("To cancel ticket");
                if(tf_ticketID.getText()==""||tf_paymentID.getText()==""){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Insufficient Info!\nPlease enter all required Info!");
                    alert.show();
                }else {
                    DBUtils.cancelFlight(event, "search-flight.fxml", tf_ticketID.getText(), tf_paymentID.getText());
                }
            }
        });

        button_goback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneBasic(event,"search-flight.fxml","Welcome!");
            }
        });
    }
}
