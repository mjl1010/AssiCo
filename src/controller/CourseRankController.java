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

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Properties;

import static utilities.VariablesAndMethodsUtils.PATH_PROPERTIES;
import static utilities.VariablesAndMethodsUtils.aCalendarioBase;

/**
 * Created by Michael
 */
public class CourseRankController {

    @FXML
    DatePicker datePicker_start, datePicker_end;

    private static String firstDayName;

    private static int contWeeks;
    private int cont_day = 0;
//    private static ArrayList<CalendarioBase> aListCalBase;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    public static LocalDate firtDay;
    public static LocalDate endDay;
    private Properties p;


    /***** Gettes and Setters ****/
    public static String getFirstDayName() {
        return firstDayName;
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

        aCalendarioBase = new ArrayList<>();
        while (!date_temp.isAfter(end)) {
            if (date_temp.getDayOfWeek().name().equals("MONDAY")) {
                contWeeks++;
            }
            cont_day++;
            if (cont_day == 1) {
                firtDay = date_temp;
                firstDayName = firtDay.getDayOfWeek().name();
            }
            aCalendarioBase.add(generarObjectCalendarBase(date_temp));
            date_temp = date_temp.plusDays(1);
        }

        if (!firstDayName.equals("MONDAY")) {
            contWeeks++;
        }
        endDay = date_temp.minusDays(1);
        updateInitData();
        openRankHolydays();

    }

    /**
     * update properti
     */
    private void updateInitData()  {
        p = new Properties();
        VariablesAndMethodsUtils.curso = CourseRankController.firtDay.getYear() + "-"
                + CourseRankController.endDay.getYear();

        try {
            p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
            p.setProperty("curso", VariablesAndMethodsUtils.curso);
            p.setProperty("year", String.valueOf(CourseRankController.firtDay.getYear()));
            p.setProperty("month", String.valueOf(CourseRankController.firtDay.getMonth().name()));
            p.store(new FileWriter(PATH_PROPERTIES), "first comment");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getCause() + "&" + e.getMessage());
        }
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
