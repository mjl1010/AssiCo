package controller;

import entity.PlanificacionCalendarios;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.GridSesion;
import utilities.VariablesAndMethodsUtils;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.*;

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

    ArrayList<GridSesion> aGridSesions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addEventCalendars();
//        VariablesAndMethodsUtils.addData();

        configurarPantalla();

    }

    private void configurarPantalla() {
        extraerValoresProperties();
        asignarValoresLabel();
        updateListsCurrentMonth_tab1();
        registredGridSessions();
        updateCalendarMaster1();
    }

    private void registredGridSessions() {
        aGridSesions = new ArrayList<>();
        GridSesion gs;
        int index = -1;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++) {
                gs = new GridSesion("01/01/1900", j, i);
                gp_calendar.add(gs.getMiniGrid(), j, i);
                aGridSesions.add(gs);
            }
    }

    private void updateCalendarMaster1() {
        int contWeeks = 1;
        int numDiaSemana = -1;
        for (int i = 0; i < aPlanCalCurrentMonthMaster1.size(); i++) {
            PlanificacionCalendarios pc = aPlanCalCurrentMonthMaster1.get(i);
            numDiaSemana = pc.getCalendarioBase().getWeekDay();
            updatesDatesMiniGridCalendar(numDiaSemana, contWeeks, pc);
            if (numDiaSemana==7) contWeeks++;
        }
    }

    private void updatesDatesMiniGridCalendar(int numDiaSemana, int contWeeks, PlanificacionCalendarios pc) {
        for (GridSesion gd:
             aGridSesions) {
//            if (gd.getNumSemana())
        }
    }

    private void updateListsCurrentMonth_tab1() { //TODO de momento siempre master1 está en tab1
        for (int i = 0; i < aPlanifCalend.size(); i++) {
            String date = String.valueOf(aPlanifCalend.get(i).getCalendarioBase().getDia());

            if (LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE).getMonth().name()
                    .equalsIgnoreCase(lblMonth.getText()) && aPlanifCalend.get(i).getMaster()
                    .equals(master1))
                aPlanCalCurrentMonthMaster1.add(aPlanifCalend.get(i));
        }

        for (int i = 0; i < aPlanCalCurrentMonthMaster1.size(); i++) {
            System.out.println(aPlanCalCurrentMonthMaster1.get(i));
        }
    }

    private void asignarValoresLabel() {
        lblYear.setText(yearInit);
        for (int i = 0; i < aMonths.size(); i++) {
            if (aMonths.get(i).equalsIgnoreCase(monthInit)) {
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
     * month previous
     *
     * @param mouseEvent
     */
    public void updateMonth(MouseEvent mouseEvent) {
        ImageView iv = (ImageView) mouseEvent.getSource();
        for (int i = 0; i < aMonths.size(); i++) {
            if (aMonths.get(i).equalsIgnoreCase(lblMonth.getText())) {
                if (iv.getId().equalsIgnoreCase("flechaLeft")) {
                    if (lblMonth.getText().equalsIgnoreCase(CourseRankController.firtDay.getMonth().name()) &&
                            lblYear.getText().equalsIgnoreCase(String.valueOf(CourseRankController.firtDay
                                    .getYear()))) break;
                    if (i == 0) {
                        lblMonth.setText(aMonths.get(aMonths.size() - 1));
                        yearInit = String.valueOf(Integer.parseInt(yearInit) - 1);
                        lblYear.setText(yearInit);
                    } else lblMonth.setText(aMonths.get(--i));
                } else { // flechaRigth
                    if (lblMonth.getText().equalsIgnoreCase(CourseRankController.endDay.getMonth().name()) &&
                            lblYear.getText().equalsIgnoreCase(String.valueOf(CourseRankController.endDay
                                    .getYear()))) break;
                    if (i == aMonths.size() - 1) {
                        lblMonth.setText(aMonths.get(0));
                        yearInit = String.valueOf(Integer.parseInt(yearInit) + 1);
                        lblYear.setText(yearInit);
                    } else lblMonth.setText(aMonths.get(++i));
                }
                break;
            }
        }
    }

    public void clickCell(MouseEvent mouseEvent) {
        System.out.println("object : " + mouseEvent.getSource());
    }

}
