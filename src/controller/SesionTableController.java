package controller;

import entity.Sesion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.GridSesion;
import utilities.TabCalendarMaster;
import utilities.SesionTableRow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.*;

/**
 * Created By Michael
 */
public class SesionTableController implements Initializable {

    private static Parent root;
    private static Stage stage;
    private static Scene scene;
    private static TabCalendarMaster tcm;

    private ObservableList<Sesion> masterData = FXCollections.observableArrayList();
    private ObservableList<Sesion> filteredData = FXCollections.observableArrayList();

    @FXML
    TableView<SesionTableRow> tv_session;

    @FXML
    TextField txtValor;

    @FXML
    ComboBox cbo_field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsTable();
        fillComboBox();
        fillDataMaster();
        addEventTxt();
        printDataInTable();
    }

    private void fillComboBox() {
        cbo_field.getItems().addAll(aColumnNametvSes);
    }

    private void addEventTxt() {
        txtValor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                updateFilteredData(newValue);
            }
        });
    }

    private void updateFilteredData(String newValue) {
        filteredData.clear();
        for (Sesion s : masterData) if (matchesFilter(s, newValue)) filteredData.add(s);
        tv_session.getItems().clear();
        printDataInTable();
    }

    private boolean matchesFilter(Sesion s, String newValue) {
        boolean ret = false;
        String opt = aColumnNametvSes.get(0);
        if (cbo_field.getValue() != null) opt = (String) cbo_field.getValue();
        newValue = newValue.toLowerCase();
        switch (opt.toLowerCase()){
            case "sesionid":
                if (String.valueOf(s.getId()).toLowerCase().startsWith(newValue)) ret = true;
                break;
            case "master1":
                if (s.getMaster1().getCode().toLowerCase().startsWith(newValue)) ret = true;
                break;
            case "master2":
                if (s.getMaster2().getCode().toLowerCase().startsWith(newValue)) ret = true;
                break;
            case "asignatura":
                if (s.getAsignatura().toLowerCase().startsWith(newValue)) ret = true;
                break;
            case "contenido":
                if (s.getContenidos().toLowerCase().startsWith(newValue)) ret = true;
                break;
            case "docentet1":
                if (s.getDocente1().getCode().toLowerCase().startsWith(newValue)) ret = true;
                break;
            case "docentet2":
                if (s.getDocente2().getCode().toLowerCase().startsWith(newValue)) ret = true;
                break;
            case "tipoaula":
                if (s.getTipoAula().equalsIgnoreCase(newValue)) ret = true;
                break;
            case "aula":
                if (s.getAula().toLowerCase().startsWith(newValue)) ret = true;
                break;
            case "nota":
                if (s.getNota0().toLowerCase().startsWith(newValue)) ret = true;
                break;
        }
        return ret;
    }

    private void fillDataMaster() {
        for (Sesion s : aSession) {
            masterData.add(s);
        }
        filteredData.addAll(masterData);
    }


    public void openScene(TabCalendarMaster tcm) throws IOException {
        this.tcm = tcm;
        root = FXMLLoader.load(getClass().getResource("/view/popUp/intSesionTable.fxml"));
        stage = new Stage(StageStyle.DECORATED);
        scene = new Scene(root, 600, 400);
        stage.setTitle("Lista de Sesiones");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * printDataInTable()
     */
    private void printDataInTable() {
        if (tcm.getMaster().equals(master1)) printM1();
        else printM2();

    }

    /**
     * printM1
     */
    private void printM1() {
        System.out.println("aSesion.size : " + aSession.size());
        System.out.println("filteredData.size : " + filteredData.size());
        for (int i = 0; i < filteredData.size(); i++) {
            if (filteredData.get(i).getMaster1() != null
                    && filteredData.get(i).getMaster1().equals(tcm.getMaster())
                    && !filteredData.get(i).isActivo()) addSesionInTable(filteredData.get(i));

        }
    }

    /**
     * printM2
     */
    private void printM2() {
        for (int i = 0; i < filteredData.size(); i++) {
            if (filteredData.get(i).getMaster2() != null
                    && filteredData.get(i).getMaster2().equals(tcm.getMaster())
                    && !filteredData.get(i).isActivo()) addSesionInTable(filteredData.get(i));
        }
    }

    /**
     * add Sesion In Table
     *
     * @param s
     */
    private void addSesionInTable(Sesion s) {
        String master1, master2, doc1, doc2;

        if (s.getMaster1() == null) master1 = "";
        else master1 = s.getMaster1().getCode();
        if (s.getMaster2() == null) master2 = "";
        else master2 = s.getMaster2().getCode();
        if (s.getDocente1() == null) doc1 = "";
        else doc1 = s.getDocente1().getCode();
        if (s.getDocente2() == null) doc2 = "";
        else doc2 = s.getDocente2().getCode();

        tv_session.getItems().add(new SesionTableRow(String.valueOf(s.getId()), master1,
                master2, s.getAsignatura(), s.getContenidos(), doc1, doc2,
                s.getTipoAula(), s.getAula(), ""));
    }

    /**
     * settingsTable
     */
    private void settingsTable() {
        int cont_tvSesColName = -1;
        tv_session.getColumns().addAll(generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName)),
                generarColumn(aColumnNametvSes.get(++cont_tvSesColName))
        );
        tv_session.setRowFactory(tv -> {
            TableRow<SesionTableRow> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    SesionTableRow objSesionTableRow = row.getItem();
                    setObjSesion(objSesionTableRow);
                    registredSesion(objSesionTableRow, tcm.getaGridSesions());
                    tcm.desmarcarGridWaiting();
                    closeStage(stage);
                }
            });
            return row;
        });
    }

    private void setObjSesion(SesionTableRow objSesionTableRow) {
        for (Sesion s :
                aSession) {
            if (String.valueOf(s.getId()).equalsIgnoreCase(objSesionTableRow.getSesionID())) {
                s.setActivo(true);
                break;
            }
        }
    }

    /**
     * registra sesion
     *
     * @param obj
     */
    private void registredSesion(SesionTableRow obj,
                                 ArrayList<GridSesion> aGridSesions) {
        for (GridSesion gs : aGridSesions) {
            if (gs.getMiniGrid().equals(tcm.getGp_waiting())) {
                if (obj.getMaster1().length() > 0)
                    addSesionToPlanifList(getSesion(Integer.parseInt(obj.getSesionID())), getCalBasID(gs.getLblDateID().getId()),
                            master1);
                if (obj.getMaster2().length() > 0)
                    addSesionToPlanifList(getSesion(Integer.parseInt(obj.getSesionID())), getCalBasID(gs.getLblDateID().getId()),
                            master2);
                if (!obj.getMaster1().isEmpty() &&
                        !obj.getMaster2().isEmpty())
                    registedSesionInCalendarVinculado(gs.getLblDateID(), obj);
                tcm.updateCalendar();
                tcm.getTcm_vinculado().updateCalendar();
                break;
            }
        }
    }

    private void registedSesionInCalendarVinculado(Label lblDateID, SesionTableRow obj) {
        for (GridSesion gd : tcm.getTcm_vinculado().getaGridSesions()) {
            if (gd.getLblDateID().getId().equals(lblDateID.getId())){
                gd.getLblDateID().setText(gd.getLblDateID().getId().split("/")[0]);
                gd.getLblAsign().setText(obj.getAsignatura());
                gd.getLblContenido().setText(obj.getContenido());
                gd.getLblJuntSep().setText(getValueJunSep(obj));
                gd.getLblAula().setText(obj.getAula());
                gd.getCbo_tipoAula().setValue(getValueTipoAula(obj));
                gd.getCbo_doc1().setValue(getValueDoc(obj, 1));
                gd.getCbo_doc2().setValue(getValueDoc(obj, 2));
                gd.setSesionID(Integer.parseInt(obj.getSesionID()));
                gd.setVisibleComboBoxs(true);
            }
        }
    }

    private String getValueDoc(SesionTableRow obj, int numDoc) {
        String sr = "";
        for (String s :
                aDocentesID) {
            switch (numDoc) {
                case 1:
                    if (s.equalsIgnoreCase(obj.getDocentet1())) sr = s;
                    break;
                case 2:
                    if (s.equalsIgnoreCase(obj.getDocentet2())) sr = s;
                    break;
            }
        }
        return sr;
    }

    private String getValueTipoAula(SesionTableRow obj) {
        String sr = "";
        for (String s :
                aTiposAula) {
            if (s.equalsIgnoreCase(obj.getTipoAula()))
                sr = s;
        }
        return sr;
    }

    private String getValueJunSep(SesionTableRow obj) {
        String s = "";
        if (obj.getMaster1() != null
                && obj.getMaster2() != null) s = "J";
        else s = "S";
        return s;
    }

    /**
     * generar Column
     *
     * @param nomColumnInObj
     * @return
     */
    private TableColumn<SesionTableRow, String> generarColumn(String nomColumnInObj) {
        TableColumn<SesionTableRow, String> t = new TableColumn<>(nomColumnInObj);
        switch (nomColumnInObj) {
            case "sesionID":
                t.setCellValueFactory(cellData -> cellData.getValue().sesionIDProperty());
                break;
            case "master1":
                t.setCellValueFactory(cellData -> cellData.getValue().master1Property());
                break;
            case "master2":
                t.setCellValueFactory(cellData -> cellData.getValue().master2Property());
                break;
            case "asignatura":
                t.setCellValueFactory(cellData -> cellData.getValue().asignaturaProperty());
                break;
            case "contenido":
                t.setCellValueFactory(cellData -> cellData.getValue().contenidoProperty());
                break;
            case "docentet1":
                t.setCellValueFactory(cellData -> cellData.getValue().docentet1Property());
                break;
            case "docentet2":
                t.setCellValueFactory(cellData -> cellData.getValue().docentet2Property());
                break;
            case "TipoAula":
                t.setCellValueFactory(cellData -> cellData.getValue().tipoAulaProperty());
                break;
            case "Aula":
                t.setCellValueFactory(cellData -> cellData.getValue().aulaProperty());
                break;
            case "nota":
                t.setCellValueFactory(cellData -> cellData.getValue().notaProperty());
                break;
        }
        return t;
    }

    public void clickBuscar(MouseEvent mouseEvent) {
        updateFilteredData(txtValor.getText());
    }
}
