package controller;

import entity.Sesion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.SesionTableRow;
import utilities.VariablesAndMethodsUtils;

import java.net.URL;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.aSession;
import static utilities.VariablesAndMethodsUtils.addData;

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
        for (Sesion s :
                aSession) {
            addSesion(s);
        }
    }

    private void addSesion(Sesion s) {
//        tv_session.getItems().add(new SesionTableRow(String.valueOf(s.getId()), s.getMaster1().getCode()))
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
        t.setCellFactory(new PropertyValueFactory(nomColumnInObj));
        return t;
    }
}
