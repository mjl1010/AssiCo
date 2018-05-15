package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.Dato;
import model.DatosModel;
import entity.Token;
import utilities.AlertHelper;
import utilities.TextResponsive;
import utilities.VariablesAndMethodsUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.PATH_PROPERTIES;

public class LoginController implements Initializable {
    private static LoginController main;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField clave;

    @FXML
    private Button acceder;

    @FXML
    private Hyperlink olvido;

    @FXML
    private Label legal;

    protected static Token token;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main = this;
        refreshText();

        Properties p = new Properties();
        try {
            p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
            String stoken = p.getProperty("token");

            DatosModel.connect(null, null);
            token = DatosModel.getToken(stoken);
            DatosModel.closeConnection();

            if (token != null && token.isActivo()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intMenu.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("AssiCo - Menú");
                stage.show();
                stage = (Stage) acceder.getScene().getWindow();
                stage.close();
            } else if (token != null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "No se ha podido verificar la sesión anterior, por favor, accede de nuevo.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void alAccederTeclado(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) alAcceder();
    }

    @FXML
    private void alAcceder(ActionEvent event) {
        alAcceder();
    }

    private void alAcceder() {
        try {
            Window owner = acceder.getScene().getWindow();
            if (usuario.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "Se necesita el nombre del usuario.");
                return;
            } else if (clave.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "Todas nuestras cuentas tienen clave.");
                return;
            }

            DatosModel.connect(owner);

            acceder.setDisable(true);

            token = DatosModel.comprobarCuenta(usuario.getText(), clave.getText());

            if (token == null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "Usuario o clave incorrectos.");
                acceder.setDisable(false);
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intMenu.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("AssiCo - Menú");
                stage.show();
                stage = (Stage) owner;
                stage.close();

                Properties p = new Properties();
                p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
                p.setProperty("token", token.getToken());
                p.store(new FileWriter(VariablesAndMethodsUtils.PATH_PROPERTIES), "first comment");
            }

            DatosModel.closeConnection();
        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", ex.getMessage());
            //ex.printStackTrace();
            DatosModel.closeConnection();
        }
    }

    public static void refreshText() {
        if (main == null) return;
        if (main.usuario != null) main.usuario.setStyle(TextResponsive.getFontStyle("h5"));
        if (main.clave != null) main.clave.setStyle(TextResponsive.getFontStyle("h5"));
        if (main.olvido != null) main.olvido.setStyle(TextResponsive.getFontStyle("h6"));
        if (main.acceder != null)main.acceder.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #cccccc; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        if (main.legal != null) main.legal.setStyle(TextResponsive.getFontStyle("h6"));
    }
}
