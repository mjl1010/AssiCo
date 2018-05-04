package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import utilities.VariablesAndMethodsUtils;

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
    Label lblYear, lblMonth;

    @FXML
    SplitMenuButton smb_menuOption;

    String monthNameInit;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calendarSettings();

        monthNameInit =  VariablesAndMethodsUtils
                .getMonthInit();
    }

    private void calendarSettings() {
        gp_calendar.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            smb_menuOption.setDisable(false);
            System.out.println(event.getSource());
        });
        fillComboBox();
    }

    /**
     * fill comboBox
     */
    private void fillComboBox() {
        int cont = 0;

        int cols = gp_calendar.getColumnConstraints().size();
        int rows = gp_calendar.getRowConstraints().size();
        int total = cols * rows;

        for (int i = 0; i < total; i++) {
            GridPane gridMini = (GridPane) gp_calendar.getChildren().get(i);
            ComboBox cbo_aux = (ComboBox) gridMini.getChildren().get(7);
            cbo_aux.getItems().addAll(VariablesAndMethodsUtils.optionsTiposAula);
            cbo_aux.setDisable(true);
        }
    }

    /**
     * month next
     * @param mouseEvent
     */
    public void getNextMonth(MouseEvent mouseEvent) {
        System.out.println("click en nextMonth");
    }

    /**
     * month previous
     * @param mouseEvent
     */
    public void getPreviousMonth(MouseEvent mouseEvent) {
        System.out.println("click en previousMonth");
    }

    public void clickCell(MouseEvent mouseEvent) {
        System.out.println("object : " + mouseEvent.getSource());
    }

    /**
     * asignar mes a
     * la interfaz
     * //TODO necesito variable de curso academico
     */
    public void AsignarMesaInterfaz(){

    }
}
