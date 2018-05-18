package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.DatosModel;
import start.MainLogin;
import utilities.AlertHelper;
import utilities.TextResponsive;
import utilities.VariablesAndMethodsUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {
    protected static HeaderController main;

    @FXML
    Button menu_btn;

    @FXML
    Label titulo;

    @FXML
    private Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        refreshText();
    }

    @FXML
    private void irMenu(ActionEvent event) throws IOException {
        // Abrir menú y cerrar parent
        MainLogin.openStage(getClass().getResource("/view/intMenu.fxml"), "Menú", null);
        ((Stage) menu_btn.getScene().getWindow()).close();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        // Cerrar sesión, abrir login y cerrar parent
        Window owner = logout_btn.getScene().getWindow();
        DatosModel.connect(owner);

        if (DatosModel.logoutToken(LoginController.token)) {
            Properties p = new Properties();
            p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
            p.setProperty("token", "");
            p.store(new FileWriter(VariablesAndMethodsUtils.PATH_PROPERTIES), "Token removed");

            MainLogin.openStage(getClass().getResource("/view/intLogin.fxml"), "Asistente de Coordinación Academica", null);
            ((Stage) owner).close();
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Error - AssiCo", "No se ha podido cerrar la sesión.");
        }

        DatosModel.closeConnection();
    }

    public static void refreshText() {
        if (main == null) return;
        if (main.titulo != null) main.titulo.setStyle(TextResponsive.getFontStyle("h3"));
        if (main.menu_btn != null) main.menu_btn.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #3f9eff; -fx-border-color: #3f9eff; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.logout_btn != null) main.logout_btn.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #ff9f3f; -fx-border-color: #ff9f3f; -fx-border-radius: 4px; -fx-background-radius: 4px;");
    }
}
