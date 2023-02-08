/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeacherPackage;

import AlertMaker.AlertMaker;
import StudentPackage.StudentClass;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class TeacherAction {

    Connection con = SqliteConnection.Connector();
    PreparedStatement ps;
    ResultSet rs;
    final ObservableList<TeacherClass> data = FXCollections.observableArrayList();
    final ObservableList<String> options = FXCollections.observableArrayList();
    private AlertMaker alert = new AlertMaker();

    public int getId() throws SQLException {
        int res = 0;
        try {
            String query = "select Max(ID) from Teachers";
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

    public void addTeacher(JFXTextField id, JFXTextField fname, JFXTextField lastname, String gender, CheckComboBox classs,
            JFXTextField phone, JFXTextField email, JFXComboBox job, JFXComboBox subject, JFXTextField salary,
            JFXTextField age, JFXDatePicker regdate) throws SQLException {

        String query = "Insert into Teachers (ID,firstname,lastname,gender,class,phone,email,job,subject,"
                + "salary,age,registerdate,registerby) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, id.getText());
            ps.setString(2, fname.getText());
            ps.setString(3, lastname.getText());
            ps.setString(4, gender);
            ps.setString(5, classs.getCheckModel().getCheckedItems().toString());
            ps.setString(6, phone.getText());
            ps.setString(7, email.getText());
            ps.setString(8, (String) job.getValue());
            ps.setString(9, (String) subject.getValue());
            ps.setString(10, salary.getText());
            ps.setString(11, age.getText());
            ps.setString(12, regdate.getValue().toString());
            ps.setString(13, FXMLDocumentController.UserNameSave);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Teacher" + fname.getText() + " " + lastname.getText() + " " + "has been added");
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
            subject.getSelectionModel().clearSelection();
            // subject.setPromptText("Please Select the subject*");
            //    classs.sett("Please Select Class Of Student *");
            job.getSelectionModel().clearSelection();
            subject.getSelectionModel().clearSelection();
            ///    job.setPromptText("Please Select the subject*");
            phone.clear();
            email.clear();
            salary.clear();
            classs.getCheckModel().clearChecks();
            age.clear();
            regdate.setValue(LocalDate.now());//set regestraion date by default to today

        } catch (SQLException e) {
            ps.close();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Add teacher " + fname.getText() + " " + lastname.getText() + " " + "has been failed");
            alert.setTitle("Failed !!");
            alert.setHeaderText(null);
            alert.showAndWait();
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            ps.close();
        }

    }
/////////////////////////////////////////////////////////////////////////////

    public void UpdateTeacher(JFXTextField id, JFXTextField fname, JFXTextField lastname, String gender, CheckComboBox classs,
            JFXTextField phone, JFXTextField email, JFXComboBox job, JFXComboBox subject, JFXTextField salary,
            JFXTextField age, JFXDatePicker regdate) throws SQLException {

        String query = "Update Teachers set firstname=?, lastname=?, gender=?,"
                + " class=?, phone=?  ,email=?, job=? , subject=? , salary=? , age=?"
                + " , registerdate=? "
                + "where ID='" + id.getText() + "'  ";
        try {

            ps = con.prepareStatement(query);

            ps.setString(1, fname.getText());
            ps.setString(2, lastname.getText());
            ps.setString(3, gender);
            ps.setString(4, classs.getCheckModel().getCheckedItems().toString());
            ps.setString(5, phone.getText());
            ps.setString(6, email.getText());
            ps.setString(7, (String) job.getValue());
            ps.setString(8, (String) subject.getValue());
            ps.setString(9, salary.getText());
            ps.setString(10, age.getText());
            ps.setString(11, regdate.getValue().toString());
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Teacher " + fname.getText() + " " + lastname.getText() + " " + "has been updated");
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
            subject.getSelectionModel().clearSelection();
            job.getSelectionModel().clearSelection();

            phone.clear();
            email.clear();
            salary.clear();
            classs.getCheckModel().clearChecks();
            age.clear();
            regdate.setValue(LocalDate.now());//set regestraion date by default to today
            phone.clear();
            email.clear();

        } catch (SQLException e) {
            ps.close();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Update Teacher " + fname.getText() + " " + lastname.getText() + " " + "has been failed");
            alert.setTitle("Failed !!");
            alert.setHeaderText(null);
            alert.showAndWait();
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            ps.close();
        }

    }

    ////////////////////////////////////////////////////////////////////////////
    public void loadTable(TableView table) throws SQLException {
        for (int i = 0; i < table.getItems().size(); i++) { // to when i back to main menu by back button remove all fields on table
            table.getItems().clear();
        }
        try {

            String query = "Select * from Teachers";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

                data.add(new TeacherClass(
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
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public void loadFromTable(JFXTextField id, JFXTextField fname, JFXTextField lastname, Label gender, CheckComboBox classs,
            JFXTextField phone, JFXTextField email, JFXComboBox job, JFXComboBox subject, JFXTextField salary,
            JFXTextField age, JFXDatePicker regdate, TableView table) throws SQLException {
        try {

            TeacherClass tc = (TeacherClass) table.getSelectionModel().getSelectedItem();
            String query = "Select * from Teachers where ID = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, tc.getId());

            rs = ps.executeQuery();
            String classss = null;
            String regdatee = "";
            String sssssssss;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                id.setText(rs.getString(1));
                fname.setText(rs.getString(2));
                lastname.setText(rs.getString(3));
                gender.setText(rs.getString(4));
                classss = (rs.getString(5));
                phone.setText(rs.getString(6));
                email.setText(rs.getString(7));
                job.setValue(rs.getString(8));
                subject.setValue(rs.getString(9));
                salary.setText(rs.getString(10));
                age.setText(rs.getString(11));

                regdatee = (rs.getString("registerdate"));

            }
            System.out.println(classs);

            LocalDate localDate1 = LocalDate.parse(regdatee, formatter);

            regdate.setValue(localDate1);

            classs.getCheckModel().clearChecks();
            if (classss.contains("1/1")) {
                classs.getCheckModel().check("1/1");
            }
            if (classss.contains("1/2")) {
                classs.getCheckModel().check("1/2");
            }
            if (classss.contains("1/3")) {
                classs.getCheckModel().check("1/3");
            }
            if (classss.contains("2/1")) {
                classs.getCheckModel().check("2/1");
            }
            if (classss.contains("2/2")) {
                classs.getCheckModel().check("2/2");
            }
            if (classss.contains("2/3")) {
                classs.getCheckModel().check("2/3");
            }
            if (classss.contains("3/1")) {
                classs.getCheckModel().check("3/1");
            }
            if (classss.contains("3/2")) {
                classs.getCheckModel().check("3/2");
            }
            if (classss.contains("3/3")) {
                classs.getCheckModel().check("3/3");
            }
            if (classss.contains("4/1")) {
                classs.getCheckModel().check("4/1");
            }
            if (classss.contains("4/2")) {
                classs.getCheckModel().check("4/2");
            }
            if (classss.contains("4/3")) {
                classs.getCheckModel().check("4/3");
            }
            if (classss.contains("5/1")) {
                classs.getCheckModel().check("5/1");
            }
            if (classss.contains("5/2")) {
                classs.getCheckModel().check("5/2");
            }
            if (classss.contains("5/3")) {
                classs.getCheckModel().check("5/3");
            }
            if (classss.contains("6/1")) {
                classs.getCheckModel().check("6/1");
            }
            if (classss.contains("6/2")) {
                classs.getCheckModel().check("6/2");
            }
            if (classss.contains("6/3")) {
                classs.getCheckModel().check("6/3");
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
                Logger.getLogger(TeacherClass.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteTeacher(JFXTextField id, JFXTextField fname, JFXTextField lastname, String gender, CheckComboBox classs,
            JFXTextField phone, JFXTextField email, JFXComboBox job, JFXComboBox subject, JFXTextField salary,
            JFXTextField age, JFXDatePicker regdate) throws SQLException {

        Alert deleteStudent = new Alert(Alert.AlertType.CONFIRMATION);
        deleteStudent.setTitle("Confirmation dialog");
        deleteStudent.setContentText("Are you sure to delete");

        Optional<ButtonType> action = deleteStudent.showAndWait();

        try {
            if (action.get() == ButtonType.OK) {

                String query = "Delete from Teachers where ID =?  ";

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
            job.getSelectionModel().clearSelection();
            subject.getSelectionModel().clearSelection();
            //  classs.setPromptText("Please Select Class Of Student *");
            //    classs.sett("Please Select Class Of Student *");
            phone.clear();
            email.clear();
            salary.clear();
            classs.getCheckModel().clearChecks();
            age.clear();
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

    public void searchTeacher(JFXTextField t, JFXComboBox c, TableView te) throws SQLException {
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
            indicator = "phone";
        }
        if (indicator.equals("Gender")) {
            indicator = "gender";
        }
        if (indicator.equals("Age")) {
            indicator = "age";
        }
        if (indicator.equals("Job")) {
            indicator = "job";
        }
        for (int i = 0; i < te.getItems().size(); i++) { // to when i back to main menu by back button remove all fields on table
            te.getItems().clear();
        }
        try {
            String query;
            if (indicator.equals("gender")) {
                query = "Select * from Teachers where " + indicator + " like '" + t.getText() + "%' ";
            } else {
                query = "Select * from Teachers where " + indicator + " like '%" + t.getText() + "%' ";
            }
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

                data.add(new TeacherClass(
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
}
