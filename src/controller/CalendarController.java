package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utilities.WeekDates;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Michael and modified by Manel
 */
public class CalendarController implements Initializable {

    @FXML
    TableView<WeekDates> tvCalendar;

    private int cont_aListDate = -1;

    private ArrayList<String> celdasSeleccionadas = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFilestoTable();
    }

    private static final String CELL_BG_RED = "-fx-background-color: red";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

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

                    if (item != null && !celdasSeleccionadas.isEmpty() && celdasSeleccionadas.contains(item)) {
                        setStyle(CELL_BG_RED);

                        System.out.println("updateTest " + item);
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty() && !(cell.getStyle().equals(CELL_BG_RED))) {
                    //System.out.println("pintar celda " + cell + " | " + cell.getText() + " de red");
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

        System.out.println("numero de semanas : " + RankController.getContWeeks());

        if (!RankController.getFirstDay().equals("MONDAY")) {
            incompletWeekRegistred();
            cont_aListDate--;
            contWeeksReg++;
        }

        for (; contWeeksReg < RankController.getContWeeks() - 1; contWeeksReg++) {
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
            dates.add(String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)));
        } while (!RankController.getaListRankDates().get(cont_aListDate)
                .isAfter(RankController.getEndDay().minusDays(1)));

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
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter))
        );

        tvCalendar.getItems().add(wd);
    }

    /**
     * incompletWeekRegistred()
     */
    private void incompletWeekRegistred() {

        ArrayList<String> dates = new ArrayList<String>();

        while (!String.valueOf(RankController.getaListRankDates().get(++cont_aListDate)
                .getDayOfWeek()).equals("MONDAY")) {
            dates.add(String.valueOf(RankController.getaListRankDates().get(cont_aListDate).format(formatter)));
        }

        WeekDates tb = new WeekDates();
        tb.completeWeek(RankController.getFirstDay(), dates);
        tvCalendar.getItems().add(tb);

    }
}