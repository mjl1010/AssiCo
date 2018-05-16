package controller;

import entity.Docente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import static utilities.VariablesAndMethodsUtils.*;


/**
 * Created by Michael
 */
public class UpdateDocenteController implements Initializable {

    private static Parent root;
    private static Stage stage;
    private static TabCalendarMaster tcm;

    @FXML
    ComboBox cbo_doc1, cbo_doc2;

    private ObservableList obsOption1 = FXCollections.observableArrayList("Docente1");
    private ObservableList obsOption2 = FXCollections.observableArrayList("Docente2");



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboBox();
    }

    /**
     * llenar combobox
     */
    private void fillComboBox() {
        cbo_doc1.getItems().addAll(obsOption1);
        cbo_doc1.setValue(obsOption1.get(0));
        cbo_doc2.getItems().addAll(obsOption2);
        cbo_doc2.setValue(obsOption2.get(0));

        for (String s : aDocentesID) {
            cbo_doc1.getItems().add(s);
            cbo_doc2.getItems().add(s);
        }
    }

    /**
     * savedDocente
     *
     * @param mouseEvent
     */
    public void savedDocente(MouseEvent mouseEvent) {
        if (!cbo_doc1.getValue().equals(obsOption1.get(0)))
            setDocente(String.valueOf(cbo_doc1.getValue()), 1);
        if (!cbo_doc2.getValue().equals(obsOption2.get(0)))
            setDocente(String.valueOf(cbo_doc2.getValue()), 2);
        closeStage(stage);
    }

    /**
     * setDocente
     *
     * @param value
     * @param numDocente
     */
    private void setDocente(String value, int numDocente) {
        for (GridSesion gs : tcm.aGridSesions) {
            if (gs.getMiniGrid().equals(gp_waiting)) {
                switch (numDocente) {
                    case 1:
                        getSesion(gs.getSesionID()).setDocente1(findNewDocente(value));
                        gs.getCbo_doc1().setValue(value);
                        break;
                    case 2:
                        getSesion(gs.getSesionID()).setDocente2(findNewDocente(value));
                        gs.getCbo_doc2().setValue(value);
                        break;
                }
                break;
            }
        }
    }

    /**
     * findNewDocente
     *
     * @param value
     * @return
     */
    private Docente findNewDocente(String value) {
        Docente nd = null;
        for (Docente docente : aDocentes) {
            if (docente.getCode().equals(value)) {
                nd = docente;
                break;
            }
        }
        return nd;
    }


    public void openScene(TabCalendarMaster tcm) throws IOException {
        this.tcm = tcm;
        root = FXMLLoader.load(getClass().getResource("/view/popUp/intUpdateDocente.fxml"));
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Editar Docente");
        stage.setScene(new Scene(root, 329, 70));
        stage.show();
    }


}
