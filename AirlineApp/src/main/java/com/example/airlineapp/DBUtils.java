package com.example.airlineapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

public class DBUtils {
    //helper class, can not be instantiated. contains just static methods
    public static void changeScene(ActionEvent event, String fxmlFile, String title, User userObject) {
	Parent root = null;
	if (userObject != null) {
	    try {
		if (userObject.getUsertype().equals("customer")) {
		    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
		    root = loader.load();
		    InterfaceCustomerController interfaceCustomerController = loader.getController();
		    userObject = DBUtils.getUserObject(userObject.getUsername());
		    interfaceCustomerController.setUserInformation(userObject);
		} else if (userObject.getUsertype().equals("staff")) {
		    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
		    root = loader.load();
		    InterfaceStaffController interfaceStaffController = loader.getController();
		    userObject = DBUtils.getUserObject(userObject.getUsername());
		    interfaceStaffController.setUserInformation(userObject);
		}

	    } catch (IOException | SQLException e) {
		e.printStackTrace();
	    }
	} else {
	    try {
		root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setTitle(title);
	stage.setScene(new Scene(root));
	stage.show();
    }

    public static void changeSceneToSeatMap(ActionEvent event, String fxmlFile, String title, Ticket ticket) {
	Parent root = null;

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    if (ticket.getFlightID() == null) {
		System.out.println("No flight has been selected!");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("No flight has been selected!2");
		alert.show();
	    } else {
		try {
		    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));

		    // Custom controller factory to pass the Ticket object
		    loader.setControllerFactory(param -> {
			if (param == SeatMapController.class) {
			    return new SeatMapController(ticket);
			} else {
			    // Return the default controller if not SeatMapController
			    try {
				return param.getDeclaredConstructor().newInstance();
			    } catch (Exception e) {
				throw new RuntimeException(e);
			    }
			}
		    });
		    root = loader.load();

		    connection = connectionObject.getDBConnection();
		    preparedStatement = connection.prepareStatement("SELECT depart_time, arrival_time,origin,destination,flightdate,aircraft FROM flights WHERE flightID =?");
		    preparedStatement.setString(1, ticket.getFlightID());
		    resultSet = preparedStatement.executeQuery();
		    resultSet.next();
		    ticket.setAircraft(resultSet.getString("aircraft"));
		    ticket.setDepart_time(resultSet.getString("depart_time"));
		    ticket.setArrival_time(resultSet.getString("arrival_time"));
		    ticket.setOriginOutput(resultSet.getString("origin"));
		    ticket.setDestinationOutput(resultSet.getString("destination"));
		    ticket.setFlightdate(resultSet.getString("flightdate"));
		    Booking.setDiscountApplied(false);

		} catch (IOException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();
	    }
	} catch (NullPointerException e) {
	    showAlert("Error", "No flight has been selected!1");
	    e.printStackTrace();
	}
    }

