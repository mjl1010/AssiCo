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
        if (event.getCode().equals(KeyCode.ENTER))
        try {
            alAcceder();
        } catch (IOException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "Error al carregar la següent pantalla!");
        }
    }

    @FXML
    private void alAcceder(ActionEvent event) {
        try {
            alAcceder();
        } catch (IOException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "Error al carregar la següent pantalla!");
        }
    }

    private void alAcceder() throws IOException {
        Window owner = acceder.getScene().getWindow();
        if (usuario.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "No s'ha introduït ningun usuari!");
            return;
        } else if (clave.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "No s'ha introduït ninguna contrasenya!");
            return;
        }

//        DatosModel.connect(owner);

        acceder.setStyle("-fx-base: #00AA00;");
        acceder.setDisable(true);

        Map<String, String> datos = new HashMap<>();
        datos.put("usuario", usuario.getText());
        datos.put("clave", clave.getText());

        token = DatosModel.comprobarCuenta(datos);
        if (token == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "Usuari o clau incorrectes!\nTambé és possible que sigui un error de conexió.");
            acceder.setStyle("");
            acceder.setDisable(false);
            DatosModel.resetConnection();
        } else {
            //TODO Lanzar MenuController
            Parent root = FXMLLoader.load(getClass().getResource("../view/intSesionsCalendar.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            Scene scene = new Scene(root, 1000, 650);
            stage.setTitle("Holydays Stage");
            stage.setScene(scene);
            stage.show();
            stage = (Stage) owner;
            stage.close();
            DatosModel.closeConnection();
        }
    }
}
