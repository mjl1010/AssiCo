package controller;

import entity.Master;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.DatosModel;
import utilities.AlertHelper;
import utilities.TextResponsive;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectMaster implements Initializable {
    private static SelectMaster main;

    @FXML
    VBox master_col_1;

    @FXML
    VBox master_col_2;

    @FXML
    Button masters_agregar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        if (HeaderController.main.titulo != null) HeaderController.main.titulo.setText("Seleccione un máster");
        generarMasters();
    }

    private void generarMasters() {
        DatosModel.connect(null);
        ArrayList<Master> masters = DatosModel.getMasters(LoginController.token.getUsuario().getUniversidad());
        DatosModel.closeConnection();

        if (masters != null) {
            for (int i = 0; i<masters.size(); i++) {
                Button btn = new Button();
                if (masters.get(i).getMasterVinculado() != null) btn.setText(masters.get(i).getNombre() + "\n" + masters.get(i).getMasterVinculado().getNombre());
                else btn.setText(masters.get(i).getNombre());
                btn.setMinHeight(60);
                btn.setMinWidth(360);
                btn.setMaxWidth(360);
                btn.setTextAlignment(TextAlignment.CENTER);
                btn.setAlignment(Pos.CENTER);
                btn.setWrapText(true);

                if (i%2 == 0) master_col_1.getChildren().add(btn);
                else master_col_2.getChildren().add(btn);
            }
            refreshText();
        } else AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "En esta universidad no hay cursos :/");
    }

    @FXML
    private void newMaster() {

    }

    public static void refreshText() {
        if (main == null) return;

        if (main.masters_agregar != null) main.masters_agregar.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        if (main.master_col_1 != null)
        for (Node node : main.master_col_1.getChildren()) {
            if (node instanceof Button) {
                node.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");
                //((Button)node).setWrapText(true);
            }
        }

        if (main.master_col_2 != null)
        for (Node node : main.master_col_2.getChildren()) {
            if (node instanceof Button) {
                node.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");
                //((Button)node).setWrapText(true);
            }
        }
    }
}
