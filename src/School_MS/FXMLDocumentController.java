/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanegementsssss;

import AlertMaker.AlertMaker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author amir
 */
public class FXMLDocumentController implements Initializable {

    public static String UserNameSave;

    public String getUserNameSave() {
        return UserNameSave;
    }
    private ResultSet rs;

    private PreparedStatement ps;

    @FXML
    private JFXTextField usernamefield;
    @FXML
    private JFXPasswordField passwordfield;
    Connection conn = SqliteConnection.Connector();
    boolean checkk = false;

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {

        AlertMaker alert = new AlertMaker();
        if (usernamefield.getText().isEmpty() || passwordfield.getText().isEmpty()) {

            if (usernamefield.getText().isEmpty() && passwordfield.getText().isEmpty()) {
                alert.showSimpleAlert("Attention", "Please enter the user name and password");
            } else if (usernamefield.getText().isEmpty()) {
                alert.showSimpleAlert("Attention", "Please enter the user name");
            } else {
                alert.showSimpleAlert("Attention", "Please enter the password");
            }
        } else {
            try {

                String query = "Select * from users where usernamee=? and password=?";
                ps = conn.prepareStatement(query);

                ps.setString(1, usernamefield.getText());
                ps.setString(2, passwordfield.getText());
                rs = ps.executeQuery();

                if (rs.next()) {
                    UserNameSave = usernamefield.getText();
                    System.out.println(UserNameSave);

                    Parent root1 = FXMLLoader.load(getClass().getResource("MainSceneFXML.fxml"));

                    Scene scene1 = new Scene(root1);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(scene1);

                    window.setMaximized(true);

                    usernamefield.clear();
                    passwordfield.clear();

                } else {
                    alert.showSimpleAlert("Attention", "Username and password did not match to records");

                }
                ps.close();
                rs.close();

            } catch (Exception e1) {

                System.err.println(e1);
                alert.showSimpleAlert("Attention", e1.getMessage());

            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
