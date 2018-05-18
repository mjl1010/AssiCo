package utilities;

import controller.*;
import entity.DiaPlanificado;
import entity.Master;
import entity.Sesion;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import static utilities.VariablesAndMethodsUtils.*;

public class TabCalendarMaster {

    private GridPane gp_calendar;
    private Master master;
    private SplitMenuButton smb_menuOption;
    private Label lblYear, lblMonth;
    private Properties p;
    private String yearInit, monthInit;
    private Master master_current;
    private ArrayList<DiaPlanificado> aPlanCalCurrentMonth;

    private ArrayList<GridSesion> aGridSesions;
    private GridPane gp_waiting;
    private String date_exchange1;
    private GridPane gp_exchange2;
    private String gp_waiting_style;
    private GridPane gp_exchange;
    private String gp_exchange_style;
    private TabCalendarMaster tcm_vinculado;

    private boolean ischange;
    private int contTest = 0;

    /**
     * Método constructor
     *
     * @param gp_calendar
     * @param master
     * @param smb_menuOption
     * @param lblYear
     * @param lblMonth
     */
    public TabCalendarMaster(GridPane gp_calendar, Master master, SplitMenuButton smb_menuOption, Label lblYear, Label lblMonth) {
        this.gp_calendar = gp_calendar;
        this.master = master;
        this.smb_menuOption = smb_menuOption;
        this.lblYear = lblYear;
        this.lblMonth = lblMonth;
        extraerValoresProperties();
        asignarValoresLabel();
        updateListsCurrentMonth();
        registredGridSessions();
        updateCalendar();
        addEventCalendars();
    }

