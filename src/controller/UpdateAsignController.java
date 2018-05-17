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
import utilities.GridSesion;
import utilities.TabCalendarMaster;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.closeStage;
import static utilities.VariablesAndMethodsUtils.getSesion;

/**
 * Created by Michael
 */
public class UpdateAsignController implements Initializable {

    private static Parent root;
    private static Stage stage;
    private static TabCalendarMaster tcm;

    @FXML
    TextField txtAsign;


    public void savedAsig(MouseEvent mouseEvent) {
        if (!txtAsign.getText().isEmpty()) tcm.setAsign(txtAsign.getText());
        for (GridSesion gs : tcm.getaGridSesions()) {
            if (gs.getMiniGrid().equals(tcm.getGp_waiting())){
                if (getSesion(gs.getSesionID()).getMaster1() != null &&
                        getSesion(gs.getSesionID()).getMaster2() != null)
                setValorInCalendarVinculado(gs.getLblDateID().getId(), txtAsign.getText());
                break;
            }
        }
        closeStage(stage);
    }

    private void setValorInCalendarVinculado(String date, String newAsign) {
        for (GridSesion gs : tcm.getTcm_vinculado().getaGridSesions()) {
            if (gs.getLblDateID().getId().equals(date)){
                gs.getLblAsign().setText(newAsign);
                break;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void openScene(TabCalendarMaster tcm) throws IOException {
        this.tcm = tcm;
        root = FXMLLoader.load(getClass().getResource("/view/popUp/intUpdateAsig.fxml"));
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Editar Asignaturas");
        stage.setScene(new Scene(root, 329, 70));
        stage.show();
    }

}
