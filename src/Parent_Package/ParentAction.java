/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParentPackage;

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

/*
 * @author amir
 */
public class ParentAction {
    Connection con = SqliteConnection.Connector();
    PreparedStatement ps;
    ResultSet rs;
    final ObservableList <ParentClass> data = FXCollections.observableArrayList();
    private AlertMaker alert = new AlertMaker();
    
    
    public void addParent ( JFXTextField id , JFXTextField fname , JFXTextField lname , String gender , 
                            JFXTextField PhoneNum1 , JFXTextField PhoneNum2 , JFXTextField email , 
                            JFXTextField address , JFXDatePicker birthdate )throws SQLException {
    String query = " Insert into Parents (ID,FNAME,LNAME,GENDER,NUM1,NUM2,EMAIL,ADDRESS,BIRTHDATE,REGBY)"
                  +"VALUES (?,?,?,?,?,?,?,?,?,?)";
    try {
        ps = con.prepareStatement(query);
        ps.setString(1, id.getText());        
        ps.setString(2,fname.getText());
        ps.setString(3,lname.getText());
        ps.setString(4,gender);
        ps.setString(5,PhoneNum1.getText());
        ps.setString(6,PhoneNum2.getText());
        ps.setString(7,email.getText());
        ps.setString(8,address.getText());
        ps.setString(9,birthdate.getValue().toString());
        ps.setString(10,FXMLDocumentController.UserNameSave);
        ps.execute();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Parent " + fname.getText() + " " + lname.getText() + " " + "has been added");
        alert.setTitle("Success !!");
        alert.setHeaderText(null);
        alert.showAndWait();
        ps.close();
        
        id.clear();
        fname.clear();
        lname.clear();
        gender="null";
        PhoneNum1.clear();
        PhoneNum2.clear();
        email.clear();
        address.clear();
        birthdate.setValue(LocalDate.of(1995, Month.JANUARY, 1));
        
    } catch (SQLException e){
            ps.close();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Add Parent " + fname.getText() + " " + lname.getText() + " " + "has been fail");
            alert.setTitle("Failed !!");
            alert.setHeaderText(null);
            alert.showAndWait();
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            ps.close();
            
            } 
}
    
    public void updateParent ( JFXTextField id , JFXTextField fname , JFXTextField lname , String gender , 
                            JFXTextField PhoneNum1 , JFXTextField PhoneNum2 , JFXTextField email , 
                            JFXTextField address , JFXDatePicker birthdate )throws SQLException {
        String query = " Update Parents set FNAME=?, LNAME=?, GENDER=?, NUM1=?, NUM2=?, EMAIL=?, ADDRESS=?, BIRTHDATE=?"
                       + "where ID ='" + id.getText() + "' ";
        try {
                   ps = con.prepareStatement(query);
                   ps.setString(1, fname.getText());
                   ps.setString(2, lname.getText());
                   ps.setString(3, gender);
                   ps.setString(4, PhoneNum1.getText());
                   ps.setString(5, PhoneNum2.getText());
                   ps.setString(6, email.getText());
                   ps.setString(7, address.getText());
                   ps.setString(8, birthdate.getValue().toString());
                   ps.execute();
                   
                   Alert alert = new Alert(Alert.AlertType.INFORMATION, "Parent " + fname.getText() + " " + lname.getText() + " " + "has been updated");
                   alert.setTitle("Success !!");
                   alert.setHeaderText(null);
                   alert.showAndWait();
                   ps.close();
                   
                   fname.clear();
                   lname.clear();
                   gender="null";
                   PhoneNum1.clear();
                   PhoneNum2.clear();
                   email.clear();
                   address.clear();
                   birthdate.setValue(LocalDate.of(1995, Month.JANUARY, 1));
                   
        } catch ( SQLException e) {
            ps.close();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Update Parent " + fname.getText() + " " + lname.getText() + " " + "has been failed");
            alert.setTitle("Failed !!");
            alert.setHeaderText(null);
            alert.showAndWait();
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            ps.close();
        }
    }
    public void deleteParent ( JFXTextField id , JFXTextField fname , JFXTextField lname , String gender , 
                            JFXTextField PhoneNum1 , JFXTextField PhoneNum2 , JFXTextField email , 
                            JFXTextField address , JFXDatePicker birthdate )throws SQLException {
        Alert deleteParent = new Alert(Alert.AlertType.CONFIRMATION);
        deleteParent.setTitle("Confirmation dialog");
        deleteParent.setContentText("Are you sure to delete");
        Optional<ButtonType> action = deleteParent.showAndWait();
        
        try {
            if (action.get() == ButtonType.OK) {

                String query = "Delete from Parents where ID =?  ";
                ps = con.prepareStatement(query);
                ps.setString(1, id.getText());
                ps.executeUpdate();
            } 
            
            ps.close();
            fname.clear();
            lname.clear();
            gender="null";
            PhoneNum1.clear();
            PhoneNum2.clear();
            email.clear();
            address.clear();
            birthdate.setValue(LocalDate.of(1995, Month.JANUARY, 1));
            
        } catch ( SQLException e) {
            Alert CreateNewUserErrorAlert = new Alert(Alert.AlertType.ERROR);
            CreateNewUserErrorAlert.setHeaderText(null);
            CreateNewUserErrorAlert.setContentText(e.getMessage());
            CreateNewUserErrorAlert.showAndWait();
            System.err.println(e);
            ps.close();

        }
    }
    
