/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanegementsssss;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author amir
 */
public class MainSceneFXMLController implements Initializable {

    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private Button StudentButton;

    String defultStyle = "-fx-border-width: 0.5px 0.5px 0.5px 0.5px;"
            + "-fx-border-color :#d9d9d9;" + "-fx-text-fill:  #FFFFFF;" + "-fx-graphic-text-gap : 40";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#D80027;" + "-fx-graphic-text-gap : 40";
    @FXML
    private Button Dashboardbutton;
    @FXML
    private Button TeachersButtom;
    @FXML
    private Button ParentsButton;
    @FXML
    private Button SubjectsButton;
    @FXML
    private Button ResuktsButton;

    @FXML
    private void student() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/StudentPackage/StudentFXML.fxml"));
        Scene scene = StudentButton.getScene();
        root.translateYProperty().set(scene.getHeight());
        SetActive(StudentButton, Dashboardbutton, TeachersButtom, ParentsButton, ResuktsButton, SubjectsButton);

        MainAnchor.getChildren().clear();

        MainAnchor.getChildren().setAll(root);
        // MainAnchorPane.setLayoutY(0);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.8), kv);
        timeline.getKeyFrames().add(kf);

        timeline.play();

    }

    @FXML
    private void parents() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/ParentPackage/ParentFXML.fxml"));
        Scene scene = ParentsButton.getScene();
        root.translateYProperty().set(scene.getHeight());
        SetActive(ParentsButton, Dashboardbutton, TeachersButtom, StudentButton, ResuktsButton, SubjectsButton);

        MainAnchor.getChildren().clear();

        MainAnchor.getChildren().setAll(root);
        // MainAnchorPane.setLayoutY(0);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.8), kv);
        timeline.getKeyFrames().add(kf);

        timeline.play();

    }
    @FXML
    private void teachers() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/TeacherPackage/TeacherFXML.fxml"));
        Scene scene = TeachersButtom.getScene();
        root.translateYProperty().set(scene.getHeight());
        SetActive(TeachersButtom, Dashboardbutton, ParentsButton, StudentButton, ResuktsButton, SubjectsButton);

        MainAnchor.getChildren().clear();

        MainAnchor.getChildren().setAll(root);
        // MainAnchorPane.setLayoutY(0);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.8), kv);
        timeline.getKeyFrames().add(kf);

        timeline.play();

    }

    private void SetActive(Button b1, Button b2, Button b3, Button b4, Button b5, Button b6) {
        b1.setStyle(activeStyle);
        b2.setStyle(defultStyle);
        b3.setStyle(defultStyle);
        b4.setStyle(defultStyle);
        b5.setStyle(defultStyle);
        b6.setStyle(defultStyle);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StudentButton.setStyle(defultStyle);
        Dashboardbutton.setStyle(defultStyle);
        TeachersButtom.setStyle(defultStyle);
        ParentsButton.setStyle(defultStyle);
        SubjectsButton.setStyle(defultStyle);
        ResuktsButton.setStyle(defultStyle);

    }

}
