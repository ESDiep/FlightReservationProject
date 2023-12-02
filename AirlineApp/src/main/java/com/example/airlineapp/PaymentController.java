package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

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
    @FXML
    private Button button_loggedin;
    @FXML
    private Button button_apply;
    @FXML
    private TextField tf_discountcode;

    private Ticket ticket;

    public PaymentController(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.checkDiscount(event,ticket,email,tf_discountcode);
                if(Booking.isUserLoggedIn()&&Booking.isDiscountApplied()) {
                    ticketprice.setText(ticket.getPrice() + "");
                    ticketprice.setTextFill(Color.web("#eb4034"));
                }
            }
        });
        textbox.setText(ticket.toString());
        ticketprice.setText(ticket.getPrice()+"");


        if(Booking.isUserLoggedIn()){
            button_loggedin.setText("Logged In");
            button_loggedin.setStyle("-fx-background-color:  #1524c2;");
            button_loggedin.setTextFill(Color.web("#ebebeb"));
        }else {
            button_loggedin.setText("Not Logged In");
            button_loggedin.setStyle("-fx-background-color:  #cccdd9;");
            button_loggedin.setTextFill(Color.web("#0d0e0f"));
        }

        pay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(firstname.getText().equals("")||lastname.getText().equals("")||email.getText().equals("")||cardholdername.getText().equals("")||cardnumber.getText().equals("")||cardcode.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Incompleted Info"); // Optional
                    alert.setContentText("All info should be filled out");
                    // Apply custom font style directly
                    DialogPane dialogPane = alert.getDialogPane();
                    Label contentLabel = (Label) dialogPane.lookup(".content.label");
                    contentLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15pt;");

                    alert.show();
                }else {
                    DBUtils.changeSceneAfterPay(event,"search-flight.fxml","Welcome!",ticket,firstname.getText(),lastname.getText(),email.getText(),cardholdername.getText(),cardnumber.getText(),cardcode.getText());
                }
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
