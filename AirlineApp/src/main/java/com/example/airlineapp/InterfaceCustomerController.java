package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InterfaceCustomerController implements Initializable {
	@FXML
	private Button button_logout;

	@FXML
	private Button button_searchFlight;

	@FXML
	private Label label_discountcode;

	@FXML
	private Button button_detail;
	@FXML
	private ListView<String> ticketlist;

	@FXML
	private Label label_name;

	User currentUser;
	Ticket ticket = new Ticket();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		Booking.setUserLoggedIn(true);
		button_logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "login-view.fxml", "Welcome!", null);
			}
		});

		button_searchFlight.setOnAction(event -> navigateToSearchFlight(event));

		button_detail.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (ticket.getEmail() == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("No Ticket is Selected!");
					alert.show();
				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Ticket Details:");
					alert.setHeaderText("Ticket Information"); // Optional
					alert.setContentText(ticket.toStringForDisplay());
					alert.showAndWait();
				}

			}
		});
	}

	private void navigateToSearchFlight(ActionEvent event) {
    try {
        Parent searchFlightParent = FXMLLoader.load(getClass().getResource("search-flight.fxml"));
        Scene searchFlightScene = new Scene(searchFlightParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(searchFlightScene);
        window.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

	public void setUserInformation(User userObject) throws SQLException, IOException {
		// label_welcome.setText("hello~"+userObject.getUsername()+" !");
		// label_airline.setText("the email is: "+userObject.getEmail()+" !");
		currentUser = DBUtils.getUserObject(userObject.getUsername());
		if (currentUser.getDiscountcode() != null) {
			label_discountcode.setText(currentUser.getDiscountcode());
		}
		label_name.setText(currentUser.getFirstname() + " " + currentUser.getLastname());
		DBUtils.displayTickets(ticketlist, currentUser, ticket);
	}

}
