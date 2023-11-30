package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML
    private TextArea textbox;

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField cardholdername;
    @FXML
    private TextField cardnumber;
    @FXML
    private TextField cardcode;
    @FXML
    private Button goback;
    @FXML
    private Button pay;
    @FXML
    private Label ticketprice;


    private Ticket ticket;

    public PaymentController(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textbox.setText(ticket.toString());
        ticketprice.setText(ticket.getPrice()+"");

        pay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneAfterPay(event,"search-flight.fxml","Welcome!",ticket,firstname.getText(),lastname.getText(),email.getText(),cardholdername.getText(),cardnumber.getText(),cardcode.getText());
            }
        });

        goback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToSeatMap(event,"seatmap.fxml","Welcome!",ticket);
            }
        });
    }
}
