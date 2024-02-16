//Imports all the packages necessary to work with.
package bureau.visitorbureau;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.net.URL;
import java.time.LocalDate;
import java.sql.*;
import java.util.ResourceBundle;

//Creates the controller class for the user data entry.

public class HomeController implements Initializable {
    //Creates objects of all items in the home Scene
    int checkZip;
    int checkNumberPeople;
    @FXML
    private TextField city;
    @FXML
    private DatePicker date;
    @FXML
    private TextField numberPeople;

    @FXML
    private ComboBox<String> reason;

    @FXML
    private TextField state;

    @FXML
    private TextField streetAddress;

    @FXML
    private Button submit;

    @FXML
    private TextField userName;

    @FXML
    private TextField userName1;

    @FXML
    private TextField email;

    @FXML
    private TextField zipCode;
    
    //This method checks the type of input data, validates the input then inserts the data into the
    //database model and then clears all the input fields.
    @FXML
    public void dataSubmit(ActionEvent event) throws SQLException {

        Window owner = submit.getScene().getWindow();

        if (userName.getText().isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your First Name");
            return;
        }

        if (userName1.getText().isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your Last Name");
            return;
        }

        if (date.getValue() == null) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please Choose Today's Date");
            return;
        }

        if (email.getText().isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }

        if (streetAddress.getText().isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter the Street Address");
            return;
        }

        if (city.getText().isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter the City Name");
            return;
        }

        if (state.getText().isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter the State");
            return;
        }

        if (String.valueOf(zipCode.getText()).isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter the Zipcode");
            return;
        }

        try{
            checkZip = Integer.parseInt(String.valueOf(zipCode.getText()));
        }
        catch (NumberFormatException e){
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a valid Zipcode");
            return;
        }


        if (String.valueOf(numberPeople.getText()).isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter number of people in your group");
            return;
        }

        try{
            checkNumberPeople = Integer.parseInt(String.valueOf(numberPeople.getText()));
        }
        catch (NumberFormatException e){
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter numeric value for Number of people in Group ");
            return;
        }

        if (reason.getSelectionModel().getSelectedItem().toString().isEmpty()) {
            displayAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter reason for your visit");
            return;
        }

        LocalDate todayDate = date.getValue();
        String date = todayDate.toString();
        String firstname = userName.getText();
        String lastname = userName1.getText();
        String Email = email.getText();
        String streetname = streetAddress.getText();
        String cityname = city.getText();
        String statename = state.getText();
        int zipcode = checkZip;
        int countGroupPeople = checkNumberPeople;
        String reasonforvisit = reason.getSelectionModel().getSelectedItem().toString();


        JDBC_Connection jdbc_connection = new JDBC_Connection();
        jdbc_connection.fillUser(Email, date, firstname, lastname, streetname, cityname, statename, zipcode, countGroupPeople, reasonforvisit);
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        reason.setItems(FXCollections.observableArrayList("Family Visit","Business","Vacation","Other"));
    }
    
    //set all field to empty/null
    private void clearFields(){
        city.setText(null);
        date.setValue(null);
        numberPeople.setText(null);
        state.setText(null);
        reason.valueProperty().set(null);
        streetAddress.setText(null);
        userName.setText(null);
        userName1.setText(null);
        zipCode.setText(null);
        email.setText(null);
    }

    //Creates a method for the dialogue box to pop up on the screen when user enters invalid datatype.    
    private static void displayAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
