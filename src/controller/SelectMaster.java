package controller;

import entity.DiaPlanificado;
import entity.Master;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.DatosModel;
import start.MainLogin;
import utilities.AlertHelper;
import utilities.TextResponsive;
import utilities.VariablesAndMethodsUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectMaster implements Initializable {
    private static SelectMaster main;

    public static ArrayList<DiaPlanificado> planificacionCalendario;

    @FXML
    VBox smaster_col_1;

    @FXML
    VBox smaster_col_2;

    @FXML
    Button smaster_atras;

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

                Master m1 = masters.get(i);
                Master m2 = masters.get(i).getMasterVinculado();

                if (SelectCourse.postSelectMaster != null) {
                    btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        DatosModel.connect(null);
                        planificacionCalendario = DatosModel.getPlanificacionCalendarios(LoginController.token.getUsuario(), SelectCourse.postSelectMaster, m1, m2);
                        DatosModel.closeConnection();

                        // TODO Pasárle planificacion y parametrizar
                        VariablesAndMethodsUtils.init();

                        MainLogin.openStage(getClass().getResource("/view/intSesionsCalendar.fxml"), "Planificaciones - Calendario " + SelectCourse.postSelectMaster, null);
                        ((Stage) main.smaster_atras.getScene().getWindow()).close();
                    });
                }

                if (i%2 == 0) smaster_col_1.getChildren().add(btn);
                else smaster_col_2.getChildren().add(btn);
            }
            refreshText();
        } else AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "En esta universidad no hay cursos :/");
    }

    @FXML
    private void back() {
        MainLogin.openStage(getClass().getResource("/view/intSelectCourse.fxml"), "Planificaciones - Selector de cursos", null);
        ((Stage) smaster_atras.getScene().getWindow()).close();
    }

    public static void refreshText() {
        if (main == null) return;

        if (main.smaster_atras != null) main.smaster_atras.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        if (main.smaster_col_1 != null)
        for (Node node : main.smaster_col_1.getChildren()) {
            if (node instanceof Button) {
                node.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");
                //((Button)node).setWrapText(true);
            }
        }

        if (main.smaster_col_2 != null)
        for (Node node : main.smaster_col_2.getChildren()) {
            if (node instanceof Button) {
                node.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");
                //((Button)node).setWrapText(true);
            }
        }
    }
}
