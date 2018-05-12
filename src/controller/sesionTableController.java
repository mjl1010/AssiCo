package controller;

import entity.Master;
import entity.Sesion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.SesionTableRow;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.aSession;
import static utilities.VariablesAndMethodsUtils.addData;
import static utilities.VariablesAndMethodsUtils.master1;
import static controller.SesionsCalendarController.master_current;

public class sesionTableController implements Initializable {

    @FXML
    TableView<SesionTableRow> tv_session;

    TableColumn<Sesion, Integer> tb_session, tb_master1, tb_master2;
    TableColumn<Sesion, String>  tb_asig, tb_content, tb_doc1, tb_doc2, tb_tipAula, tb_aula, tb_nota;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsTable();
        addData();
        printDataInTable();
    }

    private void printDataInTable() {
        if (master_current.equals(master1)) printM1();
        else printM2();

    }

    private void printM1() {
        for (int i = 0; i < aSession.size(); i++) {
            if ( aSession.get(i).getMaster1() != null
                    && aSession.get(i).getMaster1().equals(master_current)
                    && !aSession.get(i).isActivo()) addSesionInTable(aSession.get(i));
        }
    }

    private void printM2() {
        for (int i = 0; i < aSession.size(); i++) {
            if ( aSession.get(i).getMaster2() != null
                    && aSession.get(i).getMaster2().equals(master_current)
                    && !aSession.get(i).isActivo()) addSesionInTable(aSession.get(i));
        }
    }

    private void addSesionInTable(Sesion s) {
        String master1, master2, doc1, doc2;

        if (s.getMaster1() == null) master1 = "null";
        else master1 = s.getMaster1().getCode();
        if (s.getMaster2() == null) master2 = "null";
        else master2 = s.getMaster2().getCode();
        if (s.getDocente1() == null) doc1 = "null";
        else doc1 = s.getDocente1().getCode();
        if (s.getDocente2() == null) doc2 = "null";
        else doc2 = s.getDocente2().getCode();

        tv_session.getItems().add(new SesionTableRow(String.valueOf(s.getId()), master1,
                master2, s.getAsignatura(), s.getContenidos(), doc1, doc2,
                s.getTipoAula(), s.getAula(), "Hello Moto "));
    }

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
    }

    private TableColumn<SesionTableRow, String> generarColumn(String nomColumnInObj) {
        TableColumn<SesionTableRow, String> t = new TableColumn<>(nomColumnInObj);
        switch (nomColumnInObj){
            case "sesionID" :
                t.setCellValueFactory(cellData -> cellData.getValue().sesionIDProperty());
                break;
            case "master1" :
                t.setCellValueFactory(cellData -> cellData.getValue().master1Property());
                break;
            case "master2" :
                t.setCellValueFactory(cellData -> cellData.getValue().master2Property());
                break;
            case "asignatura" :
                t.setCellValueFactory(cellData -> cellData.getValue().asignaturaProperty());
                break;
            case "contenido" :
                t.setCellValueFactory(cellData -> cellData.getValue().contenidoProperty());
                break;
            case "docentet1" :
                t.setCellValueFactory(cellData -> cellData.getValue().docentet1Property());
                break;
            case "docentet2" :
                t.setCellValueFactory(cellData -> cellData.getValue().docentet2Property());
                break;
            case "TipoAula" :
                t.setCellValueFactory(cellData -> cellData.getValue().tipoAulaProperty());
                break;
            case "Aula" :
                t.setCellValueFactory(cellData -> cellData.getValue().aulaProperty());
                break;
            case "nota" :
                t.setCellValueFactory(cellData -> cellData.getValue().notaProperty());
                break;
        }
        return t;
    }
}
