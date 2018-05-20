package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import start.MainLogin;
import sun.rmi.runtime.Log;
import utilities.TextResponsive;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private static MenuController main;

    @FXML
    private Button menu_planificacion, menu_masters, menu_docentes;

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
        HeaderController.main.titulo.setText("Menú principal");
        HeaderController.main.menu_btn.setVisible(false);
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
    private void irDocentes() {

    }

    @FXML
    private void irConfig() {

    }

    @FXML
    private void irGestion() {

    }

    public static void refreshText() {
        if (main == null) return;
        main.menu_planificacion.setStyle(TextResponsive.getFontStyle("h2") + " -fx-text-fill: #000000; -fx-background-color: #0ab4c8; -fx-border-color: #0ab4c8; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        main.menu_masters.setStyle(TextResponsive.getFontStyle("h2") + " -fx-text-fill: #000000; -fx-background-color: #ffd600; -fx-border-color: #ffd600; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        main.menu_cuentas.setStyle(TextResponsive.getFontStyle("h2") + " -fx-text-fill: #000000; -fx-background-color: #0bd500; -fx-border-color: #0bd500; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        main.menu_docentes.setStyle(TextResponsive.getFontStyle("h2") + " -fx-text-fill: #000000; -fx-background-color: #ff76ae; -fx-border-color: #ff76ae; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        main.menu_config.setStyle(TextResponsive.getFontStyle("h3"));
        main.menu_gestion.setStyle(TextResponsive.getFontStyle("h3"));

        ArrayList<Node> botones = new ArrayList<>();
        botones.add(main.menu_planificacion);
        botones.add(main.menu_cuentas);
        botones.add(main.menu_masters);
        botones.add(main.menu_docentes);

        for (Node n : botones) {
            if (n instanceof ButtonBase) {
                ((ButtonBase) n).setPrefHeight(90*(TextResponsive.getH1()/26.));
                ((ButtonBase) n).setPrefWidth(250*(TextResponsive.getH1()/26.));
            }
        }
    }
}
