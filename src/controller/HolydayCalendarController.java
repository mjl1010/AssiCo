package controller;

import entity.DiaPlanificado;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import entity.CalendarioBase;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import start.MainLogin;
import utilities.AlertHelper;
import utilities.TextResponsive;
import utilities.VariablesAndMethodsUtils;
import utilities.WeekDates;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.*;

/**
 * Created by Michael and modified by Manel
 */
public class HolydayCalendarController implements Initializable {
    private static HolydayCalendarController main;

    @FXML
    TableView<WeekDates> tvCalendar;

    @FXML
    Button generar,  back;

    private int cont_aListDate = -1;
    private ArrayList<String> celdasSeleccionadas = new ArrayList<>();
    private ArrayList<String> celdasVacations = new ArrayList<>();
    private static String CELL_BG_RED = TextResponsive.getFontStyle("h6") + "-fx-background-color: #ff0000;";
    private static String CELL_BG_LIGHTGREY = TextResponsive.getFontStyle("h6") + "-fx-background-color: #dddddd;";
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String cursoAcademico;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main = this;

        HeaderController.main.titulo.setText("Selecciona los días festivos");

        refreshText();

        fillCeldasVacations();
        columnsSetting();
        fillTableView();

//        for (int i = 0; i < CourseRangeController.getaListCalBase().size(); i++) {
//            System.out.println(CourseRangeController.getaListCalBase().get(i));
//        }

    }

    /**
     * columns setting
     */
    private void columnsSetting() {
        tvCalendar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tvCalendar.getSelectionModel().setCellSelectionEnabled(true);
        tvCalendar.getColumns().addAll(generarColumna("Lunes"),
                generarColumna("Martes"),
                generarColumna("Miércoles"),
                generarColumna("Jueves"),
                generarColumna("Viernes"),
                generarColumna("Sábado"),
                generarColumna("Domingo"));
    }

    private TableColumn<WeekDates, String> generarColumna(String diaSemana) {
        TableColumn<WeekDates, String> t = new TableColumn<>(diaSemana);

        switch (diaSemana) {
            case "Lunes":
                t.setCellValueFactory(cellData -> cellData.getValue().mondayProperty());
                break;

            case "Martes":
                t.setCellValueFactory(cellData -> cellData.getValue().tuesdayProperty());
                break;

            case "Miércoles":
                t.setCellValueFactory(cellData -> cellData.getValue().wednesdayProperty());
                break;

            case "Jueves":
                t.setCellValueFactory(cellData -> cellData.getValue().thursDayProperty());
                break;

            case "Viernes":
                t.setCellValueFactory(cellData -> cellData.getValue().fridayProperty());
                break;

            case "Sábado":
                t.setCellValueFactory(cellData -> cellData.getValue().saturdayProperty());
                break;

            case "Domingo":
                t.setCellValueFactory(cellData -> cellData.getValue().sundayProperty());
                break;
        }

        t.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                    setStyle(TextResponsive.getFontStyle("h6"));

                    if ((item != null && celdasVacations.contains(item)))
                        setStyle(CELL_BG_LIGHTGREY);

                    if (item != null && !celdasSeleccionadas.isEmpty() && celdasSeleccionadas.contains(item)
                            && !celdasVacations.contains(item)) {
                        setStyle(CELL_BG_RED);
                    }

                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty() && !(cell.getStyle().equals(CELL_BG_RED))
                        && !celdasVacations.contains(cell.getText())) {
                    cell.setStyle(CELL_BG_RED);
                    celdasSeleccionadas.add(cell.getText());
                } else if (cell.getStyle().equals(CELL_BG_RED)) {
                    celdasSeleccionadas.remove(cell.getText());
                    cell.setStyle(TextResponsive.getFontStyle("h6"));
                }
            });
            return cell;
        });

        return t;
    }

    /**
     * add elements to
     * cells vacations list
     */
    private void fillCeldasVacations() {
        Iterator it = CourseRangeController.getHsVacations().iterator();
        while (it.hasNext()) {
            celdasVacations.add(String.valueOf(it.next()));
        }
    }

    /**
     * add rows to table
     */
    private void fillTableView() {
        int contWeeksReg = 0;

        System.out.println("numero de semanas : " + CourseRangeController.getContWeeks());

        if (!CourseRangeController.getFirstDayName().equals("MONDAY")) {
            incompletWeekRegistred();
            cont_aListDate--;
            contWeeksReg++;
        }
        for (; contWeeksReg < CourseRangeController.getContWeeks() - 1; contWeeksReg++) completWeekRegistred();
        endWeekRegistred();
    }


    /**
     * end Week Registred
     */
    private void endWeekRegistred() {

        ArrayList<String> dates = new ArrayList<String>();
        int cont = -1;
        LocalDate aux_localDate;

        do {
            dates.add(aCalendarioBase.get(++cont_aListDate).getIdDate());
            aux_localDate = LocalDate.parse(aCalendarioBase.get(cont_aListDate)
            .getIdDate(), FORMATTER2);

        } while (!aux_localDate.isAfter(CourseRangeController.getEndDay().minusDays(1)));

        int aux = 7 - dates.size();
        for (int i = 0; i < aux; i++) dates.add("");

        WeekDates tb = new WeekDates(dates.get(++cont), dates.get(++cont), dates.get(++cont), dates.get(++cont),
                dates.get(++cont), dates.get(++cont), dates.get(++cont));

        tvCalendar.getItems().add(tb);
    }

    /**
     * week Registred
     */
    private void completWeekRegistred() {

        WeekDates wd = new WeekDates(
                String.valueOf(aCalendarioBase.get(++cont_aListDate).getIdDate()),
                String.valueOf(aCalendarioBase.get(++cont_aListDate).getIdDate()),
                String.valueOf(aCalendarioBase.get(++cont_aListDate).getIdDate()),
                String.valueOf(aCalendarioBase.get(++cont_aListDate).getIdDate()),
                String.valueOf(aCalendarioBase.get(++cont_aListDate).getIdDate()),
                String.valueOf(aCalendarioBase.get(++cont_aListDate).getIdDate()),
                String.valueOf(aCalendarioBase.get(++cont_aListDate).getIdDate())
        );

        tvCalendar.getItems().add(wd);
    }

    /**
     * incompletWeekRegistred()
     */
    private void incompletWeekRegistred() {

        ArrayList<String> dates = new ArrayList<>();

        while (aCalendarioBase.get(++cont_aListDate)
                .getWeekDay() != 1)
            dates.add(String.valueOf(aCalendarioBase
                    .get(cont_aListDate).getIdDate()));

        WeekDates tb = new WeekDates();
        tb.completeWeek(CourseRangeController.getFirstDayName(), dates);
        tvCalendar.getItems().add(tb);
    }

    private void createPlanificacionObjects() {
        CalendarioBase pc;
        for (int i = 0; i < aCalendarioBase.size(); i++) {
            pc = aCalendarioBase.get(i);
            cursoAcademico = pc.getCursoAcademico();
            aPlanifCalend.add(new DiaPlanificado(pc, pc.getDia(), VariablesAndMethodsUtils.uni,
                    VariablesAndMethodsUtils.master1));
            aPlanifCalend.add(new DiaPlanificado(pc, pc.getDia(), VariablesAndMethodsUtils.uni,
                    VariablesAndMethodsUtils.master2));
        }
    }

    private void openIntCalendarSession() {
        MainLogin.openStage(getClass().getResource("/view/intSesionsCalendar.fxml"), "Planificaciones - Calendario " + cursoAcademico, null);
        VariablesAndMethodsUtils.closeStage(tvCalendar.getScene().getWindow());
    }

    @FXML
    private void generar() {
        VariablesAndMethodsUtils.init();

        for (CalendarioBase cb : aCalendarioBase) {
            if (celdasSeleccionadas.contains(cb.getIdDate()))
                cb.setFestivo(true);
        }

        createPlanificacionObjects();

        // TODO Mandar a guardar el curso generado
//        ClientExt.connect();
//        ClientExt. send_firstListBaseCalendar(CourseRangeController.getaListCalBase());
//        ClientExt.closeConnection();

        try {
            openIntCalendarSession();
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "No se ha podido generar la planificación");
        }
    }

    @FXML
    private void back() {
        MainLogin.openStage(getClass().getResource("/view/intCourseRange.fxml"), "Planificaciones - Nuevo curso", null);
        VariablesAndMethodsUtils.closeStage(tvCalendar.getScene().getWindow());
    }

    public static void refreshText() {
        if (main == null) return;

        main.tvCalendar.setStyle(TextResponsive.getFontStyle("h6"));
        main.generar.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #9fff3f; -fx-border-color: transparent; -fx-background-radius: 4px;");
        main.back.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #ff5959; -fx-border-color: transparent; -fx-background-radius: 4px;");

        if (main.tvCalendar != null)
            for (Node node : main.tvCalendar.getChildrenUnmodifiable()) {
                if (node instanceof Text) {
                    String addStyle = "-fx-background-color: transparent;";
                    if (node.getStyle().contains("ff0000")) {
                        CELL_BG_RED = TextResponsive.getFontStyle("h6") + "-fx-background-color: #ff0000;";
                        node.setStyle(CELL_BG_RED);
                    } else if (node.getStyle().contains("cccccc")) {
                        CELL_BG_LIGHTGREY = TextResponsive.getFontStyle("h6") + "-fx-background-color: #cccccc;";
                        node.setStyle(CELL_BG_LIGHTGREY);
                    } else {
                        node.setStyle(TextResponsive.getFontStyle("h6"));
                    }
                } else {
                    System.out.println(node.getClass().getName());
                }
            }
    }
}