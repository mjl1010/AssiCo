package controller;

import entity.DiaPlanificado;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import entity.CalendarioBase;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    @FXML
    TableView<WeekDates> tvCalendar;

    private int cont_aListDate = -1;
    private ArrayList<String> celdasSeleccionadas = new ArrayList<>();
    private ArrayList<String> celdasVacations = new ArrayList<>();
    private static final String CELL_BG_RED = "-fx-background-color: red";
    private static final String CELL_BG_LIGHTGREY = "-fx-background-color: LIGHTGREY";
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                    setStyle("");

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
                    cell.setStyle("");
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
        Iterator it = VacationsRangeController.getHsVacations().iterator();
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

        ArrayList<String> dates = new ArrayList<String>();

        while (aCalendarioBase.get(++cont_aListDate)
                .getWeekDay() != 1)
            dates.add(String.valueOf(aCalendarioBase
                    .get(cont_aListDate).getIdDate()));

        WeekDates tb = new WeekDates();
        tb.completeWeek(CourseRangeController.getFirstDayName(), dates);
        tvCalendar.getItems().add(tb);
    }

    /**
     * saveBtnEvent
     */
    public void saveBtnEvent() throws IOException {

        for (CalendarioBase cb : aCalendarioBase) {
            if (celdasSeleccionadas.contains(cb.getIdDate()))
                cb.setFestivo(true);
        }

        createPlanificacionObjects();

//        ClientExt.connect();
//        ClientExt. send_firstListBaseCalendar(CourseRangeController.getaListCalBase());
//        ClientExt.closeConnection();

        openIntCalendarSession();

    }

    private void createPlanificacionObjects() {
        aPlanCalCurrentMonthMaster1 = new ArrayList<>();
        aPlanCalCurrentMonthMaster2 = new ArrayList<>();
        CalendarioBase pc;
        for (int i = 0; i < aCalendarioBase.size(); i++) {
            pc = aCalendarioBase.get(i);
            aPlanifCalend.add(new DiaPlanificado(pc, pc.getDia(), VariablesAndMethodsUtils.uni,
                    VariablesAndMethodsUtils.master1));
            aPlanifCalend.add(new DiaPlanificado(pc, pc.getDia(), VariablesAndMethodsUtils.uni,
                    VariablesAndMethodsUtils.master2));
        }
        System.out.println("se registró todo correctamente !!");
    }

    private void openIntCalendarSession() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/intSesionsCalendar.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Sessions Calendar");
        stage.setScene(new Scene(root, 1120 , 650));
        stage.show();
        VariablesAndMethodsUtils.closeStage(tvCalendar.getScene().getWindow());
    }

    /**
     * editBtnEvent
     */
    public void editBtnEvent() {

    }

    /**
     * cancelBtnEvent
     */
    public void cancelBtnEvent() {

    }

    /**
     * backBtnEvent
     */
    public void backBtnEvent() {

    }
}