    public static void changeSceneToSeatMapTwo(ActionEvent event, String fxmlFile, String title, Ticket ticket) {
	Parent root = null;

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    if (ticket.getFlightID() == null) {
		System.out.println("No flight has been selected!");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("No flight has been selected!2");
		alert.show();
	    } else {
		try {
		    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));

		    // Custom controller factory to pass the Ticket object
		    loader.setControllerFactory(param -> {
			if (param == SeatMapTwoController.class) {
			    return new SeatMapTwoController(ticket);
			} else {
			    // Return the default controller if not SeatMapController
			    try {
				return param.getDeclaredConstructor().newInstance();
			    } catch (Exception e) {
				throw new RuntimeException(e);
			    }
			}
		    });
		    root = loader.load();

		    connection = connectionObject.getDBConnection();
		    preparedStatement = connection.prepareStatement("SELECT depart_time, arrival_time,origin,destination,flightdate,aircraft FROM flights WHERE flightID =?");
		    preparedStatement.setString(1, ticket.getFlightID());
		    resultSet = preparedStatement.executeQuery();
		    resultSet.next();
		    ticket.setAircraft(resultSet.getString("aircraft"));
		    ticket.setDepart_time(resultSet.getString("depart_time"));
		    ticket.setArrival_time(resultSet.getString("arrival_time"));
		    ticket.setOriginOutput(resultSet.getString("origin"));
		    ticket.setDestinationOutput(resultSet.getString("destination"));
		    ticket.setFlightdate(resultSet.getString("flightdate"));
		    Booking.setDiscountApplied(false);

		} catch (IOException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();
	    }
	} catch (NullPointerException e) {
	    showAlert("Error", "No flight has been selected!1");
	    e.printStackTrace();
	}
    }

    public static void changeSceneAfterPay(ActionEvent event, String fxmlFile, String title, Ticket ticket, String firstname, String lastname, String email, String cardholdername, String cardnumber, String cardcode) {
	Parent root = null;

	Payment paymentObject = new Payment();//to be used in later action and function

	Connection connection = null;
	PreparedStatement psInsert = null;
	PreparedStatement psInsertPayment = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    Random random = new Random();

	    // Generate a random 6-digit integer
	    ticket.setTicketID(100_000 + random.nextInt(900_000));
	    ticket.setPaymentID(100_000 + random.nextInt(900_000));
	    ticket.setCust_firstname(firstname);
	    ticket.setCust_lastname(lastname);
	    ticket.setEmail(email);

	    paymentObject.setPaymentID(ticket.getPaymentID());
	    paymentObject.setCardholdername(cardholdername);
	    paymentObject.setCardnumber(Integer.parseInt(cardnumber));
	    paymentObject.setCardcode(Integer.parseInt(cardcode));

	    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
	    root = loader.load();

	    connection = connectionObject.getDBConnection();
	    psInsert = connection.prepareStatement("INSERT INTO tickets (ticketID,paymentID,flightID,cust_lastname,cust_firstname,seatID,price,depart_time,arrival_time,originOutput,destinationOutput,flightdate,aircraft,email) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	    psInsert.setInt(1, ticket.getTicketID());
	    psInsert.setInt(2, ticket.getPaymentID());
	    psInsert.setString(3, ticket.getFlightID());
	    psInsert.setString(4, ticket.getCust_lastname());
	    psInsert.setString(5, ticket.getCust_firstname());
	    psInsert.setString(6, ticket.getSeatID());
	    psInsert.setDouble(7, ticket.getPrice());
	    psInsert.setString(8, ticket.getDepart_time());
	    psInsert.setString(9, ticket.getArrival_time());
	    psInsert.setString(10, ticket.getOriginOutput());
	    psInsert.setString(11, ticket.getDestinationOutput());
	    psInsert.setString(12, ticket.getFlightdate());
	    psInsert.setString(13, ticket.getAircraft());
	    psInsert.setString(14, ticket.getEmail());
	    psInsert.executeUpdate();

	    psInsertPayment = connection.prepareStatement("INSERT INTO payments (paymentID,cardholdername,cardnumber,securitycode) VALUES(?,?,?,?)");
	    psInsertPayment.setInt(1, ticket.getPaymentID());
	    psInsertPayment.setString(2, cardholdername);
	    psInsertPayment.setInt(3, Integer.parseInt(cardnumber));
	    psInsertPayment.setInt(4, Integer.parseInt(cardcode));
	    psInsertPayment.executeUpdate();

	    Booking.setDiscountApplied(false);
	    //code below was outside
	    System.out.println("Payment is Confirmed with unique paymentID:" + ticket.getPaymentID() + "\nTicket is booked with unique TicketID:" + ticket.getTicketID() + "\nConfirmation Email is sent to:" + ticket.getEmail());
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Payment is Confirmed, Ticket is booked!");
	    alert.setHeaderText(null); // Optional: You can set a header text if needed
	    alert.setContentText("Payment is Confirmed with unique paymentID:" + ticket.getPaymentID() + "\nTicket is booked with unique TicketID:" + ticket.getTicketID() + "\nConfirmation Email is sent to:" + ticket.getEmail());
	    alert.showAndWait();


	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setTitle(title);
	    stage.setScene(new Scene(root));
	    stage.show();

	} catch (IOException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (psInsertPayment != null) {
		try {
		    psInsertPayment.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (psInsert != null) {
		try {
		    psInsert.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}


    }

    private static void showAlert(String title, String content) {
	Alert alert = new Alert(Alert.AlertType.ERROR);
	alert.setTitle(title);
	alert.setContentText(content);
	alert.show();
    }

    public static void changeSceneBasic(ActionEvent event, String fxmlFile, String title) {
	Parent root = null;

	try {
	    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
	    root = loader.load();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setTitle(title);
	stage.setScene(new Scene(root));
	stage.show();

    }

    public static void changeSceneToPay(ActionEvent event, String fxmlFile, String title, Ticket ticket) {
	Parent root = null;

	try {
	    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
	    // Custom controller factory to pass the Ticket object
	    loader.setControllerFactory(param -> {
		if (param == PaymentController.class) {
		    return new PaymentController(ticket);
		} else {
		    // Return the default controller if not SeatMapController
		    try {
			return param.getDeclaredConstructor().newInstance();
		    } catch (Exception e) {
			throw new RuntimeException(e);
		    }
		}
	    });
	    root = loader.load();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setTitle(title);
	stage.setScene(new Scene(root));
	stage.show();

    }

    public static void signUpUser(ActionEvent event, User userObject) {
	Connection connection = null;
	PreparedStatement psInsert = null;
	PreparedStatement psCheckUserExists = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    connection = connectionObject.getDBConnection();
	    psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
	    psCheckUserExists.setString(1, userObject.getUsername());
	    resultSet = psCheckUserExists.executeQuery();

	    if (resultSet.isBeforeFirst()) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Username already exists!\nYou cannot use this username.");
		alert.show();
	    } else {
		psInsert = connection.prepareStatement("INSERT INTO users (username,password,usertype,email,lastname,firstname) VALUES(?,?,?,?,?,?)");
		psInsert.setString(1, userObject.getUsername());
		psInsert.setString(2, userObject.getPassword());
		psInsert.setString(3, userObject.getUsertype());
		psInsert.setString(4, userObject.getEmail());
		psInsert.setString(5, userObject.getLastname());
		psInsert.setString(6, userObject.getFirstname());
		psInsert.executeUpdate();
		System.out.println(userObject.toString());

		if (userObject.getUsertype().equals("customer")) {
		    changeScene(event, "interface-cust.fxml", "Welcome! Customer~", userObject);
		} else if (userObject.getUsertype().equals("staff")) {
		    changeScene(event, "interface-staff.fxml", "Welcome! Staff~", userObject);
		} else {
		    System.out.println("bummer");
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (psInsert != null) {
		try {
		    psInsert.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (psCheckUserExists != null) {
		try {
		    psCheckUserExists.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public static void logInUser(ActionEvent event, String username, String password) {
	//this function is on the login page and click on login after the username and password is entered.
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT password,usertype FROM users WHERE username =?");
	    preparedStatement.setString(1, username);
	    resultSet = preparedStatement.executeQuery();

	    if (!resultSet.isBeforeFirst()) {
		System.out.println("User not found in the database!");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("provided credentials are incorrect!");
		alert.show();
	    } else {
		while (resultSet.next()) {
		    String retrievedPassword = resultSet.getString("password");
		    String usertype = resultSet.getString("usertype");
		    if (retrievedPassword.equals(password)) {
			User userObject = new User();
			userObject.setUsername(username);
			userObject.setPassword(password);
			userObject.setUsertype(usertype);
			if (usertype.equals("customer")) {
			    changeScene(event, "interface-cust.fxml", "Welcome! Customer~", userObject);
			} else if (usertype.equals("staff")) {
			    changeScene(event, "interface-staff.fxml", "Welcome! Staff~", userObject);
			}
		    } else {
			System.out.println("password did not match");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("provided password incorrect!");
			alert.show();
		    }
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public static void searchFlight(ActionEvent event, ListView<String> listview_box, Label label_select, String origin, String destination, Ticket ticket) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT origin, destination,flightdate FROM flights WHERE origin =? AND destination=? AND flightdate=?");
	    preparedStatement.setString(1, origin);
	    preparedStatement.setString(2, destination);
	    preparedStatement.setString(3, ticket.getFlightdate());
	    resultSet = preparedStatement.executeQuery();

	    if (!resultSet.isBeforeFirst()) {
		System.out.println("Flight not found in the database!");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("NO Matching Flight Found!");
		alert.show();
	    } else {

		preparedStatement = connection.prepareStatement("SELECT flightID, depart_time, arrival_time, origin,destination,flightdate,aircraft FROM flights WHERE origin =? AND destination=? AND flightDate=?");
		preparedStatement.setString(1, origin);
		preparedStatement.setString(2, destination);
		preparedStatement.setString(3, ticket.getFlightdate());
		resultSet = preparedStatement.executeQuery();

//		listview_box.getItems().clear();
		while (resultSet.next()) {
		    String flightID = resultSet.getString("flightID");
		    String depart_time = resultSet.getString("depart_time");
		    String arrival_time = resultSet.getString("arrival_time");
		    String originOutput = resultSet.getString("origin");
		    String destinationOutput = resultSet.getString("destination");
		    String flightdate = resultSet.getString("flightdate");
		    String aircraft = resultSet.getString("aircraft");
		    String listOut = flightID + "\t\t" + depart_time + "\t\t" + arrival_time + "\t\t" + originOutput + "\t\t" + destinationOutput + "\t\t" + flightdate + "\t\t" + aircraft;
		    listview_box.getItems().add(listOut);//populate listview with values
		}

		//select item from the list
		listview_box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
			//everytime an item is selected, this function changed will be called.
			if (t1 != null) {//this if{} is added to handle clear the listview_box after each time search is pressed.
			    String extractedFlightID = listview_box.getSelectionModel().getSelectedItem().substring(0, 7).trim();
			    label_select.setText(extractedFlightID);
			    ticket.setFlightID(extractedFlightID);
			    ticket.setAircraft(DBUtils.getAircraft(extractedFlightID));

			}
		    }
		});

	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    private static String getAircraft(String extractedFlightID) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();
	String aircraft = null;
	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT aircraft FROM flights WHERE flightID =? ");
	    preparedStatement.setString(1, extractedFlightID);
	    resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
		aircraft = resultSet.getString("aircraft");
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}

	return aircraft;
    }

    public static Ticket getTicketObject(String ticketID) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	Ticket tempTicketObject = new Ticket();
	tempTicketObject.setTicketID(Integer.parseInt(ticketID));
	connection = connectionObject.getDBConnection();
	preparedStatement = connection.prepareStatement("SELECT paymentID, flightID,cust_lastname,cust_firstname,seatID,price,depart_time,arrival_time,originOutput,destinationOutput,flightdate,aircraft,email FROM tickets WHERE ticketID =?");
	preparedStatement.setString(1, ticketID);
	resultSet = preparedStatement.executeQuery();

	resultSet.next();
	String paymentID = resultSet.getString("paymentID");
	String flightID = resultSet.getString("flightID");
	String cust_lastname = resultSet.getString("cust_lastname");
	String cust_firstname = resultSet.getString("cust_firstname");
	String seatID = resultSet.getString("seatID");
	String depart_time = resultSet.getString("depart_time");
	String arrival_time = resultSet.getString("arrival_time");
	String originOutput = resultSet.getString("originOutput");
	String destinationOutput = resultSet.getString("destinationOutput");
	String flightdate = resultSet.getString("flightdate");
	String price = resultSet.getString("price");
	String aircraft = resultSet.getString("aircraft");
	String email = resultSet.getString("email");


	tempTicketObject.setPaymentID(Integer.parseInt(paymentID));
	tempTicketObject.setFlightID(flightID);
	tempTicketObject.setCust_firstname(cust_firstname);
	tempTicketObject.setCust_lastname(cust_lastname);
	tempTicketObject.setSeatID(seatID);
	tempTicketObject.setDepart_time(depart_time);
	tempTicketObject.setArrival_time(arrival_time);
	tempTicketObject.setOriginOutput(originOutput);
	tempTicketObject.setDestinationOutput(destinationOutput);
	tempTicketObject.setFlightdate(flightdate);
	tempTicketObject.setAircraft(aircraft);
	tempTicketObject.setEmail(email);
	tempTicketObject.setPrice(Double.parseDouble(price));

	return tempTicketObject;

    }

    public static User getUserObject(String username) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	User tempUserObject = new User();
	tempUserObject.setUsername(username);

	connection = connectionObject.getDBConnection();
	preparedStatement = connection.prepareStatement("SELECT usertype, email,lastname,firstname,discountcode FROM users WHERE username =?");
	preparedStatement.setString(1, username);
	resultSet = preparedStatement.executeQuery();

	resultSet.next();
	String usertype = resultSet.getString("usertype");
	String email = resultSet.getString("email");
	String lastname = resultSet.getString("lastname");
	String firstname = resultSet.getString("firstname");
	String discountcode = resultSet.getString("discountcode");
	tempUserObject.setUsertype(usertype);
	tempUserObject.setEmail(email);
	tempUserObject.setLastname(lastname);
	tempUserObject.setFirstname(firstname);
	tempUserObject.setDiscountcode(discountcode);
	return tempUserObject;
    }

    public static void cancelFlight(ActionEvent event, String fxmlFile, String ticketID, String paymentID) {
	Parent root = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();


	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT ticketID,paymentID FROM tickets WHERE ticketID =? AND paymentID=? ");
	    preparedStatement.setString(1, ticketID);
	    preparedStatement.setString(2, paymentID);
	    resultSet = preparedStatement.executeQuery();

	    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
	    root = loader.load();

	    if (!resultSet.isBeforeFirst()) {
		System.out.println("Ticket not found in the database!");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Ticket not found in the database!");
		alert.show();
	    } else {
		Ticket ticketObject = DBUtils.getTicketObject(ticketID);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to cancel this ticket?\n" + ticketObject.toStringForCancel());

		// Get the dialog pane
		DialogPane dialogPane = alert.getDialogPane();

		// Modify the buttons
		for (ButtonType buttonType : dialogPane.getButtonTypes()) {
		    if (buttonType.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
			Button cancelButton = (Button) dialogPane.lookupButton(buttonType);
			cancelButton.setText("Go Back");
		    }
		}
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {
		    // User clicked OK, perform the action
		    System.out.println(ticketObject);
		    preparedStatement = connection.prepareStatement("DELETE FROM tickets WHERE ticketID =? AND paymentID=? ");
		    preparedStatement.setString(1, ticketID);
		    preparedStatement.setString(2, paymentID);
		    preparedStatement.executeUpdate();

		    alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Confirmed, Ticket is Cancelled!");
		    alert.setHeaderText(null); // Optional: You can set a header text if needed
		    alert.setContentText("Ticket is Cancelled!\n" + "Notification is sent to Email:\n" + ticketObject.getEmail());
		    alert.showAndWait();

		    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    stage.setScene(new Scene(root));
		    stage.show();
		} else {
		    // User clicked Cancel or closed the dialog, handle accordingly
		    // do nothing
		}


	    }
	} catch (SQLException | IOException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}


    }

    public static void displayTickets(ListView<String> listview_box, User user, Ticket outsideTicketObject) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT ticketID FROM tickets WHERE email =? ");
	    preparedStatement.setString(1, user.getEmail());
	    resultSet = preparedStatement.executeQuery();

	    while (resultSet.next()) {
		String ticketID = resultSet.getString("ticketID");
		Ticket ticket = DBUtils.getTicketObject(ticketID);
		String listOut = ticket.getTicketID() + "    " + ticket.getFlightID() + "    " + ticket.getOriginOutput() + "    " + ticket.getDestinationOutput() + "    " + ticket.getFlightdate() + "    " + ticket.getDepart_time() + "    " + ticket.getArrival_time();
		listview_box.getItems().add(listOut);//populate listview with values
	    }

	    //select item from the list
	    if (listview_box != null) {
		listview_box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
			//everytime an item is selected, this function changed will be called.
			if (t1 != null) {//this if{} is added to handle clear the listview_box after each time search is pressed.
			    String var = listview_box.getSelectionModel().getSelectedItem().substring(0, 7).trim();
			    try {
				Ticket temp = DBUtils.getTicketObject(var);
				outsideTicketObject.setTicketID(temp.getTicketID());
				outsideTicketObject.setCust_firstname(temp.getCust_firstname());
				outsideTicketObject.setCust_lastname(temp.getCust_lastname());
				outsideTicketObject.setPaymentID(temp.getPaymentID());
				outsideTicketObject.setFlightID(temp.getFlightID());
				outsideTicketObject.setSeatID(temp.getSeatID());
				outsideTicketObject.setPrice(temp.getPrice());
				outsideTicketObject.setEmail(temp.getEmail());
				outsideTicketObject.setAircraft(temp.getAircraft());

			    } catch (SQLException e) {
				throw new RuntimeException(e);
			    }

			}
		    }
		});
	    }


	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public static void checkDiscount(ActionEvent event, Ticket ticket, TextField email, TextField tf_discountcode) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT discountcode FROM users WHERE email =? ");
	    preparedStatement.setString(1, email.getText());
	    resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {
		String discountcode = resultSet.getString("discountcode");
		if (discountcode.equals(tf_discountcode.getText())) {
		    ticket.setPrice(ticket.getPrice() * 0.8);
		    Booking.setDiscountApplied(true);

		    Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setHeaderText("Discount Applied!"); // Optional
		    alert.setContentText("Congratulations!\nYou get a 20% discount");
		    alert.show();
		} else {
		    Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setContentText("Invalid Discount Code\n(Valid Code is Linked with Email)");
		    alert.show();
		}
	    } else {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Invalid Discount Code\n(Valid Code is Linked with Email)");
		alert.show();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public static void showPassenger(ActionEvent event, String flightID, ListView<String> listview_box) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT flightID FROM flights WHERE flightID =? ");
	    preparedStatement.setString(1, flightID);
	    resultSet = preparedStatement.executeQuery();
	    if (!resultSet.next()) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Flight Not Found!");
		alert.show();
	    } else {

		preparedStatement = connection.prepareStatement("SELECT ticketID,cust_firstname,cust_lastname,seatID,email FROM tickets WHERE flightID =? ");
		preparedStatement.setString(1, flightID);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
		    String ticketID = resultSet.getString("ticketID");
		    String cust_firstname = resultSet.getString("cust_firstname");
		    String cust_lastname = resultSet.getString("cust_lastname");
		    String seatID = resultSet.getString("seatID");
		    String email = resultSet.getString("email");

		    String listOut = ticketID + "    " + seatID + "      " + cust_firstname + "  " + cust_lastname + "      " + email;
		    listview_box.getItems().add(listOut);//populate listview with values
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public static void assignDiscount(String email, String code) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT email FROM users WHERE email =? ");
	    preparedStatement.setString(1, email);
	    resultSet = preparedStatement.executeQuery();
	    if (!resultSet.next()) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("User with that Email Not Found\nTry different Email");
		alert.show();
	    } else {

		preparedStatement = connection.prepareStatement("UPDATE users SET discountcode =?  WHERE email =?");
		preparedStatement.setString(1, code);
		preparedStatement.setString(2, email);
		preparedStatement.executeUpdate();

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("New Discount Code Assigned!");
		alert.setHeaderText("New Discount Code Assigned!"); // Optional: You can set a header text if needed
		alert.setContentText("Discount Code: " + code + "\nSent to Email: " + email);
		alert.showAndWait();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public static void showSeats(Ticket ticket, RadioButton rbA1, RadioButton rbB1, RadioButton rbC1, RadioButton rbD1, RadioButton rbE1) {


	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseConnection connectionObject = new DatabaseConnection();

	try {
	    connection = connectionObject.getDBConnection();
	    preparedStatement = connection.prepareStatement("SELECT seatID FROM tickets WHERE flightID =? ");
	    preparedStatement.setString(1, ticket.getFlightID());
	    resultSet = preparedStatement.executeQuery();

	    while (resultSet.next()) {
		String seatID = resultSet.getString("seatID");
		if (seatID.equals("A1")) {
		    rbA1.setVisible(false);
		} else if (seatID.equals("B1")) {
		    rbB1.setVisible(false);
		} else if (seatID.equals("C1")) {
		    rbC1.setVisible(false);
		} else if (seatID.equals("D1")) {
		    rbD1.setVisible(false);
		} else if (seatID.equals("E1")) {
		    rbE1.setVisible(false);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {

	    if (resultSet != null) {
		try {
		    resultSet.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}

