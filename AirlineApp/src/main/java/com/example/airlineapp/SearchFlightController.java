package com.example.airlineapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchFlightController implements Initializable {
    private Ticket ticket;

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

    private LocalDate dateObject;

    public void getDate(ActionEvent event){
         dateObject=datepicker.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ticket=new Ticket();
                if(dateObject==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Insufficient Info!\nPlease enter all required Info!");
                    alert.show();
                }else {
                    ticket.setFlightdate(dateObject.toString());
                    listview_box.getItems().clear();
                    DBUtils.searchFlight(event,listview_box,label_select,tf_origin.getText(),tf_destination.getText(),ticket);
                }
            }
        });

        button_confirmselection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(ticket==null){
                    System.out.println("No flight has been selected!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No flight has been selected!");
                    alert.show();
                }else{
                    System.out.println("ticketID:"+ticket.getFlightID());
                    DBUtils.changeSceneToSeatMap(event,"seatmap.fxml","Seat Map",ticket);
                }

            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"login-view.fxml","Welcome to Login Page!",null,null);
            }
        });

        button_cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneBasic(event,"cancel-view.fxml","Booked the wrong ticket?");
            }
        });
//        Connection connection = null;
//        Statement statement=null;
//        ResultSet queryOutput=null;
//        DatabaseConnection connectionObject=new DatabaseConnection();
//
//        try {
//
////            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AirlineApp", "root", "12345678");
//            connection = connectionObject.getDBConnection();
//            String connectQuery="SELECT flightID, depart_time, arrival_time FROM flights";
//
//            statement=connection.createStatement();
//            queryOutput=statement.executeQuery(connectQuery);
//
//            while (queryOutput.next()){
//                String flightID=queryOutput.getString("flightID");
//                String depart_time=queryOutput.getString("depart_time");
//                String arrival_time=queryOutput.getString("arrival_time");
//                String listOut=flightID+"\t\t"+depart_time+"\t\t"+arrival_time;
////                String listOut=username+"\""+favChannel+"\"";
//                listview_box.getItems().add(listOut);//populate listview with values
//                listview_box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                        //everytime an item is selected, this function changed will be called.
//                        var=listview_box.getSelectionModel().getSelectedItem();
//                        label_select.setText(var.substring(0,7));
//                    }
//                });
//
//            }
//
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

    }
}
