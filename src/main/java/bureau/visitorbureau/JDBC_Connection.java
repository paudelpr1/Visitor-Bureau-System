/**
 * Imports all packages that we need to work with
 */
package bureau.visitorbureau;
import javafx.scene.control.Alert;
import java.sql.*;

/**
 * This class connects to the database
 * */
public class JDBC_Connection {
    Connection connection = null;
    Statement statement;
    PreparedStatement psInsert = null;
    PreparedStatement psEmailExists = null;
    ResultSet resultSet;

    /**
     * This method make a connection to the database and inserts data into database table.
     * */
    public void fillUser(String Email, String inputdate, String firstname, String lastname, String streetname, String cityname, String statename, int zipcode, int countGroupPeople, String reasonforvisit) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabaseproject", "root", "");
            psEmailExists = connection.prepareStatement("SELECT * FROM guest WHERE Email = ?");
            psEmailExists.setString(1, Email);
            resultSet = psEmailExists.executeQuery();

            /**
             * This condition checks if the primary key of database table is unique or not and prompts an alert if it is not unique.
             * */
            if (resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Visitor information is already stored");
                alert.show();

                /**
                 * Inserts unique primary key and other values to the database.
                 * */
            } else {
                psInsert = connection.prepareStatement("INSERT INTO guest( Email, Date, Fname, Lname ,StreetAdd, City, State, Zip, NumPeople, VisitReason) VALUES (?,?,?,?,?,?,?,?,?,?)");
                psInsert.setString(1, Email);
                psInsert.setString(2, inputdate);
                psInsert.setString(3, firstname);
                psInsert.setString(4, lastname);
                psInsert.setString(5, streetname);
                psInsert.setString(6, cityname);
                psInsert.setString(7, statename);
                psInsert.setInt(8, zipcode);
                psInsert.setInt(9, countGroupPeople);
                psInsert.setString(10, reasonforvisit);

                psInsert.executeUpdate();


                //Success message if the registration is successful
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Registration Successful");
                alert.show();
            }


        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }

}
