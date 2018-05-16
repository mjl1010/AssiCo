package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.TabCalendarMaster;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.closeStage;

public class UpdateAulaController implements Initializable {
    private static Parent root;
    private static Stage stage;
    private static TabCalendarMaster tcm;

    @FXML
    TextField txtAula;

    public void savedAsig(MouseEvent mouseEvent) {
        if (!txtAula.getText().isEmpty()){
            tcm.setAulaSes(Integer.parseInt(txtAula.getText()));
        }
        closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void openScene(TabCalendarMaster tcm) throws IOException {
        this.tcm = tcm;
        root = FXMLLoader.load(getClass().getResource("/view/popUp/intUpdateAula.fxml"));
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Editar Aula");
        stage.setScene(new Scene(root, 329, 70));
        stage.show();
    }
}