/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShowChildrenPackage;

import static ParentPackage.ParentFXMLController.ParentSIDsave;
import StudentPackage.StudentClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import schoolmanegementsssss.SqliteConnection;

/**
 * FXML Controller class
 *
 * @author Amir
 */
public class ShowChildernFXMLController implements Initializable {

    @FXML
    private TableColumn<studentTable, String> StudentID;
    @FXML
    private TableColumn<studentTable, String> StudentFirstName;
    @FXML
    private TableColumn<studentTable, String> StudentLastName;
    @FXML
    private TableColumn<studentTable, String> StudentGender;
    @FXML
    private TableColumn<studentTable, String> StudentClass;
    @FXML
    private TableColumn<techerTable, String> TeacherFirstName;
    @FXML
    private TableColumn<techerTable, String> TeacherLastName;
    @FXML
    private TableColumn<techerTable, String> TeacherGender;
    @FXML
    private TableColumn<techerTable, String> TeacherPhoneNumber;
    @FXML
    private TableColumn<techerTable, String> TeacherEmail;
     @FXML
    private TableColumn<techerTable, String> Subject;

    @FXML
    private TextField textfield;
    @FXML
    private TableView<studentTable> StudentTble;
    @FXML
    private TableView<techerTable> TeacherTable;
    final ObservableList<studentTable> data = FXCollections.observableArrayList();
    final ObservableList<techerTable> data1 = FXCollections.observableArrayList();
    Connection con = SqliteConnection.Connector();
    PreparedStatement ps;
    ResultSet rs;
    @FXML
    private Label topLabel;
    @FXML
    private Label close;
    @FXML
    private Label close1;
    @FXML
    private AnchorPane anch;

    private double xOffset;
    private double yOffset;
   
    private void load() {
        String query = "Select * from Students where parentid='" + ParentSIDsave + "' ";
        for (int i = 0; i < StudentTble.getItems().size(); i++) { // to when i back to main menu by back button remove all fields on table
            StudentTble.getItems().clear();
        }
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new studentTable(
                        rs.getString("ID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("gender"),
                        rs.getString("class"))
                );
                StudentTble.setItems(data);

            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ShowChildernFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }

    private void loadTeachers() {
        for (int i = 0; i < TeacherTable.getItems().size(); i++) { // to when i back to main menu by back button remove all fields on table
            TeacherTable.getItems().clear();
        }
        String query = "Select * from Teachers where class LIKE '%" + textfield.getText() + "%' ";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                data1.add(new techerTable(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("subject"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("email")
                )
                );
                TeacherTable.setItems(data1);

            }
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ShowChildernFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }
    public static Stage Childrenstage = new Stage(StageStyle.TRANSPARENT);

    public void startStage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ShowChildernFXML.fxml"));

        Scene scene = new Scene(root);
        Childrenstage.setTitle("Children Details");
        Childrenstage.setScene(scene);
        Childrenstage.getIcons().add(new Image("/icon2/school.png"));
        Childrenstage.showAndWait();

    }

    @FXML
    public void loadFromTable() throws SQLException {
        try {

            studentTable sc = (studentTable) StudentTble.getSelectionModel().getSelectedItem();
            String query = "Select * from Students where ID = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, sc.getId());

            rs = ps.executeQuery();

            while (rs.next()) {
                textfield.setText(rs.getString("class"));

            }

            ps.close();
            rs.close();
            loadTeachers();
        } catch (Exception e) {

            System.err.println(e);
            Alert CreateNewUserErrorAlert = new Alert(Alert.AlertType.ERROR);
            CreateNewUserErrorAlert.setHeaderText(null);
            CreateNewUserErrorAlert.setContentText(e.getMessage());
            CreateNewUserErrorAlert.showAndWait();
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentClass.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        StudentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        StudentFirstName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        StudentLastName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        StudentGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        StudentClass.setCellValueFactory(new PropertyValueFactory<>("classs"));
        TeacherFirstName.setCellValueFactory(new PropertyValueFactory<>("TFname"));
        TeacherLastName.setCellValueFactory(new PropertyValueFactory<>("TLname"));
        Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        TeacherGender.setCellValueFactory(new PropertyValueFactory<>("TGender"));
        TeacherPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        TeacherEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
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
            Childrenstage.close();

        });
        close1.setOnMousePressed(e -> {
            Childrenstage.close();

        });
        close.setOnMouseClicked(e -> {
            Childrenstage.setIconified(true);

        });
        close.setOnMousePressed(e -> {
            Childrenstage.setIconified(true);

        });

    }

}
