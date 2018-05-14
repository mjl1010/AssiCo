package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.DatosModel;
import start.MainLogin;
import utilities.AlertHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeaderFooterController implements Initializable {
    @FXML
    private Button menu_btn;

    @FXML
    private Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void irMenu(ActionEvent event) throws IOException {
        //TODO Abrir menú y cerrar parent
        Window owner = menu_btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intMenu.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        stage = (Stage) owner;
        stage.close();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        //TODO Cerrar sesión, abrir login y cerrar parent
        Window owner = logout_btn.getScene().getWindow();
        DatosModel.connect(owner);

        if (DatosModel.logoutToken(LoginController.token)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/intLogin.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) owner;
            stage.close();
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Error - AssiCo", "No se ha podido cerrar la sesión.");
        }

        DatosModel.closeConnection();
    }
}
