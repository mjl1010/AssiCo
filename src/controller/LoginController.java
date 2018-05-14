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
import model.DatosModel;
import entity.Token;
import utilities.AlertHelper;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usuario;

    @FXML
    private PasswordField clave;

    @FXML
    private Button acceder;

    @FXML
    private Hyperlink olvido;

    protected static Token token;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

            acceder.setStyle("-fx-text-fill: #000000; -fx-background-color: #00aa00; -fx-border-color: #cccccc; -fx-border-radius: 4px; -fx-background-radius: 4px;");
            acceder.setDisable(true);

            token = DatosModel.comprobarCuenta(usuario.getText(), clave.getText());

            if (token == null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "Usuario o clave incorrectos.");
                acceder.setStyle("-fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #cccccc; -fx-border-radius: 4px; -fx-background-radius: 4px;");
                acceder.setDisable(false);

            } else {
                //TODO Lanzar MenuController

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intMenu.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage) owner;
                stage.close();

                /*Parent root = FXMLLoader.load(getClass().getResource("../view/intSesionsCalendar.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                Scene scene = new Scene(root, 1000, 650);
                stage.setTitle("Holydays Stage");
                stage.setScene(scene);
                stage.show();
                stage = (Stage) owner;
                stage.close();*/
            }

            DatosModel.closeConnection();
        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", ex.getMessage());
        }
    }
}
