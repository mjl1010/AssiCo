package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.CalendarioBase;
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
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    private ArrayList<CalendarioBase> aListCalBase;

    private int cont_test = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCeldasVacations();
        addFilestoTable();
    }

    /**
     * add elements to
     * cells vacations list
     */
    private void fillCeldasVacations() {
        Iterator it = VacationsRankController.getHsVacations().iterator();
        while (it.hasNext()) {
            LocalDate aux = (LocalDate) it.next();
            celdasVacations.add(aux.format(FORMATTER));
        }
    }


    /**
     * add rows to table
     */
    private void addFilestoTable() {
        tvCalendar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tvCalendar.getSelectionModel().setCellSelectionEnabled(true);
        tvCalendar.getColumns().addAll(generarColumna("Lunes"),
                generarColumna("Martes"),
                generarColumna("Miércoles"),
                generarColumna("Jueves"),
                generarColumna("Viernes"),
                generarColumna("Sábado"),
                generarColumna("Domingo"));

        rellenarTableView();
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
     * rellenar table
     */
    private void rellenarTableView() {

        int contWeeksReg = 0;

        System.out.println("numero de semanas : " + CourseRankController.getContWeeks());

        if (!CourseRankController.getFirstDay().equals("MONDAY")) {
            incompletWeekRegistred();
            cont_aListDate--;
            contWeeksReg++;
        }

        for (; contWeeksReg < CourseRankController.getContWeeks() - 1; contWeeksReg++) {
            completWeekRegistred();
        }

        endWeekRegistred();

    }

    /**
     * end Week Registred
     */
    private void endWeekRegistred() {

        ArrayList<String> dates = new ArrayList<String>();
        int cont = -1;

        do {
            dates.add(String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate).format(FORMATTER)));
        } while (!CourseRankController.getaListRankDates().get(cont_aListDate)
                .isAfter(CourseRankController.getEndDay().minusDays(1)));

        int aux = 7 - dates.size();
        for (int i = 0; i < aux; i++) {
            dates.add("");
        }

        WeekDates tb = new WeekDates(dates.get(++cont), dates.get(++cont), dates.get(++cont), dates.get(++cont),
                dates.get(++cont), dates.get(++cont), dates.get(++cont));

        tvCalendar.getItems().add(tb);
    }

    /**
     * week Registred
     */
    private void completWeekRegistred() {

        WeekDates wd = new WeekDates(
                String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate).format(FORMATTER)),
                String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate).format(FORMATTER)),
                String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate).format(FORMATTER)),
                String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate).format(FORMATTER)),
                String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate).format(FORMATTER)),
                String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate).format(FORMATTER)),
                String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate).format(FORMATTER))
        );

        tvCalendar.getItems().add(wd);
    }

    /**
     * incompletWeekRegistred()
     */
    private void incompletWeekRegistred() {

        ArrayList<String> dates = new ArrayList<String>();

        while (!String.valueOf(CourseRankController.getaListRankDates().get(++cont_aListDate)
                .getDayOfWeek()).equals("MONDAY")) {
            dates.add(String.valueOf(CourseRankController.getaListRankDates().get(cont_aListDate).format(FORMATTER)));
        }

        WeekDates tb = new WeekDates();
        tb.completeWeek(CourseRankController.getFirstDay(), dates);
        tvCalendar.getItems().add(tb);
    }

    /**
     * saveBtnEvent
     */
    public void saveBtnEvent() {

        aListCalBase = new ArrayList<>();
        CalendarioBase cb;
        String day , month , year ;
        long code = -1;

        for (int i = 0; i < celdasSeleccionadas.size(); i++) {

            String[] s = celdasSeleccionadas.get(i).split("/");

            day = s[0];
            month = s[1];
            year = s[2];


//            cb = new CalendarioBase()




        }

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