/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentPackage;

import AlertMaker.AlertMaker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.controlsfx.control.CheckComboBox;
import schoolmanegementsssss.FXMLDocumentController;
import schoolmanegementsssss.SqliteConnection;

/**
 *
 * @author amir
 */
public class StudentAction {

    Connection con = SqliteConnection.Connector();
    PreparedStatement ps;
    ResultSet rs;
    final ObservableList<StudentClass> data = FXCollections.observableArrayList();
    final ObservableList<String> options = FXCollections.observableArrayList();
    private AlertMaker alert = new AlertMaker();
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    public int getId() throws SQLException {
        int res = 0;
        try {
            String query = "select Max(ID) from Students";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                res = rs.getInt(1);
                
                
            }
            res += 1;
            ps.close();
            rs.close();
        } catch (Exception e) {
            ps.close();
            rs.close();
        }
        return res;

    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    public void addStudent(JFXTextField id, JFXTextField fname, JFXTextField lastname, String gender, JFXComboBox classs,
            JFXTextField phone, JFXTextField email, JFXTextField address, CheckComboBox hobbies, JFXTextField parentid,
            JFXDatePicker birthdate, JFXDatePicker regdate) throws SQLException {

        String query = "Insert into Students (ID,firstname,lastname,gender,class,phonenumber,email,adress,hobbies"
                + ",parentid,birthdate,registerdate,registerby) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, id.getText());
            ps.setString(2, fname.getText());
            ps.setString(3, lastname.getText());
            ps.setString(4, gender);
            ps.setString(5, (String) classs.getValue());
            ps.setString(6, phone.getText());
            ps.setString(7, email.getText());
            ps.setString(8, address.getText());
            ps.setString(9, hobbies.getCheckModel().getCheckedItems().toString());
            ps.setString(10, parentid.getText());
            ps.setString(11, birthdate.getValue().toString());
            ps.setString(12, regdate.getValue().toString());
            ps.setString(13, FXMLDocumentController.UserNameSave);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student " + fname.getText() + " " + lastname.getText() + " " + "has been added");
            alert.setTitle("Success !!");
            alert.setHeaderText(null);
            alert.showAndWait();
            ps.close();
            int h = Integer.parseInt(id.getText()) + 1;
            id.setText(Integer.toString(h));
            fname.clear();
            lastname.clear();
            gender = "null";
            gender = "null";
            classs.getSelectionModel().clearSelection();
            classs.setPromptText("Please Select Class Of Student *");
            //    classs.sett("Please Select Class Of Student *");
            phone.clear();
            email.clear();
            address.clear();
            hobbies.getCheckModel().clearChecks();
            parentid.clear();
            birthdate.setValue(LocalDate.of(2000, Month.JANUARY, 1));//set local date for birthday pickerto first day in 2000's 
            regdate.setValue(LocalDate.now());//set regestraion date by default to today

        } catch (SQLException e) {
            ps.close();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Add student " + fname.getText() + " " + lastname.getText() + " " + "has been fail");
            alert.setTitle("Failed !!");
            alert.setHeaderText(null);
            alert.showAndWait();
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            ps.close();
        }

    }

    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    public void updateStudent(JFXTextField id, JFXTextField fname, JFXTextField lastname, String gender, JFXComboBox classs,
            JFXTextField phone, JFXTextField email, JFXTextField address, CheckComboBox hobbies, JFXTextField parentid,
            JFXDatePicker birthdate, JFXDatePicker regdate) throws SQLException {

        String query = "Update Students set firstname=?, lastname=?, gender=?,"
                + " class=?, phonenumber=?  ,email=?, adress=? , hobbies=? , parentid=?"
                + " , birthdate=? , registerdate=?"
                + "where ID='" + id.getText() + "'  ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, fname.getText());
            ps.setString(2, lastname.getText());
            ps.setString(3, gender);
            ps.setString(4, (String) classs.getValue());
            ps.setString(5, phone.getText());
            ps.setString(6, email.getText());
            ps.setString(7, address.getText());
            ps.setString(8, hobbies.getCheckModel().getCheckedItems().toString());
            ps.setString(9, parentid.getText());
            ps.setString(10, birthdate.getValue().toString());
            ps.setString(11, regdate.getValue().toString());

            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student " + fname.getText() + " " + lastname.getText() + " " + "has been update");
            alert.setTitle("Success !!");
            alert.setHeaderText(null);
            alert.showAndWait();
            ps.close();
            int h = getId();
            id.setText(Integer.toString(h));
            fname.clear();
            lastname.clear();
            gender = "null";
            gender = "null";
            classs.getSelectionModel().clearSelection();
            //  classs.setPromptText("Please Select Class Of Student *");
            //    classs.sett("Please Select Class Of Student *");
            phone.clear();
            email.clear();
            address.clear();
            hobbies.getCheckModel().clearChecks();
            parentid.clear();
            birthdate.setValue(LocalDate.of(2000, Month.JANUARY, 1));//set local date for birthday pickerto first day in 2000's 
            regdate.setValue(LocalDate.now());//set regestraion date by default to today

        } catch (SQLException e) {
            ps.close();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Add student " + fname.getText() + " " + lastname.getText() + " " + "has been fail");
            alert.setTitle("Failed !!");
            alert.setHeaderText(null);
            alert.showAndWait();
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            ps.close();
        }

    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    public void loadTable(TableView table) throws SQLException {
        for (int i = 0; i < table.getItems().size(); i++) { // to when i back to main menu by back button remove all fields on table
            table.getItems().clear();
        }
        try {

            String query = "Select * from Students";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

                data.add(new StudentClass(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13)
                ));
                table.setItems(data);

            }

            ps.close();
            rs.close();
            //        table.getColumns().addAll(tid, tfn, tln, te, tg, tmn, tm, tbd, tvd);

        } catch (Exception eViewAllPatient) {
            System.err.println(eViewAllPatient);
            Alert CreateNewUserErrorAlert = new Alert(Alert.AlertType.ERROR);
            CreateNewUserErrorAlert.setHeaderText(null);
            CreateNewUserErrorAlert.setContentText(eViewAllPatient.getMessage());
            CreateNewUserErrorAlert.showAndWait();
         ps.close();
          rs.close();
        }
    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    public void loadFromTable(JFXTextField id, JFXTextField fname, JFXTextField lastname, Label gender, JFXComboBox classs,
            JFXTextField phone, JFXTextField email, JFXTextField address, CheckComboBox hobbies, JFXTextField parentid,
            JFXDatePicker birthdate, JFXDatePicker regdate, TableView table) throws SQLException {
        try {

            StudentClass sc = (StudentClass) table.getSelectionModel().getSelectedItem();
            String query = "Select * from Students where ID = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, sc.getId());

            rs = ps.executeQuery();
            String hobbiess = null;
            String birth = "";
            String regdatee = "";
            String sssssssss;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                id.setText(rs.getString(1));
                fname.setText(rs.getString(2));
                lastname.setText(rs.getString(3));
                gender.setText(rs.getString(4));
                classs.setValue(rs.getString(5));
                phone.setText(rs.getString(6));
                email.setText(rs.getString(7));
                address.setText(rs.getString(8));
                hobbiess = (rs.getString(9));
                parentid.setText(rs.getString(10));
                birth = (rs.getString("birthdate"));
                regdatee = (rs.getString("registerdate"));
                sssssssss = (rs.getString(13));

            }
            System.out.println(hobbiess);
            LocalDate localDate1 = LocalDate.parse(birth, formatter);
            LocalDate localDate2 = LocalDate.parse(regdatee, formatter);
            birthdate.setValue(localDate1);

            regdate.setValue(localDate2);
            hobbies.getCheckModel().clearChecks();
            if (hobbiess.contains("Reading")) {
                hobbies.getCheckModel().check("Reading");
            }
            if (hobbiess.contains("Writing")) {
                hobbies.getCheckModel().check("Writing");
            }
            if (hobbiess.contains("Computer")) {
                hobbies.getCheckModel().check("Computer");
            }
            if (hobbiess.contains("Coding")) {
                hobbies.getCheckModel().check("Coding");
            }
            if (hobbiess.contains("Watching TV")) {
                hobbies.getCheckModel().check("Watching TV");
            }
            if (hobbiess.contains("Going to Movies")) {
                hobbies.getCheckModel().check("Going to Movies");
            }
            if (hobbiess.contains("Walking")) {
                hobbies.getCheckModel().check("Walking");
            }
            if (hobbiess.contains("Running")) {
                hobbies.getCheckModel().check("Running");
            }
            if (hobbiess.contains("Singing")) {
                hobbies.getCheckModel().check("Singing");
            }
            if (hobbiess.contains("Acting")) {
                hobbies.getCheckModel().check("Acting");
            }
            if (hobbiess.contains("Painting")) {
                hobbies.getCheckModel().check("Painting");
            }
            if (hobbiess.contains("Football")) {
                hobbies.getCheckModel().check("Football");
            }
            if (hobbiess.contains("Handball")) {
                hobbies.getCheckModel().check("Handball");
            }
            if (hobbiess.contains("Swimming")) {
                hobbies.getCheckModel().check("Swimming");
            }
            if (hobbiess.contains("Tennis")) {
                hobbies.getCheckModel().check("Tennis");
            }
            if (hobbiess.contains("Studying")) {
                hobbies.getCheckModel().check("Studying");
            }

            ps.close();
            rs.close();

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
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////// ///////////////////////////////////////
    ///////////////////////////////////////

    public void deleteStudent(JFXTextField id, JFXTextField fname, JFXTextField lastname, String gender, JFXComboBox classs,
            JFXTextField phone, JFXTextField email, JFXTextField address, CheckComboBox hobbies, JFXTextField parentid,
            JFXDatePicker birthdate, JFXDatePicker regdate) throws SQLException {
        //  conn = DriverManager.getConnection("jdbc:sqlite:Clinic_Manager_DB.db");

        Alert deleteStudent = new Alert(Alert.AlertType.CONFIRMATION);
        deleteStudent.setTitle("Confirmation dialog");
        deleteStudent.setContentText("Are you sure to delete");
        deleteStudent.setHeaderText("hhhhhhhhhhh");

        Optional<ButtonType> action = deleteStudent.showAndWait();

        try {
            if (action.get() == ButtonType.OK) {

                String query = "Delete from Students where ID =?  ";

                ps = con.prepareStatement(query);

                ps.setString(1, id.getText());

                ps.executeUpdate();

            }

            ps.close();
            int h = getId();
            id.setText(Integer.toString(h));
            fname.clear();
            lastname.clear();
            gender = "null";
            gender = "null";
            classs.getSelectionModel().clearSelection();
            //  classs.setPromptText("Please Select Class Of Student *");
            //    classs.sett("Please Select Class Of Student *");
            phone.clear();
            email.clear();
            address.clear();
            hobbies.getCheckModel().clearChecks();
            parentid.clear();
            birthdate.setValue(LocalDate.of(2000, Month.JANUARY, 1));//set local date for birthday pickerto first day in 2000's 
            regdate.setValue(LocalDate.now());//set regestraion date by default to today

        } catch (Exception e1) {
            Alert CreateNewUserErrorAlert = new Alert(Alert.AlertType.ERROR);
            CreateNewUserErrorAlert.setHeaderText(null);
            CreateNewUserErrorAlert.setContentText(e1.getMessage());
            CreateNewUserErrorAlert.showAndWait();
            System.err.println(e1);
            ps.close();

        }

    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    public void searchStudent(JFXTextField t, JFXComboBox c, TableView te) throws SQLException {
        String indicator = (String) c.getValue();
        if (indicator.equals("ID")) {
            indicator = "ID";
        }
        if (indicator.equals("First Name")) {
            indicator = "firstname";
        }
        if (indicator.equals("Last Name")) {
            indicator = "lastname";
        }
        if (indicator.equals("Phone Number")) {
            indicator = "phonenumber";
        }
        if (indicator.equals("Gender")) {
            indicator = "gender";
        }
        if (indicator.equals("Parent ID")) {
            indicator = "parentid";
        }
        if (indicator.equals("Hobbies")) {
            indicator = "hobbies";
        }
        for (int i = 0; i < te.getItems().size(); i++) { // to when i back to main menu by back button remove all fields on table
            te.getItems().clear();
        }
        try {
            String query;
            if (indicator.equals("gender")) {
                query = "Select * from Students where " + indicator + " like '" + t.getText() + "%' ";
            }

            else query = "Select * from Students where " + indicator + " like '%" + t.getText() + "%' ";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

                data.add(new StudentClass(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13)
                ));
                te.setItems(data);

            }

            ps.close();
            rs.close();

        } catch (Exception eViewAllPatient) {
            System.err.println(eViewAllPatient);
            Alert CreateNewUserErrorAlert = new Alert(Alert.AlertType.ERROR);
            CreateNewUserErrorAlert.setHeaderText(null);
            CreateNewUserErrorAlert.setContentText(eViewAllPatient.getMessage());
            CreateNewUserErrorAlert.showAndWait();
            ps.close();
            rs.close();
        }

    }

///////////////////////////////////////
    ///////////////////////////////////////
}///////
