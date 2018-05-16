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
import model.GridSesion;
import model.TabCalendarMaster;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.aTiposAula;
import static utilities.VariablesAndMethodsUtils.closeStage;
import static utilities.VariablesAndMethodsUtils.getSesion;

/**
 * Created by MIchael
 */
public class UpdateTipoAulaController implements Initializable {

    private static Parent root;
    private static Stage stage;
    private static TabCalendarMaster tcm;


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
        if (!cbo_tipoAula.getValue().equals(cbo_tipoAula.getItems().get(0)))
        setTipoAulaSet(cbo_tipoAula.getValue().toString());
        closeStage(stage);
    }

    public void setTipoAulaSet(String newTipAul) {
        for (GridSesion gs : tcm.getaGridSesions()) {
            if (gs.getMiniGrid().equals(tcm.getGp_waiting())) {
                getSesion(gs.getSesionID()).setTipoAula(newTipAul);
                gs.getCbo_tipoAula().setValue((newTipAul));
                if (getSesion(gs.getSesionID()).getMaster1() != null &&
                        getSesion(gs.getSesionID()).getMaster2() != null)
                    setCboTipoAulaInCalendarVinculado(gs.getLblDateID().getId(), newTipAul);
                break;
            }
        }
    }

    private void setCboTipoAulaInCalendarVinculado(String date, String newTipAul) {
        for (GridSesion gs : tcm.getTcm_vinculado().getaGridSesions()) {
            if (gs.getLblDateID().getId().equals(date)){
                gs.getCbo_tipoAula().setValue(newTipAul);
                break;
            }
        }
    }

    public void openScene(TabCalendarMaster tcm) throws IOException {
        this.tcm = tcm;
        root = FXMLLoader.load(getClass().getResource("/view/popUp/intUpdateTipoAula.fxml"));
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Editar Tipo de Aula");
        stage.setScene(new Scene(root, 329, 70));
        stage.show();
    }
}
