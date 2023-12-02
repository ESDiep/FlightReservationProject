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
    @FXML
    private TextField tf_firstname;
    @FXML
    private TextField tf_lastname;
    @FXML
    private TextField tf_email;


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
		//added first last and email
		if(!tf_username.getText().trim().isEmpty()&&!tf_password.getText().trim().isEmpty()&&!tf_firstname.getText().trim().isEmpty()&&!tf_lastname.getText().trim().isEmpty()&&!tf_email.getText().trim().isEmpty()){
		    User userObject=new User();
		    userObject.setEmail(tf_email.getText().trim());
		    userObject.setFirstname(tf_firstname.getText().trim());
		    userObject.setLastname(tf_lastname.getText().trim());
		    userObject.setUsername(tf_username.getText().trim());
		    userObject.setPassword(tf_password.getText().trim());
		    userObject.setUsertype(toggleName);
//		    DBUtils.signUpUser(event,tf_username.getText(),tf_password.getText(),toggleName);
		    DBUtils.signUpUser(event,userObject);
		}else {
		    System.out.println("Please fill in ALL information");
		    Alert alert=new Alert(Alert.AlertType.ERROR);
		    alert.setContentText("Requires ALL info filled out to sign up!");
		    alert.show();
		}
	    }
	});

	//this is the goback button
	button_login.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		DBUtils.changeScene(event,"login-view.fxml","Welcome!",null);
	    }
	});
    }


}
