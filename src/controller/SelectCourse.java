package controller;

import entity.Master;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.DatosModel;
import start.MainLogin;
import utilities.AlertHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectCourse implements Initializable {
    @FXML
    VBox master_col_1;

    @FXML
    VBox master_col_2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generarMasters();
    }

    private void generarMasters() {
        DatosModel.connect(null);
        ArrayList<Master> masters = DatosModel.getMasters(LoginController.token.getUsuario().getUniversidad());
        DatosModel.closeConnection();

        if (masters != null) {
            for (int i = 0; i<masters.size(); i++) {

                Button btn = new Button();
                Label label = new Label(masters.get(i).getNombre());
                label.setStyle("-fx-effect: dropshadow( one-pass-box , white , 40 , 0.9 , 0 , 0 )");
                btn.setGraphic(label);
                String color = MainLogin.generateRandomColor();
                btn.setStyle("-fx-text-fill: #000000; -fx-background-color: " + color + "; -fx-border-color: " + color + "; -fx-border-radius: 4px; -fx-background-radius: 4px;");
                btn.setMinHeight(60);
                btn.setAlignment(Pos.CENTER);

                if (i%2 == 0) master_col_1.getChildren().add(btn);
                else master_col_2.getChildren().add(btn);
            }
        } else AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "En esta universidad no hay másters :/");
    }
}
