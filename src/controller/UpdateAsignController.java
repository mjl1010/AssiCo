package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michael
 */
public class UpdateAsignController implements Initializable {

    Stage stage;

    @FXML
    TextField txtAsign;


    public void savedAsig(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/intUpdateAsig.fxml"));
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Lista de Sesiones");
        stage.setScene(new Scene(root, 329, 70));
        stage.show();
    }
}
