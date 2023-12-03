package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchFlightController implements Initializable {
	@FXML
	private ListView<String> listview_box;
	@FXML
	private Label label_select;
	@FXML
	private TextField tf_origin;
	@FXML
	private TextField tf_destination;
	@FXML
	private Button button_search;
	@FXML
	private Button button_confirmselection;
	@FXML
	private Button button_login;
	@FXML
	private DatePicker datepicker;
	@FXML
	private Button button_cancel;
	@FXML
	private Button button_loggedin;

	private LocalDate dateObject;
	private Ticket ticket;

	public void getDate(ActionEvent event) {
		dateObject = datepicker.getValue();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		if (Booking.isUserLoggedIn()) {
			button_loggedin.setText("Logged In");
			button_loggedin.setStyle("-fx-background-color:  #1524c2;");
			button_loggedin.setTextFill(Color.web("#ebebeb"));
		} else {
			button_loggedin.setText("Not Logged In");
			button_loggedin.setStyle("-fx-background-color:  #cccdd9;");
			button_loggedin.setTextFill(Color.web("#0d0e0f"));
		}

		button_search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ticket = new Ticket();
				if (dateObject == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Insufficient Info!\nPlease enter all required Info!");
					alert.show();
				} else {
					ticket.setFlightdate(dateObject.toString());
					listview_box.getItems().clear();
					DBUtils.searchFlight(event, listview_box, label_select, tf_origin.getText(),
							tf_destination.getText(), ticket);
				}
			}
		});

		button_confirmselection.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (ticket == null) {
					System.out.println("No flight has been selected!");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("No flight has been selected!");
					alert.show();
				} else {
					System.out.println("ticketID:" + ticket.getFlightID());
					DBUtils.changeSceneToSeatMap(event, "seatmap.fxml", "Seat Map", ticket);
				}

			}
		});

		button_login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "login-view.fxml", "Welcome to Login Page!", null);
			}
		});

		button_cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeSceneBasic(event, "cancel-view.fxml", "Booked the wrong ticket?");
			}
		});

		datepicker.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			if (isNowFocused) {
				datepicker.show();
			}
		});
	}
}
