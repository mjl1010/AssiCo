package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.TableRow;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Michael
 */
public class CalendaryController implements Initializable {

    @FXML
    TableView<TableRow> tvCalendary;

    private int cont_aListDate = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFilestoTable();
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");


    private void addFilestoTable() {

        TableColumn<model.TableRow, String> t1 = new TableColumn<>("Monday");
        t1.setCellValueFactory(new PropertyValueFactory<TableRow, String>("dateMonday"));

        TableColumn<model.TableRow, String> t2 = new TableColumn<>("Tuesday");
        t2.setCellValueFactory(new PropertyValueFactory<TableRow, String>("dateTuesday"));

        TableColumn<model.TableRow, String> t3 = new TableColumn<>("Wednesday");
        t3.setCellValueFactory(new PropertyValueFactory<TableRow, String>("dateWednesday"));


        TableColumn<model.TableRow, String> t4 = new TableColumn<>("ThursDay");
        t4.setCellValueFactory(new PropertyValueFactory<TableRow, String>("dateThursDay"));

        TableColumn<model.TableRow, String> t5 = new TableColumn<>("Friday");
        t5.setCellValueFactory(new PropertyValueFactory<TableRow, String>("dateFriday"));


        TableColumn<model.TableRow, String> t6 = new TableColumn<>("Saturday");
        t6.setCellValueFactory(new PropertyValueFactory<TableRow, String>("dateSaturday"));


        TableColumn<model.TableRow, String> t7 = new TableColumn<>("Sunday");
        t7.setCellValueFactory(new PropertyValueFactory<TableRow, String>("dateSunday"));

        tvCalendary.getColumns().addAll(t1, t2, t3, t4, t5, t6, t7);
        rellenarTableView();

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

        for (; contWeeksReg < RankController.getContWeeks() - 1 ; contWeeksReg++) {
            completWeekRegistred();
        }

        endWeekRegistred();

    }

    /**
     * end Week Registred
     */
    private void endWeekRegistred() {

        ArrayList<String> dates = new ArrayList<>();
        int cont = -1;

        do {

            dates.add(String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)));

        } while (!RankController.getaListRankDates().get(cont_aListDate)
                .isAfter(RankController.getEndDay().minusDays(1)));

        int aux = 7 - dates.size();
        for (int i = 0; i < aux; i++) {
            dates.add("");
        }

        TableRow tb = new TableRow(dates.get(++cont), dates.get(++cont), dates.get(++cont), dates.get(++cont),
                dates.get(++cont), dates.get(++cont), dates.get(++cont));

        tvCalendary.getItems().add(tb);
    }

    /**
     * week Registred
     */
    private void completWeekRegistred() {

        TableRow tb = new TableRow(
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter))
        );

        tvCalendary.getItems().add(tb);
    }

    /**
     * incompletWeekRegistred()
     */
    private void incompletWeekRegistred() {

        ArrayList<String> dates = new ArrayList<>();

         while (!String.valueOf(RankController.getaListRankDates().get(++cont_aListDate)
                .getDayOfWeek()).equals("MONDAY")) {
            System.out.println("cont_aListDate++");
            dates.add(String.valueOf(RankController.getaListRankDates().get(cont_aListDate).format(formatter)));
        }

        TableRow tb = new TableRow();
        tb.completeWeek(RankController.getFirstDay(), dates);
//        ObservableList<TableRow> data = FXCollections.observableArrayList(tb);
        tvCalendary.getItems().add(tb);

    }
}
