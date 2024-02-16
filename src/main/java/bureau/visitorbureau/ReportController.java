/**
* Imports all packages that we need to work with
 */

package bureau.visitorbureau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Creates the controller class for our search menu
 */
public class ReportController {

    //Creates a database model object
    private DatabaseDummyModel model = new DatabaseDummyModel();


    //Creates objects of all items in our Scene
    @FXML
    private TableColumn<Visitors, String> addressCol;

    @FXML
    private TableColumn<Visitors, String> cityCol;

    @FXML
    private TableColumn<Visitors, String> emailCol;

    @FXML
    private TableColumn<Visitors, String> fNameCol;

    @FXML
    private TableColumn<Visitors, String> groupsizeCol;

    @FXML
    private TableColumn<Visitors, String> lNameCol;

    @FXML
    private Button myButton;

    @FXML
    private Label myLable;

    @FXML
    private TableView<Visitors> resultsTable;

    @FXML
    private TextField searchBar;

    @FXML
    private TextField searchBar1;

    @FXML
    private TextField searchBar2;

    @FXML
    private TableColumn<Visitors, String> stateCol;

    @FXML
    private TableColumn<Visitors, String> zipCol;

    //Creates the command used by our submit button
    @FXML
    void submit(ActionEvent event) {
        model.updateVisitorsList(searchBar.getText(), searchBar1.getText(), searchBar2.getText());
        resultsTable.setItems(model.getVisitor());

    }

    //Has our searchBar item do the same thing as our submit button when the user presses enter
    @FXML
    void useSearchBar(ActionEvent event) {
        submit(event);
    }

    //sets all of the columns in our scene to whatever is stored in each variable
    @FXML
    public void initialize()
    {
        fNameCol.setCellValueFactory(new PropertyValueFactory<Visitors, String>("fName"));
        lNameCol.setCellValueFactory(new PropertyValueFactory<Visitors, String>("lName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Visitors, String>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Visitors, String>("address"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Visitors, String>("city"));
        stateCol.setCellValueFactory(new PropertyValueFactory<Visitors, String>("state"));
        zipCol.setCellValueFactory(new PropertyValueFactory<Visitors, String>("zipCode"));
        groupsizeCol.setCellValueFactory(new PropertyValueFactory<Visitors, String>("groupSize"));
        resultsTable.setItems(model.getVisitor());
    }

}
