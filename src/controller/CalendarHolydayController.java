package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.CalendarioBase;
import model.ClientExt;
import utilities.WeekDates;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by Michael and modified by Manel
 */
public class CalendarHolydayController implements Initializable {

    @FXML
    TableView<WeekDates> tvCalendar;

    private int cont_aListDate = -1;
    private ArrayList<String> celdasSeleccionadas = new ArrayList<>();
    private ArrayList<String> celdasVacations = new ArrayList<>();
    private static final String CELL_BG_RED = "-fx-background-color: red";
    private static final String CELL_BG_LIGHTGREY = "-fx-background-color: LIGHTGREY";
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int cont_test = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCeldasVacations();
        columnsSetting();
        fillTableView();
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
        Iterator it = VacationsRankController.getHsVacations().iterator();
        while (it.hasNext()) {
            celdasVacations.add(String.valueOf(it.next()));
        }
    }

    /**
     * add rows to table
     */
    private void fillTableView() {
        int contWeeksReg = 0;

        System.out.println("numero de semanas : " + CourseRankController.getContWeeks());

        if (!CourseRankController.getFirstDay().equals("MONDAY")) {
            incompletWeekRegistred();
            cont_aListDate--;
            contWeeksReg++;
        }
        for (; contWeeksReg < CourseRankController.getContWeeks() - 1; contWeeksReg++) completWeekRegistred();
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
            dates.add(CourseRankController.getaListCalBase().get(++cont_aListDate).getIdDate());
            aux_localDate = LocalDate.parse(CourseRankController.getaListCalBase().get(cont_aListDate)
            .getIdDate(), FORMATTER2);

        } while (!aux_localDate.isAfter(CourseRankController.getEndDay().minusDays(1)));

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
                String.valueOf(CourseRankController.getaListCalBase().get(++cont_aListDate).getIdDate()),
                String.valueOf(CourseRankController.getaListCalBase().get(++cont_aListDate).getIdDate()),
                String.valueOf(CourseRankController.getaListCalBase().get(++cont_aListDate).getIdDate()),
                String.valueOf(CourseRankController.getaListCalBase().get(++cont_aListDate).getIdDate()),
                String.valueOf(CourseRankController.getaListCalBase().get(++cont_aListDate).getIdDate()),
                String.valueOf(CourseRankController.getaListCalBase().get(++cont_aListDate).getIdDate()),
                String.valueOf(CourseRankController.getaListCalBase().get(++cont_aListDate).getIdDate())
        );

        tvCalendar.getItems().add(wd);
    }

    /**
     * incompletWeekRegistred()
     */
    private void incompletWeekRegistred() {

        ArrayList<String> dates = new ArrayList<String>();

        while (CourseRankController.getaListCalBase().get(++cont_aListDate)
                .getWeekDay() != 1)
            dates.add(String.valueOf(CourseRankController.getaListCalBase()
                    .get(cont_aListDate).getIdDate()));

        WeekDates tb = new WeekDates();
        tb.completeWeek(CourseRankController.getFirstDay(), dates);
        tvCalendar.getItems().add(tb);
    }

    /**
     * saveBtnEvent
     */
    public void saveBtnEvent() {

        for (CalendarioBase cb :
                CourseRankController.getaListCalBase()) {
            if (celdasSeleccionadas.contains(cb.getIdDate()))
                cb.setFestivo(true);
        }

        ClientExt.connect();
        ClientExt. send_firstListBaseCalendar(CourseRankController.getaListCalBase());
        ClientExt.closeConnection();
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