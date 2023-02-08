/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShowParent;

//import static ShowParent.ShowParentFXMLController.stage;
import static StudentPackage.StudentFXMLController.ParentIDSave;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import schoolmanegementsssss.SqliteConnection;
//import StudentPackage.StudentFXMLController.ParentIDSave;

/**
 * FXML Controller class
 *
 * @author Amir
 */
public class ShowParentFXMLController implements Initializable {

    @FXML
    private Label id;
    @FXML
    private Label fname;
    
    @FXML
    private Label phone1;
    @FXML
    private Label phone2;
    @FXML
    private Label email;
    @FXML
    private Label address;
    private double xOffset;
    private double yOffset;

    Connection con = SqliteConnection.Connector();
    PreparedStatement ps;
    ResultSet rs;
    @FXML
    private Label birthdate;
    @FXML
    private AnchorPane anch;
    @FXML
    private Label topLabel;
    @FXML
    private Label close;
    String firstname;
    String lastname;
    @FXML
    private Label close1;

    private void load() {
        String query = "Select * from Parents where ID='" + ParentIDSave + "' ";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                id.setText(rs.getString(1));
                firstname = (rs.getString(2));
                lastname = (rs.getString(3));
                phone1.setText(rs.getString(5));
                phone2.setText(rs.getString(6));
                email.setText(rs.getString(7));
                address.setText(rs.getString(8));
                birthdate.setText(rs.getString(9));
            }
            fname.setText(firstname + " " + lastname);
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShowParentFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            try {
                ps.close();
                rs.close();
            } catch (Exception e) {
            }
        }

    }
    public static Stage stage = new Stage(StageStyle.TRANSPARENT);

    public void startStage() throws IOException {
        System.out.println(ParentIDSave);
        Parent root = FXMLLoader.load(getClass().getResource("ShowParentFXML.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Parent Information");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/icon2/school.png"));
        stage.showAndWait();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();

        topLabel.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) anch.getScene().getWindow();
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });
        // Set up Mouse Dragging for the Event pop up window
        topLabel.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) anch.getScene().getWindow();
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });
        close1.setOnMouseClicked(e -> {
            stage.close();

        });
        close1.setOnMousePressed(e -> {
            stage.close();

        });
        close.setOnMouseClicked(e -> {
            stage.setIconified(true);

        });
        close.setOnMousePressed(e -> {
            stage.setIconified(true);

        });

    }

}