    /**
     * add event to calendar
     */
    private void addEventCalendars() {
        for (int i = 0; i < gp_calendar.getChildren().size() - 1; i++) {
            if (gp_calendar.getChildren().get(i) instanceof javafx.scene.Group) continue;
            GridPane gp_aux = (GridPane) gp_calendar.getChildren().get(i);
            Label lbl_aux = (Label) gp_aux.getChildren().get(0);
            if (!lbl_aux.getText().isEmpty()) {
                gp_calendar.getChildren().get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (event.getClickCount() == 2) {
                        smb_menuOption.setDisable(false);
                        if (!ischange) marcarGrid((GridPane) event.getSource());
                        else {
                            gp_exchange2 = (GridPane) event.getSource();
                            gp_exchange2.setStyle("-fx-border-color: #A9D0F5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 5;");
                            SesionsExchange.change(this, date_exchange1, getDate(gp_exchange2));
                            ischange = false;
                        }
                    }
                });
            }
        }
    }

    /**
     * tratamiento de grid seleccionado
     *
     * @param miniGrid
     */
    private void marcarGrid(GridPane miniGrid) {
        if (gp_waiting != null) desmarcarGridWaiting();
        gp_waiting = miniGrid;
        gp_waiting_style = gp_waiting.getStyle();
        gp_waiting.setStyle(gp_waiting_style + "-fx-border-color: #A9D0F5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 5;");

        smb_menuOption.setDisable(false);
    }

    public String getDate(GridPane gridPane) {
        System.out.println("contTest : " + ++contTest);
        String date = null;
        for (GridSesion gs : aGridSesions) {
            if (gs.getMiniGrid().equals(gridPane)) {
                System.out.println("se encuentra gridxD");
                date = gs.getLblDateID().getId();
                break;
            }
        }
        System.out.println("return date : " + date);
        return date;
    }

    public void desmarcarGridWaiting() {
        gp_waiting.setStyle(gp_waiting_style + "-fx-border-color: #dddddd;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;");
        smb_menuOption.setDisable(true);
    }

    private void updateCalendarMaster() {
        int contRow = 0;
        int numDiaSemana = 0;
        for (int i = 0; i < aPlanCalCurrentMonth.size(); i++) {
            DiaPlanificado pc = aPlanCalCurrentMonth.get(i);
            numDiaSemana = pc.getCalendarioBase().getWeekDay();
            if (numDiaSemana == 6 || numDiaSemana == 7) {
                if (numDiaSemana == 7) contRow++;
                continue;
            }
            updatesDatesMiniGridCalendar(contRow, numDiaSemana - 1, pc);
        }
    }

    private void updatesDatesMiniGridCalendar(int indexRow, int indexColumn, DiaPlanificado pc) {
        for (int i = 0; i < aGridSesions.size(); i++) {
            if (aGridSesions.get(i).getIndexRow() == indexRow && aGridSesions.get(i).getIndexColum() == indexColumn) {
                aGridSesions.get(i).getLblDateID().setId(pc.getCalendarioBase().getIdDate());
                aGridSesions.get(i).getLblDateID().setText(pc.getCalendarioBase().getIdDate().split("/")[0]);
                aGridSesions.get(i).getLblDateID().setStyle("-fx-text-fill: #000000; " + TextResponsive.getFontStyle("h4"));
                aGridSesions.get(i).getMiniGrid().setStyle("-fx-border-color: #dddddd;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;");
                if (pc.getCalendarioBase().isFestivo()) aGridSesions.get(i).getMiniGrid().setStyle("-fx-background-color: #dddddd;");
                if (pc.getCalendarioBase().isSummer()) aGridSesions.get(i).getMiniGrid().setStyle("-fx-background-color: #cccccc;");
                if (!pc.getCalendarioBase().isActive()) aGridSesions.get(i).getMiniGrid().setStyle("-fx-background-color: #f5f5f5;");
                if (pc.getSesion() != null) regDatosSesion(aGridSesions.get(i), pc.getSesion());
                break;
            }
        }
    }

    /**
     * registrar sesion
     *
     * @param gs
     * @param s
     */
    private void regDatosSesion(GridSesion gs, Sesion s) {
        String jp = "S";
        if (s.getMaster1() != null && s.getMaster2() != null) jp = "J";
        gs.getLblAsign().setText(s.getAsignatura());
        gs.getLblContenido().setText(s.getContenidos());
        gs.getLblJuntSep().setText(jp);
        if (jp.equals("S")) gs.getLblJuntSep().setStyle("-fx-background-color: white; -fx-text-fill: black;");
        gs.getLblAula().setText(s.getAula());
        if (s.getDocente1() != null) gs.getCbo_doc1().setValue(s.getDocente1().getCode());
        if (s.getDocente2() != null) gs.getCbo_doc2().setValue(s.getDocente2().getCode());
        gs.getCbo_tipoAula().setValue(s.getTipoAula());
        gs.setSesionID(s.getId());
        gs.setVisibleComboBoxs(true);

        gs.getMiniGrid().setStyle("-fx-background-color: " + s.getColorFondo() + ";");
        gs.getLblDateID().setStyle("-fx-background-color: " + s.getColorFondo() + "; -fx-font: " + TextResponsive.getH6() + " \"System Bold\"; -fx-text-fill: " + s.getColorTexto() + ";");

    }

    /**
     * registredGridSessions
     */
    private void registredGridSessions() {
        aGridSesions = new ArrayList<>();
        GridSesion gs;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++) {
                gs = new GridSesion(new String(), i, j);
                gp_calendar.add(gs.getMiniGrid(), j, i);
                aGridSesions.add(gs);
            }
    }

    /**
     * updateListsCurrentMonth
     */
    private void updateListsCurrentMonth() { //de momento siempre master1 está en tab1, TODO validar break;
        aPlanCalCurrentMonth = new ArrayList<>();
        for (int i = 0; i < aPlanifCalend.size(); i++) {
            String date = String.valueOf(aPlanifCalend.get(i).getCalendarioBase().getDia());
            LocalDate ld = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
            if (String.valueOf(ld.getYear()).equalsIgnoreCase(lblYear.getText()) && ld.getMonth().name().equalsIgnoreCase(lblMonth.getText()) && aPlanifCalend.get(i).getMaster().equals(master))
                if (!aPlanifCalend.get(i).getCalendarioBase().isFestivo() && !aPlanifCalend.get(i).getCalendarioBase().isSummer() && aPlanifCalend.get(i).getCalendarioBase().isActive()) {
                    aPlanCalCurrentMonth.add(aPlanifCalend.get(i));
                }
        }
    }

    /**
     * asignar valores label
     */
    private void asignarValoresLabel() {
        lblYear.setText(yearInit);
        for (int i = 0; i < aMonths.size(); i++) {
            if (aMonths.get(i).equalsIgnoreCase(monthInit)) {
                lblMonth.setText(aMonths.get(i));
                break;
            }
        }
    }

    /**
     * extraer valores del property file
     */
    private void extraerValoresProperties() {
        p = new Properties();
        try {
            p.load(new FileReader(PATH_PROPERTIES));
            master_current = getMasterSes(p.getProperty("masterID"));
            yearInit = p.getProperty("year");
            monthInit = p.getProperty("month");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getCause() + "&" + e.getMessage());
        }
    }

    public void updateMonth(MouseEvent mouseEvent) {
        ImageView iv = (ImageView) mouseEvent.getSource();
        for (int i = 0; i < aMonths.size(); i++) {
            if (aMonths.get(i).equalsIgnoreCase(lblMonth.getText())) {
                if (iv.getId().equalsIgnoreCase("flechaLeft")) {
                    if (lblMonth.getText().equalsIgnoreCase(CourseRangeController.firtDay.getMonth().name()) && lblYear.getText().equalsIgnoreCase(String.valueOf(CourseRangeController.firtDay.getYear())))
                        break;
                    if (i == 0) {
                        lblMonth.setText(aMonths.get(aMonths.size() - 1));
                        yearInit = String.valueOf(Integer.parseInt(yearInit) - 1);
                        lblYear.setText(yearInit);
                    } else lblMonth.setText(aMonths.get(--i));
                } else { // flechaRigth
                    if (lblMonth.getText().equalsIgnoreCase(CourseRangeController.endDay.getMonth().name()) && lblYear.getText().equalsIgnoreCase(String.valueOf(CourseRangeController.endDay.getYear())))
                        break;
                    if (i == aMonths.size() - 1) {
                        lblMonth.setText(aMonths.get(0));
                        yearInit = String.valueOf(Integer.parseInt(yearInit) + 1);
                        lblYear.setText(yearInit);
                    } else lblMonth.setText(aMonths.get(++i));
                }
                updateCalendar();
                break;
            }
        }
    }

    public void updateCalendar() {
        gp_calendar.getChildren().clear();
        updateListsCurrentMonth();
        registredGridSessions();
        updateCalendarMaster();
        addEventCalendars();
    }


    // metodos de grid

    /**
     * Set Contenido of sesion
     *
     * @param newSes
     */
    public void setAulaSes(int newSes) {
        for (GridSesion gs : aGridSesions) {
            if (gs.getMiniGrid().equals(gp_waiting)) {
                getSesion(gs.getSesionID()).setAula(String.valueOf(newSes));
                gs.getLblAula().setText(String.valueOf((newSes)));
                break;
            }
        }
    }

    /**
     * Set Contenido of sesion
     *
     * @param newContent
     */
    public void setContenido(String newContent) {
        for (GridSesion gs : aGridSesions) {
            if (gs.getMiniGrid().equals(gp_waiting)) {
                getSesion(gs.getSesionID()).setContenidos(newContent);
                gs.getLblContenido().setText((newContent));
                break;
            }
        }
    }

    /**
     * Set Asignatura of sesion
     *
     * @param newAsign
     */
    public void setAsign(String newAsign) {
        for (GridSesion gs : aGridSesions) {
            if (gs.getMiniGrid().equals(gp_waiting)) {
                getSesion(gs.getSesionID()).setAsignatura(newAsign);
                gs.getLblAsign().setText((newAsign));
                break;
            }
        }
    }

    /**
     * sesion sube
     * al limbo
     */
    public void outSesion() {
        for (GridSesion gs : aGridSesions) {
            if (gs.getMiniGrid().equals(gp_waiting)) {
                if (getSesion(gs.getSesionID()).getMaster1() != null)
                    removeSesion_PlanifList(getCalBasID(gs.getLblDateID().getId()), master1);
                if (getSesion(gs.getSesionID()).getMaster2() != null)
                    removeSesion_PlanifList(getCalBasID(gs.getLblDateID().getId()), master2);
                if (getSesion(gs.getSesionID()).getMaster1() != null &&
                        getSesion(gs.getSesionID()).getMaster2() != null)
                    tcm_vinculado.updateCalendar();
                setActiveValueSesion(gs.getSesionID());
                break;
            }
        }
    }

    /**
     * setActive() - sesion object
     * back limbo
     *
     * @param sesionID
     */
    private void setActiveValueSesion(int sesionID) {
        for (Sesion s : aSession) {
            if (s.getId() == sesionID) {
                s.setActivo(false);
                break;
            }
        }
    }


    // Getters and Setters

    public GridPane getGp_calendar() {
        return gp_calendar;
    }

    public void setGp_calendar(GridPane gp_calendar) {
        this.gp_calendar = gp_calendar;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public ArrayList<GridSesion> getaGridSesions() {
        return aGridSesions;
    }

    public void setaGridSesions(ArrayList<GridSesion> aGridSesions) {
        this.aGridSesions = aGridSesions;
    }

    public GridPane getGp_waiting() {
        return gp_waiting;
    }

    public void setGp_waiting(GridPane gp_waiting) {
        this.gp_waiting = gp_waiting;
    }

    public TabCalendarMaster getTcm_vinculado() {
        return tcm_vinculado;
    }

    public void setTcm_vinculado(TabCalendarMaster tcm_vinculado) {
        this.tcm_vinculado = tcm_vinculado;
    }

    public boolean isIschange() {
        return ischange;
    }

    public void setIschange(boolean ischange) {
        this.ischange = ischange;
    }

    public String getDate_exchange1() {
        return date_exchange1;
    }

    public void setDate_exchange1(String date_exchange1) {
        this.date_exchange1 = date_exchange1;
    }

    public GridPane getGp_exchange2() {
        return gp_exchange2;
    }

    public void setGp_exchange2(GridPane gp_exchange2) {
        this.gp_exchange2 = gp_exchange2;
    }
}
