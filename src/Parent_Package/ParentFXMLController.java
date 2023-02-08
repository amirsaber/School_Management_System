/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParentPackage;

import AlertMaker.AlertMaker;
import ShowChildrenPackage.ShowChildernFXMLController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author amir
 */
public class ParentFXMLController implements Initializable {

    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField lname;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXRadioButton father;
    @FXML
    private JFXRadioButton mother;
    @FXML
    private JFXTextField phonenum1;
    @FXML
    private JFXTextField phonenum2;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXDatePicker birthdate;
    @FXML
    private TableView<ParentClass> table1;
    @FXML
    private TableColumn<ParentClass, String> FirstNameCol;
    @FXML
    private TableColumn<ParentClass, String> LastNameCol;
    @FXML
    private TableColumn<ParentClass, String> GenderCol;
    @FXML
    private TableColumn<ParentClass, String> EmailCol;
    @FXML
    private TableColumn<ParentClass, String> ParentIDCol;
    @FXML
    private TableColumn<ParentClass, String> PhoneNumCol1;
    @FXML
    private TableColumn<ParentClass, String> PhoneNumCol2;
    @FXML
    private TableColumn<ParentClass, String> AddressCol;
    @FXML
    private TableColumn<ParentClass, String> BirthDateCol;
    @FXML
    private TableColumn<ParentClass, String> RegCol;
    @FXML
    private JFXTextField SearchTectField1;
    @FXML
    private JFXComboBox<String> SearchComboBox1;
    @FXML
    private AnchorPane anch;

    ParentAction ParentActionObject = new ParentAction();
    String ParentGenderStrng = "null";
    ToggleGroup group = new ToggleGroup();
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
  public static String ParentSIDsave;
    //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////// ///////////////////////////////////////
    ///////////////////////////////////////

    public static String getParentSIDsave() {
        return ParentSIDsave;
    }
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
    @FXML
    private void AddParent() throws SQLException {

        if (father.isSelected()) {
            ParentGenderStrng = "Father";

        } else {
            ParentGenderStrng = "Mother";
        }
        if (ID.getText().isEmpty() || fname.getText().isEmpty() || lname.getText().isEmpty() || ParentGenderStrng.equals("null")
                || phonenum1.getText().isEmpty() || phonenum2.getText().isEmpty()) {
            AlertMaker a = new AlertMaker();
            a.showErrorMessage("Attention", "Please fill out all required fields");
        } else {
            ParentActionObject.addParent(ID, fname, lname,
                    ParentGenderStrng, phonenum1, phonenum2, email, address,
                    birthdate);
            ParentGenderStrng = "null";
            father.setSelected(true);
            ParentActionObject.loadTable(table1);
        }

    }

    @FXML
    private void UpdateParent() throws SQLException {

        if (father.isSelected()) {
            ParentGenderStrng = "Father";

        } else {
            ParentGenderStrng = "Mother";
        }
        if (ID.getText().isEmpty() || fname.getText().isEmpty() || lname.getText().isEmpty() || ParentGenderStrng.equals("null")
                || phonenum1.getText().isEmpty() || phonenum2.getText().isEmpty()) {
            AlertMaker a = new AlertMaker();
            a.showErrorMessage("Attention", "Please fill out all required fields");
        } else {
            ParentActionObject.updateParent(ID, fname, lname,
                    ParentGenderStrng, phonenum1, phonenum2, email, address,
                    birthdate);
            ParentGenderStrng = "null";
            father.setSelected(true);
            ParentActionObject.loadTable(table1);
        }

    }

    @FXML
    private void LoadFromTable() throws SQLException {

        Label l = new Label();
        ParentActionObject.loadfromtable(ID, fname, lname,
                l, phonenum1, phonenum2, email, address,
                birthdate, table1);
        if (l.getText().equals("Father")) {
            father.setSelected(true);
        } else {
            mother.setSelected(true);
        }

    }

    @FXML
    private void DeleteParent() throws SQLException {

        ParentActionObject.deleteParent(ID, fname, lname,
                ParentGenderStrng, phonenum1, phonenum2, email, address,
                birthdate);
        father.setSelected(true);
        ParentActionObject.loadTable(table1);

    }

    @FXML
    private void SearchParent() throws SQLException {

        ParentActionObject.searchParent(SearchTectField1, SearchComboBox1, table1);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> ID.selectEnd());
        birthdate.setValue(LocalDate.of(1995, Month.JANUARY, 1));
        birthdate.setShowWeekNumbers(false);
        father.setToggleGroup(group);
        mother.setToggleGroup(group);
        mother.selectedColorProperty().set(Color.RED);
        ID.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                ID.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        phonenum1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                phonenum1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        phonenum2.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                phonenum2.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        SearchComboBox1.getItems().addAll("ID", "First Name", "Last Name",
                "Phone Number1", "Gender", "Phone Number2", "Birth Date"
        );
        father.setSelected(true);

        ParentIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        PhoneNumCol1.setCellValueFactory(new PropertyValueFactory<>("phonenum1"));
        PhoneNumCol2.setCellValueFactory(new PropertyValueFactory<>("phonenum2"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        BirthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        RegCol.setCellValueFactory(new PropertyValueFactory<>("reg"));
        anch.setOnMouseEntered(e -> {
            try {
                ParentActionObject.loadTable(table1);
            } catch (SQLException ex) {
                Logger.getLogger(ParentFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        table1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                   ParentSIDsave = ID.getText();
                   ShowChildernFXMLController s = new ShowChildernFXMLController();
                        try {
                            s.startStage();
                        } catch (IOException ex) {
                            Logger.getLogger(ParentFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    }
                }
            }
        });
    }///for main class
}//end of class

