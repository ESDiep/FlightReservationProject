package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SeatMapController implements Initializable {
    @FXML
    private RadioButton rb_a1;
    @FXML
    private RadioButton rb_b1;
    @FXML
    private RadioButton rb_c1;
    @FXML
    private RadioButton rb_d1;
    @FXML
    private RadioButton rb_e1;
    @FXML
    private Label label_flightid;
    @FXML
    private Label showprice;
    @FXML
    private Button button_cancel;
    @FXML
    private Button button_confirmseat;
    @FXML
    private Label label_aircraft;

    private Ticket ticket;

    public SeatMapController(Ticket ticket) {
	this.ticket = ticket;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	DBUtils.showSeats(ticket,rb_a1,rb_b1,rb_c1,rb_d1,rb_e1);

	ToggleGroup toggleGroup=new ToggleGroup();
	rb_a1.setToggleGroup(toggleGroup);
	rb_b1.setToggleGroup(toggleGroup);
	rb_c1.setToggleGroup(toggleGroup);
	rb_d1.setToggleGroup(toggleGroup);
	rb_e1.setToggleGroup(toggleGroup);

	toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
	    if (toggleGroup.getSelectedToggle() != null) {
		RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
		String toggleButton=selectedRadioButton.getText();
		switch (toggleButton){
		    case "A1":
			showprice.setText("5000");
			break;
		    case "B1":
			showprice.setText("4000");
			break;
		    case "C1":
			showprice.setText("3000");
			break;
		    case "D1":
			showprice.setText("2000");
			break;
		    case "E1":
			showprice.setText("1000");
			break;
		}
	    }
	});

	label_flightid.setText(ticket.getFlightID());
	label_aircraft.setText(ticket.getAircraft());

	button_cancel.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		DBUtils.changeSceneBasic(event,"search-flight.fxml","Welcome!");
	    }
	});

	button_confirmseat.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		if(toggleGroup.getSelectedToggle()==null){
		    System.out.println("Please select a seat before proceeding");
		    Alert alert=new Alert(Alert.AlertType.ERROR);
		    alert.setContentText("Must select a seat before proceeding!");
		    alert.show();
		}else {
		    String toggleName=((RadioButton)toggleGroup.getSelectedToggle()).getText();
		    double price = 0;
		    switch (toggleName){
			case "A1":
			    price=5000;
			    break;
			case "B1":
			    price=4000;
			    break;
			case "C1":
			    price=3000;
			    break;
			case "D1":
			    price=2000;
			    break;
			case "E1":
			    price=1000;
			    break;
		    }
		    ticket.setPrice(price);
		    ticket.setSeatID(toggleName);
		    System.out.println("Selected Seat: "+toggleName+" Price: "+price);
//		    System.out.println(ticket.toString());
		    DBUtils.changeSceneToPay(event,"payment.fxml","Payment",ticket);
		}
	    }
	});
    }
}
