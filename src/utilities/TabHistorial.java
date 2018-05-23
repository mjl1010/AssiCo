package utilities;


import entity.Master;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;

import java.io.IOException;

import static utilities.RecordHistoryManager.fillTvHist;
import static utilities.VariablesAndMethodsUtils.aMaster;

/**
 * Created by Michael
 */
public class TabHistorial {

    private TableView<HistoryTableRow> tv_historial;
    private ComboBox cbo_master;
    private Button btnGenerarTxt;


    /**
     * Método Constructor
     *
     * @param tv_historial
     */
    public TabHistorial(TableView tv_historial, ComboBox cbo_master, Button btnGenerarTxt) {
        this.tv_historial = tv_historial;
        this.cbo_master = cbo_master;
        this.btnGenerarTxt = btnGenerarTxt;
        columnsSetting();
        settingCombobox();
    }

    public void settingCombobox() {
        if (cbo_master.getItems().size() < 1) cbo_master.getItems().addAll(aMaster);
        cbo_master.setOnAction((event) -> {
            try {
                System.out.println("click combo");
                fillTvHist(String.valueOf(cbo_master.getValue()), tv_historial);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void columnsSetting() {
        tv_historial.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv_historial.getSelectionModel().setCellSelectionEnabled(true);
        tv_historial.getColumns().addAll(generarColumna("fechaInter"),
                generarColumna("horaInter"),
                generarColumna("masterID"),
                generarColumna("sesionID"),
                generarColumna("asignatura"),
                generarColumna("contenido"),
                generarColumna("doc1"),
                generarColumna("doc2"),
                generarColumna("dateOrigen"),
                generarColumna("dateDestino"));
    }

    private TableColumn<HistoryTableRow, String> generarColumna(String diaSemana) {
        TableColumn<HistoryTableRow, String> t = new TableColumn<>(diaSemana);
        switch (diaSemana) {
            case "fechaInter":
                t.setCellValueFactory(cellData -> cellData.getValue().fechaInterProperty());
                break;
            case "horaInter":
                t.setCellValueFactory(cellData -> cellData.getValue().horaInterProperty());
                break;
            case "masterID":
                t.setCellValueFactory(cellData -> cellData.getValue().masterIDProperty());
                break;
            case "sesionID":
                t.setCellValueFactory(cellData -> cellData.getValue().sesionIDProperty());
                break;
            case "asignatura":
                t.setCellValueFactory(cellData -> cellData.getValue().asignaturaProperty());
                break;
            case "contenido":
                t.setCellValueFactory(cellData -> cellData.getValue().contenidoProperty());
                break;
            case "doc1":
                t.setCellValueFactory(cellData -> cellData.getValue().doc1Property());
                break;
            case "doc2":
                t.setCellValueFactory(cellData -> cellData.getValue().doc2Property());
                break;
            case "dateOrigen":
                t.setCellValueFactory(cellData -> cellData.getValue().dateOrigenProperty());
                break;
            case "dateDestino":
                t.setCellValueFactory(cellData -> cellData.getValue().dateDestinoProperty());
                break;
        }
        return t;
    }

    public TableView getTv_historial() {
        return tv_historial;
    }

    public void setTv_historial(TableView tv_historial) {
        this.tv_historial = tv_historial;
    }

    public ComboBox getCbo_master() {
        return cbo_master;
    }

    public void setCbo_master(ComboBox cbo_master) {
        this.cbo_master = cbo_master;
    }

    public Button getBtnGenerarTxt() {
        return btnGenerarTxt;
    }

    public void setBtnGenerarTxt(Button btnGenerarTxt) {
        this.btnGenerarTxt = btnGenerarTxt;
    }
}
