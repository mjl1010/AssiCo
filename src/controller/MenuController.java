package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;
import utilities.TextResponsive;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private static MenuController main;

    @FXML
    private Button menu_planificacion;

    @FXML
    private Button menu_masters;

    @FXML
    private MenuButton menu_cuentas;

    @FXML
    private MenuItem menu_config;

    @FXML
    private MenuItem menu_gestion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        refreshText();
        HeaderController.main.titulo.setText("Menú");
    }

    @FXML
    private void irPlanificacion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intSelectCourse.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("AssiCo - Planificaciones - Selector de cursos");
        stage.show();
        stage = (Stage) menu_masters.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void irMasters() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intMasters.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("AssiCo - Másters");
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

    public static void refreshText() {
        if (main == null) return;
        if (main.menu_planificacion != null) main.menu_planificacion.setStyle(TextResponsive.getFontStyle("h2") + " -fx-text-fill: #000000; -fx-background-color: #78a199; -fx-border-color: #78a199; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.menu_masters != null) main.menu_masters.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #ffb84c; -fx-border-color: #ffb84c; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.menu_cuentas != null) main.menu_cuentas.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #9fdaf5; -fx-border-color: #9fdaf5; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.menu_config != null) main.menu_config.setStyle(TextResponsive.getFontStyle("h3"));
        if (main.menu_gestion != null) main.menu_gestion.setStyle(TextResponsive.getFontStyle("h3"));
    }
}
