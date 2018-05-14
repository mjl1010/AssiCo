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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.closeStage;
import static utilities.VariablesAndMethodsUtils.setContenido;

/**
 * Created by Michael
 */
public class UpdateContenidoController implements Initializable {
    private static Parent root;
    private static Stage stage;

    @FXML
    TextField txtCont;

    public void savedAsig(MouseEvent mouseEvent) {
        if (!txtCont.getText().isEmpty()){
            setContenido(txtCont.getText());
        }
        closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void openScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/intUpdateCont.fxml"));
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Editar Contenido");
        stage.setScene(new Scene(root, 329, 70));
        stage.show();
    }
}
