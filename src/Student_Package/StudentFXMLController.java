/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentPackage;

import AlertMaker.AlertMaker;
import ShowParent.ShowParentFXMLController;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author amir
 */
public class StudentFXMLController implements Initializable {

    @FXML
    private JFXTextField StudentId;
    @FXML
    private JFXTextField StudentFirstName;
    @FXML
    private JFXTextField StudentLastName;
    @FXML
    private JFXRadioButton MaleRadioButton;
    @FXML
    private JFXRadioButton FemaleRadioButton;
    @FXML
    private JFXComboBox<String> ClassComboBox;
    @FXML
    private JFXTextField PhoneNumber;
    @FXML
    private JFXTextField Adress;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField ParentName;
    @FXML
    private JFXDatePicker BirthDate;
    @FXML
    private JFXDatePicker RegisterDate;
    @FXML
    private CheckComboBox<String> checkComboBox;
    @FXML
    private TableColumn<StudentClass, String> StudentIDCol;
    @FXML
    private TableColumn<StudentClass, String> FirstNameCol;
    @FXML
    private TableColumn<StudentClass, String> LastNameCol;
    @FXML
    private TableColumn<StudentClass, String> GenderCol;
    @FXML
    private TableColumn<StudentClass, String> ClassCol;
    @FXML
    private TableColumn<StudentClass, String> PhoneCol;
    @FXML
    private TableColumn<StudentClass, String> EmailCol;
    @FXML
    private TableColumn<StudentClass, String> AddrssCol;
    @FXML
    private TableColumn<StudentClass, String> HobbiesCol;
    @FXML
    private TableColumn<StudentClass, String> ParentIDCol;
    @FXML
    private TableColumn<StudentClass, String> BirthDayCol;
    @FXML
    private TableColumn<StudentClass, String> RegestrationDateCol;
    @FXML
    private TableColumn<StudentClass, String> RegestrationByCol;
    @FXML
    private TableView<StudentClass> table;
    @FXML
    private JFXTextField SearchTectField;
    @FXML
    private JFXComboBox<String> SearchComboBox;
    final ObservableList<String> strings = FXCollections.observableArrayList();
    StudentAction StudentActionObject = new StudentAction();
    String StudentGenderStrng = "null";
    ToggleGroup group = new ToggleGroup();//new toggle group to make user select only one type of gender
    @FXML
    private AnchorPane anch;
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////// ///////////////////////////////////////
    ///////////////////////////////////////
    @FXML
    private HBox HobbiesHbox;

