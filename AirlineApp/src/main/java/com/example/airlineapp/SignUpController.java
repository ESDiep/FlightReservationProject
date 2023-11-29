package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;
    @FXML
    private RadioButton rb_customer;
    @FXML
    private RadioButton rb_agent;
    @FXML
    private Button button_signup;

    @FXML
    private Button button_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	ToggleGroup toggleGroup=new ToggleGroup();
	rb_customer.setToggleGroup(toggleGroup);
	rb_agent.setToggleGroup(toggleGroup);
	rb_customer.setSelected(true);



	button_signup.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		String toggleName=((RadioButton)toggleGroup.getSelectedToggle()).getText();
		if(!tf_username.getText().trim().isEmpty()&&!tf_password.getText().trim().isEmpty()){
		    DBUtils.signUpUser(event,tf_username.getText(),tf_password.getText(),toggleName);
		}else {
		    System.out.println("Please fill in all information");
		    Alert alert=new Alert(Alert.AlertType.ERROR);
		    alert.setContentText("Need all info filled in to sign up!");
		    alert.show();
		}
	    }
	});

	button_login.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		DBUtils.changeScene(event,"login-view.fxml","you can login, from signupcontrol",null,null);
	    }
	});
    }


}
