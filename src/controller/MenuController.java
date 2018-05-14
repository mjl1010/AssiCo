package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button menu_planificacion;

    @FXML
    private Button menu_masters;

    @FXML
    private MenuItem menu_config;

    @FXML
    private MenuItem menu_gestion;

    @FXML
    private void irPlanificacion() {

    }

    @FXML
    private void irMasters() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intSelectCourse.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        stage = (Stage) menu_masters.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void irConfig() {

    }

    @FXML
    private void irGestion() {

    }
}