    @FXML
    private void AddStudent() throws SQLException {

        if (MaleRadioButton.isSelected()) {
            StudentGenderStrng = "Male";

        } else {
            StudentGenderStrng = "Female";
        }
        if (StudentFirstName.getText().isEmpty() || StudentLastName.getText().isEmpty() || StudentGenderStrng.equals("null")
                || ClassComboBox.getSelectionModel().getSelectedItem().isEmpty() || PhoneNumber.getText().isEmpty()
                || ParentName.getText().isEmpty()) {
            AlertMaker a = new AlertMaker();
            a.showErrorMessage("Attention", "Please fill out all required fields");
        } else {
            StudentActionObject.addStudent(StudentId, StudentFirstName, StudentLastName,
                    StudentGenderStrng, ClassComboBox, PhoneNumber, Email, Adress,
                    checkComboBox, ParentName, BirthDate, RegisterDate);
            StudentGenderStrng = "null";
            MaleRadioButton.setSelected(true);
            StudentActionObject.loadTable(table);
        }

    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    public static String ParentIDSave;
    //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////// ///////////////////////////////////////
    ///////////////////////////////////////

    public static String getParentIDSave() {
        return ParentIDSave;
    }

    @FXML
    private void UpdateStudent() throws SQLException {

        if (MaleRadioButton.isSelected()) {
            StudentGenderStrng = "Male";

        } else {
            StudentGenderStrng = "Female";
        }
        if (StudentFirstName.getText().isEmpty() || StudentLastName.getText().isEmpty() || StudentGenderStrng.equals("null")
                || ClassComboBox.getSelectionModel().getSelectedItem().isEmpty() || PhoneNumber.getText().isEmpty()
                || ParentName.getText().isEmpty()) {
            AlertMaker a = new AlertMaker();
            a.showErrorMessage("Attention", "Please fill out all required fields");
        } else {
            StudentActionObject.updateStudent(StudentId, StudentFirstName, StudentLastName,
                    StudentGenderStrng, ClassComboBox, PhoneNumber, Email, Adress,
                    checkComboBox, ParentName, BirthDate, RegisterDate);
            StudentGenderStrng = "null";
            MaleRadioButton.setSelected(true);
            StudentActionObject.loadTable(table);
        }

    }

    @FXML
    private void LoadFromTable() throws SQLException {

        Label l = new Label();
        StudentActionObject.loadFromTable(StudentId, StudentFirstName, StudentLastName,
                l, ClassComboBox, PhoneNumber, Email, Adress,
                checkComboBox, ParentName, BirthDate, RegisterDate, table);
        if (l.getText().equals("Male")) {
            MaleRadioButton.setSelected(true);
        } else {
            FemaleRadioButton.setSelected(true);
        }

    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////// ///////////////////////////////////////
    ///////////////////////////////////////

    @FXML
    private void DeleteStudent() throws SQLException {

        StudentActionObject.deleteStudent(StudentId, StudentFirstName, StudentLastName,
                StudentGenderStrng, ClassComboBox, PhoneNumber, Email, Adress,
                checkComboBox, ParentName, BirthDate, RegisterDate);
        MaleRadioButton.setSelected(true);
        StudentActionObject.loadTable(table);

    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////// ///////////////////////////////////////
    ///////////////////////////////////////

    @FXML
    private void SearchStudent() throws SQLException {

        StudentActionObject.searchStudent(SearchTectField, SearchComboBox, table);

    }

///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> StudentId.selectEnd());//to request focus when scene start
        BirthDate.setValue(LocalDate.of(2000, Month.JANUARY, 1));//set local date for birthday pickerto first day in 2000's 
        RegisterDate.setValue(LocalDate.now());//set regestraion date by default to today
        BirthDate.setShowWeekNumbers(false); // false to prevent user from input incorrect date
        RegisterDate.setShowWeekNumbers(false);// false to prevent user from input incorrect date
        strings.addAll("Reading", "Writing", "Studying", "Computer", "Coding", "Watching TV", "Going to Movies", "Walking",
                "Running", "Singing", "Acting", "Painting", "Football", "Handball", "Swimming", "Tennis"
        );// set some hobbies in list
        checkComboBox.getItems().addAll(strings);// add list into checkcombobox

        MaleRadioButton.setToggleGroup(group);//add male radio button to group
        FemaleRadioButton.setToggleGroup(group);//add female radio button to group
        FemaleRadioButton.selectedColorProperty().set(Color.RED);//change selction color for female radio button

        StudentId.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                StudentId.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });//number validation for student id textfield
        PhoneNumber.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                PhoneNumber.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });//number validation for student phone number textfield

        ParentName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                ParentName.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });//number validation for parent id textfield

        try {
            int returnId = StudentActionObject.getId();
            StudentId.setText(Integer.toString(returnId));

        } catch (Exception e) {
        }

        ClassComboBox.getItems().addAll("1/1", "1/2", "1/3",
                "2/1", "2/2", "2/3",
                "3/1", "3/2", "3/3",
                "4/1", "4/2", "4/3",
                "5/1", "5/2", "5/3",
                "6/1", "6/2", "6/3"
        );
        SearchComboBox.getItems().addAll("ID", "First Name", "Last Name",
                "Phone Number", "Gender", "Parent ID", "Hobbies"
        );
        MaleRadioButton.setSelected(true);
        //////////////////////////////////
        //////////////////////////////////
        //////////////////////////////////

        StudentIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        ClassCol.setCellValueFactory(new PropertyValueFactory<>("classs"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        AddrssCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        HobbiesCol.setCellValueFactory(new PropertyValueFactory<>("hobbies"));
        ParentIDCol.setCellValueFactory(new PropertyValueFactory<>("parentid"));
        BirthDayCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        RegestrationDateCol.setCellValueFactory(new PropertyValueFactory<>("regdate"));
        RegestrationByCol.setCellValueFactory(new PropertyValueFactory<>("regname"));
        //////////////////////////////////
        //////////////////////////////////
        //////////////////////////////////
        anch.setOnMouseEntered(e -> {
            try {
                StudentActionObject.loadTable(table);
                 
               
            } catch (SQLException e1) {
            }
            
        });
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        ParentIDSave = ParentName.getText();
                        ShowParentFXMLController s = new ShowParentFXMLController();
                       
                        try {
                            s.startStage();
                        } catch (IOException ex) {
                            Logger.getLogger(StudentFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }//

}//
