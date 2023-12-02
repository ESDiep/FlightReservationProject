package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InterfaceStaffController implements Initializable {
    @FXML
    private Button button_logout;

    @FXML
    private TextField tf_email;

    @FXML
    private Button button_assign;
    @FXML
    private ListView<String> ticketlist;

    @FXML
    private Label label_name;
    @FXML
    private TextField tf_flightid;
    @FXML
    private TextField tf_code;
    @FXML
    private Button button_show;


    User currentUser;
//    User selectedUser=new User();// this user object will only have email
    Ticket ticket = new Ticket();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

	Booking.setUserLoggedIn(true);


	button_show.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		ticketlist.getItems().clear();
		DBUtils.showPassenger(event,tf_flightid.getText(),ticketlist);
	    }
	});

	button_logout.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		DBUtils.changeScene(event, "login-view.fxml", "Welcome!", null);
	    }
	});

	button_assign.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {


		if (tf_code.getText().equals("")||tf_email.getText().equals("")) {
		    Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setContentText("Incomplete Info!\nPlease enter both email and code");
		    alert.show();
		} else {
		    DBUtils.assignDiscount(tf_email.getText(),tf_code.getText());
//		    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//		    alert.setTitle("Ticket Details:");
//		    alert.setHeaderText("Ticket Information"); // Optional
//		    alert.setContentText(ticket.toStringForDisplay());
//		    alert.showAndWait();
		}

	    }
	});
    }

    public void setUserInformation(User userObject) throws SQLException {
//        label_welcome.setText("hello~"+userObject.getUsername()+" !");
//        label_airline.setText("the email is: "+userObject.getEmail()+" !");
	currentUser = DBUtils.getUserObject(userObject.getUsername());
//	if (currentUser.getDiscountcode() != null) {
//	    label_discountcode.setText(currentUser.getDiscountcode());
//	}
	label_name.setText(currentUser.getFirstname() + " " + currentUser.getLastname());
//	DBUtils.displayTickets(ticketlist, currentUser, ticket);
    }


}
