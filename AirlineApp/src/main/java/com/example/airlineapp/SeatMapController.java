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
    private Button button_cancel;
    @FXML
    private Button button_confirmseat;

    private Ticket ticket;

    public SeatMapController(Ticket ticket) {
	this.ticket = ticket;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	ToggleGroup toggleGroup=new ToggleGroup();
	rb_a1.setToggleGroup(toggleGroup);
	rb_b1.setToggleGroup(toggleGroup);
	rb_c1.setToggleGroup(toggleGroup);
	rb_d1.setToggleGroup(toggleGroup);
	rb_e1.setToggleGroup(toggleGroup);

	label_flightid.setText(ticket.getFlightID());

	button_cancel.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		DBUtils.changeSceneToSearch(event,"search-flight.fxml","Welcome!");
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
		    System.out.println(ticket.toString());
		    DBUtils.changeSceneToPay(event,"payment.fxml","Payment",ticket);
		}
	    }
	});
    }
}
