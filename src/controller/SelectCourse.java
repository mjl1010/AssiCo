package controller;

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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectCourse implements Initializable {
    private static SelectCourse main;

    public static String postSelectMaster;

    @FXML
    VBox curso_col_1;

    @FXML
    VBox curso_col_2;

    @FXML
    Button select_course_nuevo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        if (HeaderController.main.titulo != null) HeaderController.main.titulo.setText("Seleccione un curso");
        generarMasters();
    }

    private void generarMasters() {
        DatosModel.connect(null);
        ArrayList<String> cursos = DatosModel.getCursos(LoginController.token.getUsuario().getUniversidad());
        DatosModel.closeConnection();

        if (cursos != null) {
            for (int i = 0; i<cursos.size(); i++) {
                Button btn = new Button();
                btn.setText(cursos.get(i));
                btn.setMinHeight(60);
                btn.setMinWidth(360);
                btn.setMaxWidth(360);
                btn.setTextAlignment(TextAlignment.CENTER);
                btn.setAlignment(Pos.CENTER);
                btn.setWrapText(true);
                String nombre = cursos.get(i);

                btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    postSelectMaster = nombre;
                    MainLogin.openStage(getClass().getResource("/view/intSelectMaster.fxml"), "Planificaciones - Selector de máster", null);
                    ((Stage) main.select_course_nuevo.getScene().getWindow()).close();
                });

                if (i%2 == 0) curso_col_1.getChildren().add(btn);
                else curso_col_2.getChildren().add(btn);
            }
            refreshText();
        } else AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "En esta universidad no hay cursos :/");
    }

    @FXML
    private void newCurso() {
        MainLogin.openStage(getClass().getResource("/view/intCourseRange.fxml"), "Planificaciones - Nuevo curso", null);
        ((Stage) select_course_nuevo.getScene().getWindow()).close();
    }

    public static void refreshText() {
        if (main == null) return;

        if (main.select_course_nuevo != null) main.select_course_nuevo.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        if (main.curso_col_1 != null)
            for (Node node : main.curso_col_1.getChildren()) {
                if (node instanceof Button) {
                    node.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");
                }
            }

        if (main.curso_col_2 != null)
            for (Node node : main.curso_col_2.getChildren()) {
                if (node instanceof Button) {
                    node.setStyle(TextResponsive.getFontStyle("h3") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");
                }
            }
    }
}
