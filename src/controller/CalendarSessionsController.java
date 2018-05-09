package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.GridSesion;
import utilities.VariablesAndMethodsUtils;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.PATH_PROPERTIES;
import static utilities.VariablesAndMethodsUtils.aMonths;

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

    String yearInit, monthInit;
    private Properties p;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addEventCalendars();
//        VariablesAndMethodsUtils.addData();

//        Master master1 = new Master(1, "M01", "master1");
//        Master master2 = new Master(2, "M02", "master2");
//        DatosModel.connect(null);
//        if (DatosModel.getPlanificacionCalendarios(LoginController.token.getUsuario(), "2017-2018",
//                master1, master2) == null) System.out.println("Es nulo xD!");
//        DatosModel.closeConnection();

        GridSesion gs;
        int index = -1;
        for (int i = 0; i < 6; i++) for (int j = 0; j < 5; j++) {
            gs = new GridSesion("20/10/2000");
            gp_calendar.add(gs.getMiniGrid(), j, i);

        }
        configurarPantalla();

    }

    private void configurarPantalla() {
        extraerValoresProperties();
        asignarValoresLabel();
    }

    private void asignarValoresLabel() {
        lblYear.setText(yearInit);
        for (int i = 0; i < aMonths.size(); i++) {
            if (aMonths.get(i).equalsIgnoreCase(monthInit)){
                lblMonth.setText(aMonths.get(i));
                break;
            }
        }
    }

    private void extraerValoresProperties() {
        p = new Properties();
        try {
            p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));

            yearInit = p.getProperty("year");
            monthInit = p.getProperty("month");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getCause() + "&" + e.getMessage());
        }
    }

    private void addEventCalendars() {
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
    }

    /**
     * month next
     * @param mouseEvent
     */
    public void getNextMonth(MouseEvent mouseEvent) {
        for (int i = 0; i < aMonths.size(); i++) {
            if (aMonths.get(i).equalsIgnoreCase(lblMonth.getText())){
                if (i == aMonths.size()-1) lblMonth.setText(aMonths.get(0));
                else lblMonth.setText(aMonths.get(++i));
                break;
            }
        }
        System.out.println("click en nextMonth");
    }

    /**
     * month previous
     * @param mouseEvent
     */
    public void getPreviousMonth(MouseEvent mouseEvent) {
        for (int i = 0; i < aMonths.size(); i++) {
            if (aMonths.get(i).equalsIgnoreCase(lblMonth.getText())){
                if (i == 0) lblMonth.setText(aMonths.get(aMonths.size()-1));
                else lblMonth.setText(aMonths.get(--i));
                break;
            }
        }
        System.out.println("click en previousMonth");
    }

    public void clickCell(MouseEvent mouseEvent) {
        System.out.println("object : " + mouseEvent.getSource());
    }

}
