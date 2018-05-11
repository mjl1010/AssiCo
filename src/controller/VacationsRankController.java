package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entity.CalendarioBase;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.aCalendarioBase;

/**
 * Created by Michael
 */
public class VacationsRankController implements Initializable {


    @FXML
    DatePicker datePicker_start, datePicker_end;

    private static HashSet<String> hsVacations = new HashSet<>();
    private int cont_day = 0;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    /*** Getters and Setters ***/

    public static HashSet<String> getHsVacations() {
        return hsVacations;
    }
    public static void setHsVacations(HashSet<String> hsVacations) {
        VacationsRankController.hsVacations = hsVacations;
    }


    /**** Métodos Agregados ***/

    /**
     *
     * @param actionEvent
     */
    public void generarDates(ActionEvent actionEvent) throws IOException {
        LocalDate init = datePicker_start.getValue();
        LocalDate end = datePicker_end.getValue();
        LocalDate date_temp = init;

        while (!date_temp.isAfter(end)) {
            cont_day++;
            hsVacations.add(date_temp.format(FORMATTER));
            date_temp = date_temp.plusDays(1);
        }

        updateObjectsCalendarBase();
        openCalendarHolydays();
    }

    /**
     * updateObjectsCalendarBase
     */
    private void updateObjectsCalendarBase() {
        for (CalendarioBase cb : aCalendarioBase) {
            if (hsVacations.contains(cb.getIdDate()))
                cb.setSummer(true);

        }
    }

    /**
     * open Calendar Holy Days
     */
    private void openCalendarHolydays() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/intHolydaysCalendar.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Scene scene = new Scene(root, 697, 515);
        stage.setTitle("Holydays Stage");
        stage.setScene(scene);
        stage.show();
    }
}
