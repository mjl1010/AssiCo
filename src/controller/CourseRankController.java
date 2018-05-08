package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entity.CalendarioBase;
import utilities.VariablesAndMethodsUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

/**
 * Created by Michael
 */
public class CourseRankController {

    @FXML
    DatePicker datePicker_start, datePicker_end;

    private static String firstDay;
    private static LocalDate endDay;
    private static int contWeeks;
    private int cont_day = 0;
    private static ArrayList<CalendarioBase> aListCalBase;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");


    /***** Gettes and Setters ****/

    public static ArrayList<CalendarioBase> getaListCalBase() {
        return aListCalBase;
    }
    public static void setaListCalBase(ArrayList<CalendarioBase> aListCalBase) {
        CourseRankController.aListCalBase = aListCalBase;
    }
    public static String getFirstDay() {
        return firstDay;
    }
    public static int getContWeeks() {
        return contWeeks;
    }
    public static LocalDate getEndDay() {
        return endDay;
    }



    /******* Métodos agregados *******/

    /**
     * generador de fechas
     */
    @FXML
    private void generarDates() throws IOException {

        LocalDate init = datePicker_start.getValue();
        LocalDate end = datePicker_end.getValue();
        LocalDate date_temp = init;
        CalendarioBase cb;

        aListCalBase = new ArrayList<>();
        while (!date_temp.isAfter(end)) {
            if (date_temp.getDayOfWeek().name().equals("MONDAY")) {
                contWeeks++;
            }
            cont_day++;
            if (cont_day == 1) firstDay = date_temp.getDayOfWeek().name();
            aListCalBase.add(generarObjectCalendarBase(date_temp));
            date_temp = date_temp.plusDays(1);
        }

        if (!firstDay.equals("MONDAY")) {
            contWeeks++;
        }
        endDay = date_temp.minusDays(1);
        openRankHolydays();

    }

    /**
     *
     * @param date_temp
     * @return obj CalendarBase
     */
    public static CalendarioBase generarObjectCalendarBase(LocalDate date_temp) {
        return new CalendarioBase(Integer.parseInt(date_temp.format(DateTimeFormatter.BASIC_ISO_DATE)),
                VariablesAndMethodsUtils.uni, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(date_temp),
                 date_temp.getDayOfWeek().getValue(), date_temp.format(FORMATTER), "");
    }

    /**
     * open calendary
     */
    public void openRankHolydays() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/intVacationsRank.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Scene scene = new Scene(root, 495, 307);
        stage.setTitle("Holydays Stage");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * close stage
     * @param stage
     */
    public static void closeStage(Stage stage){
        stage.close();
    }

}
