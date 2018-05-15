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
    private Button menu_btn;

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
        Window owner = menu_btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intMenu.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("AssiCo - Menú");
        stage.show();
        stage = (Stage) owner;
        stage.close();
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

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intLogin.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("AssiCo - Asistente de Coordinación Academica");
            stage.show();
            stage = (Stage) owner;
            stage.close();
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Error - AssiCo", "No se ha podido cerrar la sesión.");
        }

        DatosModel.closeConnection();
    }

    public static void refreshText() {
        if (main == null) return;
        if (main.titulo != null) main.titulo.setStyle(TextResponsive.getFontStyle("h3"));
        if (main.menu_btn != null) main.menu_btn.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.logout_btn != null) main.logout_btn.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");
    }
}
