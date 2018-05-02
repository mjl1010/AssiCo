package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import model.TableMini;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 */
public class CalendarSessionsController implements Initializable {

    @FXML
    GridPane gp_calendar;

    @FXML
    ScrollPane sp_tab1;

    @FXML
    AnchorPane ap_limbo;

    @FXML
    Pane p_sessionCal;
    Pane paneAux;

    ArrayList<String> days = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillDays();
//        calendarSettings();
        test();
    }

    private void test() {

//        TableMini tableMini = new TableMini("01/01/01", "01/01/01", "01/01/01", "01/01/01");
//        gp_calendar.add(tableMini.getTv_mini(), 1, 1);

        paneAux = p_sessionCal;
//        gp_calendar.add(paneAux, 2, 5);
    }

    private void fillDays() {
        days.add("Lunes");
        days.add("Martes");
        days.add("Miercoles");
        days.add("Jueves");
        days.add("Viernes");
        days.add("Sabado");
        days.add("Domingo");
    }

    private void calendarSettings() {

        ScrollPane sp = new ScrollPane();
        sp.setContent(gp_calendar);

//        for (int i = 0; i < days.size(); i++) {
//            gp_calendar.getColumnConstraints().add(new ColumnConstraints(95));
//            gp_calendar.getRowConstraints().add(new RowConstraints(65));
//        }


    }
}
