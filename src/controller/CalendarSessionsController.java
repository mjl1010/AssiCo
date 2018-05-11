package controller;

import entity.PlanificacionCalendarios;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
    @FXML
    MenuItem menuOpt1, menuOpt2, menuOpt3, menuOpt4, menuOpt5, menuOpt6, menuOpt7, menuOpt8;
    ArrayList<MenuItem> aSplitMenuButton;

    String yearInit, monthInit;
    private Properties p;

    ArrayList<GridSesion> aGridSesions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addEventCalendars();
        configurarPantalla();
    }

    private void configurarPantalla() {
        settingsSplitMenuButton();
        extraerValoresProperties();
        asignarValoresLabel();
        updateListsCurrentMonth_tab1();
        registredGridSessions();
        updateCalendarMaster1();
//        for (int i = 0; i < aGridSesions.size(); i++) {
//            System.out.println(aGridSesions.get(i).toString());
//        }
        configSplitMenuButton();

    }

    private void settingsSplitMenuButton() {
        aSplitMenuButton = new ArrayList<>();
        aSplitMenuButton.add(menuOpt1);
        aSplitMenuButton.add(menuOpt2);
        aSplitMenuButton.add(menuOpt3);
        aSplitMenuButton.add(menuOpt4);
        aSplitMenuButton.add(menuOpt5);
        aSplitMenuButton.add(menuOpt6);
        aSplitMenuButton.add(menuOpt7);
        aSplitMenuButton.add(menuOpt8);
    }

    private void configSplitMenuButton() {
        for (MenuItem mi :
                aSplitMenuButton) {
            mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    gestionarOpcion(mi.getId());
                }
                private void gestionarOpcion(String id) {
                    switch (id){
                        case "menuOpt1" :
                            System.out.println("agrega sesion");
                            break;
                        case "menuOpt2" :
                            System.out.println("Intercambio de Sesión");
                            break;
                        case "menuOpt3" :
                            System.out.println("Editar Practica 1");
                            break;
                        case "menuOpt4" :
                            System.out.println("Editar Practica 2");
                            break;
                        case "menuOpt5" :
                            System.out.println("Editar Contenido de Asignatura");
                            break;
                        case "menuOpt6" :
                            System.out.println("Editar Profesor(s)");
                            break;
                        case "menuOpt7" :
                            System.out.println("Editar Tipo de Aula");
                            break;
                        case "menuOpt8" :
                            System.out.println("Editar Aula");
                            break;
                    }
                }
            });
        }
    }

    private void registredGridSessions() {
        aGridSesions = new ArrayList<>();
        GridSesion gs;
        int index = -1;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++) {
                gs = new GridSesion("01/01/1900", i, j);
                gp_calendar.add(gs.getMiniGrid(), j, i);
                aGridSesions.add(gs);
            }
    }

    private void updateCalendarMaster1() {
        int contRow = 0;
        int numDiaSemana = 0;
        for (int i = 0; i < aPlanCalCurrentMonthMaster1.size(); i++) {
            PlanificacionCalendarios pc = aPlanCalCurrentMonthMaster1.get(i);
            numDiaSemana = pc.getCalendarioBase().getWeekDay();
            if (numDiaSemana == 6 || numDiaSemana == 7) {
                if (numDiaSemana == 7) contRow++;
                continue;
            }
            updatesDatesMiniGridCalendar(contRow, numDiaSemana - 1, pc);
        }
    }

    private void updatesDatesMiniGridCalendar(int indexRow, int indexColumn,
                                              PlanificacionCalendarios pc) {
        for (int i = 0; i < aGridSesions.size(); i++) {
            if (aGridSesions.get(i).getIndexRow() == indexRow &&
                    aGridSesions.get(i).getIndexColum() == indexColumn) {
                aGridSesions.get(i).getLblDateID().setText(pc.getCalendarioBase().getIdDate());
                aGridSesions.get(i).getLblDateID().setStyle("-fx-background-color: #BE81F7");
                break;
            }
        }
    }

    private void updateListsCurrentMonth_tab1() { //de momento siempre master1 está en tab1, TODO validar break;
        aPlanCalCurrentMonthMaster1 = new ArrayList<>();
        for (int i = 0; i < aPlanifCalend.size(); i++) {
            String date = String.valueOf(aPlanifCalend.get(i).getCalendarioBase().getDia());
            LocalDate ld = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
            if (String.valueOf(ld.getYear()).equalsIgnoreCase(lblYear.getText()) &&
                    ld.getMonth().name().equalsIgnoreCase(lblMonth.getText()) &&
                    aPlanifCalend.get(i).getMaster().equals(master1))
                aPlanCalCurrentMonthMaster1.add(aPlanifCalend.get(i));
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
                updateListsCurrentMonth_tab1();
                registredGridSessions();
                updateCalendarMaster1();
                break;
            }
        }
    }

    public void clickCell(MouseEvent mouseEvent) {
        System.out.println("object : " + mouseEvent.getSource());
    }
}




