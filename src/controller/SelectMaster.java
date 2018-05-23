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
        if (HeaderController.main.titulo != null) HeaderController.main.titulo.setText("Seleccione un máster del curso " + SelectCourse.postSelectMaster);
        generarMasters();
    }

    private void generarMasters() {
        DatosModel.connect(null);
        ArrayList<Master> masters = DatosModel.getMasters(LoginController.token.getUsuario().getUniversidad());
        ArrayList<Master> mastersLeidos = new ArrayList<>();
        DatosModel.closeConnection();

        if (masters != null) {
            masters.sort((s, t1) -> t1.getNombre().compareToIgnoreCase(s.getNombre()));

            int i = 0;
            for (Master master : masters) {
                if (mastersLeidos.contains(master) || (master.getMasterVinculado() != null && mastersLeidos.contains(master.getMasterVinculado()))) continue;
                mastersLeidos.add(master);
                if (master.getMasterVinculado() != null) mastersLeidos.add(master.getMasterVinculado());

                Button btn = new Button();
                if (master.getMasterVinculado() != null) btn.setText(master.getNombre() + "\n\n" + master.getMasterVinculado().getNombre());
                else btn.setText(master.getNombre());
                btn.setMinHeight(60);
                btn.setMinWidth(363);
                btn.setMaxWidth(363);
                btn.setTextAlignment(TextAlignment.CENTER);
                btn.setAlignment(Pos.CENTER);
                btn.setWrapText(true);

                if (SelectCourse.postSelectMaster != null) {
                    btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        DatosModel.connect(null);
                        planificacionCalendario = DatosModel.getPlanificacionCalendarios(LoginController.token.getUsuario(), SelectCourse.postSelectMaster, master, master.getMasterVinculado());
                        DatosModel.closeConnection();

                        SesionsCalendarController.nombre_curso = SelectCourse.postSelectMaster;
                        VariablesAndMethodsUtils.init(planificacionCalendario);

                        MainLogin.openStage(getClass().getResource("/view/intSesionsCalendar.fxml"), "Planificaciones - Calendario " + SelectCourse.postSelectMaster, null);
                        ((Stage) main.smaster_atras.getScene().getWindow()).close();
                    });
                }

                if (i%2 == 0) smaster_col_1.getChildren().add(btn);
                else smaster_col_2.getChildren().add(btn);

                i++;
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

        if (main.smaster_atras != null) main.smaster_atras.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #ff5959; -fx-border-color: transparent; -fx-background-radius: 4px;");

        if (main.smaster_col_1 != null)
        for (Node node : main.smaster_col_1.getChildren()) {
            if (node instanceof Button) {
                node.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: transparent; -fx-background-radius: 4px;");
                //((Button)node).setWrapText(true);
            }
        }

        if (main.smaster_col_2 != null)
        for (Node node : main.smaster_col_2.getChildren()) {
            if (node instanceof Button) {
                node.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: transparent; -fx-background-radius: 4px;");
                //((Button)node).setWrapText(true);
            }
        }
    }
}
