package controller;

import entity.Sesion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.GridSesion;
import model.TabCalendarMaster;
import utilities.SesionTableRow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.SesionsCalendarController.master_current1;
import static utilities.VariablesAndMethodsUtils.*;

public class SesionTableController implements Initializable {

    private static Parent root;
    private static Stage stage;
    private static Scene scene;
    private static TabCalendarMaster tcm;

    @FXML
    TableView<SesionTableRow> tv_session;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsTable();
        printDataInTable();
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
        for (int i = 0; i < aSession.size(); i++) {
            if (aSession.get(i).getMaster1() != null
                    && aSession.get(i).getMaster1().equals(tcm.getMaster())
                    && !aSession.get(i).isActivo()) addSesionInTable(aSession.get(i));
        }
    }

    /**
     * printM2
     */
    private void printM2() {
        for (int i = 0; i < aSession.size(); i++) {
            if (aSession.get(i).getMaster2() != null
                    && aSession.get(i).getMaster2().equals(tcm.getMaster())
                    && !aSession.get(i).isActivo()) addSesionInTable(aSession.get(i));
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
        tv_session.getColumns().addAll(generarColumn("sesionID"),
                generarColumn("master1"),
                generarColumn("master2"),
                generarColumn("asignatura"),
                generarColumn("contenido"),
                generarColumn("docentet1"),
                generarColumn("docentet2"),
                generarColumn("TipoAula"),
                generarColumn("Aula"),
                generarColumn("nota")
        );
        tv_session.setRowFactory(tv -> {
            TableRow<SesionTableRow> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    SesionTableRow objSesionTableRow = row.getItem();
                    registredSesion(objSesionTableRow, tcm.aGridSesions);
                    setObjSesion(objSesionTableRow);
                    tcm.desmarcarGridWaiting();
                    closeStage(stage);
                    printSesionReg();
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
            if (gs.getMiniGrid().equals(gp_waiting)) {
                gs.getLblAsign().setText(obj.getAsignatura());
                gs.getLblContenido().setText(obj.getContenido());
                gs.getLblJuntSep().setText(getValueJunSep(obj));
                gs.getLblAula().setText(obj.getAula());
                gs.getCbo_tipoAula().setValue(getValueTipoAula(obj));
                gs.getCbo_doc1().setValue(getValueDoc(obj, 1));
                gs.getCbo_doc2().setValue(getValueDoc(obj, 2));
                gs.setSesionID(Integer.parseInt(obj.getSesionID()));
                if (obj.getMaster1().length() > 0)
                    addSesionToPlanifList(
                            getSesion(Integer.parseInt(obj.getSesionID())), getCalBasID(gs.getLblDateID().getText()),
                            master1);
                if (obj.getMaster2().length() > 0)
                    addSesionToPlanifList(
                            getSesion(Integer.parseInt(obj.getSesionID())), getCalBasID(gs.getLblDateID().getText()),
                            master2);
                gs.setVisibleComboBoxs(true);
                break;
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
}
