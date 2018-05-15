package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.aTiposAula;
import static utilities.VariablesAndMethodsUtils.closeStage;
import static utilities.VariablesAndMethodsUtils.setTipoAulaSet;

/**
 * Created by MIchael
 */
public class UpdateTipoAulaController implements Initializable {

    private static Parent root;
    private static Stage stage;

    @FXML
    ComboBox cbo_tipoAula;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCbox();
    }

    private void fillCbox() {
        cbo_tipoAula.getItems().addAll(aTiposAula);
    }

    public void savedTipoAula(MouseEvent mouseEvent) {
        setTipoAulaSet(cbo_tipoAula.getValue().toString());
        closeStage(stage);
    }

    public void openScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/popUp/intUpdateTipoAula.fxml"));
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Editar Tipo de Aula");
        stage.setScene(new Scene(root, 329, 70));
        stage.show();
    }
}
