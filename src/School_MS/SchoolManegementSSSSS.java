/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanegementsssss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author amir
 */
public class SchoolManegementSSSSS extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("MainSceneFXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("School Management System");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/icon2/school.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
