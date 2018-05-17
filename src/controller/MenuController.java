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
import start.MainLogin;
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
        MainLogin.openStage(getClass().getResource("/view/intSelectCourse.fxml"), "Planificaciones - Selector de cursos", null);
        ((Stage) menu_masters.getScene().getWindow()).close();
    }

    @FXML
    private void irMasters() throws IOException {
        SelectCourse.postSelectMaster = null;
        MainLogin.openStage(getClass().getResource("/view/intMasters.fxml"), "Másters", null);
        ((Stage) menu_masters.getScene().getWindow()).close();
    }

    @FXML
    private void irConfig() {

    }

    @FXML
    private void irGestion() {

    }

    public static void refreshText() {
        if (main == null) return;
        if (main.menu_planificacion != null) main.menu_planificacion.setStyle(TextResponsive.getFontStyle("h2") + " -fx-text-fill: #000000; -fx-background-color: #0ab4c8; -fx-border-color: #0ab4c8; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.menu_masters != null) main.menu_masters.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #ffd600; -fx-border-color: #ffd600; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.menu_cuentas != null) main.menu_cuentas.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #0bd500; -fx-border-color: #0bd500; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.menu_config != null) main.menu_config.setStyle(TextResponsive.getFontStyle("h3"));
        if (main.menu_gestion != null) main.menu_gestion.setStyle(TextResponsive.getFontStyle("h3"));
    }
}