     public void loadTable(TableView table) throws SQLException {
        for (int i = 0; i < table.getItems().size(); i++) { // to when i back to main menu by back button remove all fields on table
            table.getItems().clear();
        }
        try {
            String query = "Select * from Parents ";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                data.add(new ParentClass (
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
                table.setItems(data);
            }
            ps.close();
            rs.close();

        } catch ( SQLException eViewAllParent) {
            System.err.println(eViewAllParent);
            Alert CreateNewUserErrorAlert = new Alert(Alert.AlertType.ERROR);
            CreateNewUserErrorAlert.setHeaderText(null);
            CreateNewUserErrorAlert.setContentText(eViewAllParent.getMessage());
            CreateNewUserErrorAlert.showAndWait();
         ps.close();
          rs.close();
        }
    }
     
     public void loadfromtable ( JFXTextField id , JFXTextField fname , JFXTextField lname , Label gender , 
                                 JFXTextField PhoneNum1 , JFXTextField PhoneNum2 , JFXTextField email , 
                                 JFXTextField address , JFXDatePicker birthdate, TableView table )throws SQLException {
         
         try {

            ParentClass pc = (ParentClass)table.getSelectionModel().getSelectedItem();
            String query = "Select * from Parents where ID = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, pc.getId());

            rs = ps.executeQuery();
            String birth = "";
            String reg;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          while (rs.next()) {
                id.setText(rs.getString(1));
                fname.setText(rs.getString(2));
                lname.setText(rs.getString(3));
                gender.setText(rs.getString(4));
                PhoneNum1.setText(rs.getString(5));
                PhoneNum2.setText(rs.getString(6));
                email.setText(rs.getString(7));
                address.setText(rs.getString(8));
                birth = (rs.getString(9));
                reg = (rs.getString(10));
          }
          
          LocalDate localDate1 = LocalDate.parse(birth, formatter);
          birthdate.setValue(localDate1);
          ps.close();
          rs.close();
           } catch (SQLException e) {
            System.err.println(e);
            Alert CreateNewUserErrorAlert = new Alert(Alert.AlertType.ERROR);
            CreateNewUserErrorAlert.setHeaderText(null);
            CreateNewUserErrorAlert.setContentText(e.getMessage());
            CreateNewUserErrorAlert.showAndWait();
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ParentClass.class.getName()).log(Level.SEVERE, null, ex);
            }

     }
     }
     
     public void searchParent(JFXTextField t, JFXComboBox c, TableView te) throws SQLException {
         String indicator = (String) c.getValue();
         if (indicator.equals("ID")) {
            indicator = "ID";
        }
        if (indicator.equals("First Name")) {
            indicator = "FNAME";
        }
        if (indicator.equals("Last Name")) {
            indicator = "LNAME";
        }
        if (indicator.equals("Phone Number1")) {
            indicator = "NUM1";
        }
        if (indicator.equals("Phone Number2")) {
            indicator = "NUM2";
        }
        if (indicator.equals("Gender")) {
            indicator = "GENDER";
        }
        if (indicator.equals("Birth Date")) {
            indicator = "BIRTHDATE";
        }
       for (int i = 0; i < te.getItems().size(); i++) { 
            te.getItems().clear();
        }
         try {
            String query;
            if (indicator.equals("GENDER")) {
                query = "Select * from Parents where " + indicator + " like '" + t.getText() + "%' ";
            }

            else query = "Select * from Parents where " + indicator + " like '%" + t.getText() + "%' ";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

                data.add(new ParentClass(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
                te.setItems(data);

            }

            ps.close();
            rs.close();

        } catch (SQLException eViewAllParent) {
            System.err.println(eViewAllParent);
            Alert CreateNewUserErrorAlert = new Alert(Alert.AlertType.ERROR);
            CreateNewUserErrorAlert.setHeaderText(null);
            CreateNewUserErrorAlert.setContentText(eViewAllParent.getMessage());
            CreateNewUserErrorAlert.showAndWait();
            ps.close();
            rs.close();
        }

    }
         
         
     
}
