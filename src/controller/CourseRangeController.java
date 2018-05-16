package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entity.CalendarioBase;
import start.MainLogin;
import utilities.AlertHelper;
import utilities.TextResponsive;
import utilities.VariablesAndMethodsUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.PATH_PROPERTIES;
import static utilities.VariablesAndMethodsUtils.aCalendarioBase;

/**
 * Created by Michael
 */
public class CourseRangeController implements Initializable {
    private static CourseRangeController main;

    @FXML
    DatePicker initCurso, endCurso, initVacations, endVacations;

    @FXML
    Label courseRange1, courseRange2, courseRange3, courseRange4;

    @FXML
    Button generarCurso;

    private static String firstDayName;

    private static int contWeeks;
    private int cont_day = 0;
//    private static ArrayList<CalendarioBase> aListCalBase;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    public static LocalDate firtDay;
    public static LocalDate endDay;
    private Properties p;

    private static HashSet<String> hsVacations = new HashSet<>();
    private int cont_vacation_day = 0;


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
    private void generarCurso() throws IOException {

        try {
            LocalDate init = initCurso.getValue();
            LocalDate end = endCurso.getValue();
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

            init = initVacations.getValue();
            end = endVacations.getValue();
            date_temp = init;

            while (!date_temp.isAfter(end)) {
                cont_vacation_day++;
                hsVacations.add(date_temp.format(FORMATTER));
                date_temp = date_temp.plusDays(1);
            }

            updateObjectsCalendarBase();
            openCalendarHolydays();
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, generarCurso.getScene().getWindow(), "AssiCo - Error", "No se ha podido generar el calendario del curso por un valor mal introducido. O un error interno...");
        }
    }

    /**
     * update properti
     */
    private void updateInitData()  {
        p = new Properties();
        VariablesAndMethodsUtils.curso = CourseRangeController.firtDay.getYear() + "-"
                + CourseRangeController.endDay.getYear();

        try {
            p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
            p.setProperty("curso", VariablesAndMethodsUtils.curso);
            p.setProperty("year", String.valueOf(CourseRangeController.firtDay.getYear()));
            p.setProperty("month", String.valueOf(CourseRangeController.firtDay.getMonth().name()));
            p.store(new FileWriter(PATH_PROPERTIES), "Update because new course");

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


    //                          //
    //                          //
    //      VACATIONS RANGE     //
    //                          //
    //                          //


    public static HashSet<String> getHsVacations() {
        return hsVacations;
    }

    public static void setHsVacations(HashSet<String> hsVacations) {
        hsVacations = hsVacations;
    }


    /**** Métodos Agregados ***/

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
        MainLogin.openStage(getClass().getResource("/view/intHolydaysCalendar.fxml"), "Planificaciones - Selector de días festivos", null);
        ((Stage) generarCurso.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        if (HeaderController.main.titulo != null) HeaderController.main.titulo.setText("Rangos de fechas del curso y vacaciones");
        refreshText();
    }

    public static void refreshText() {
        if (main == null) return;

        main.initCurso.setStyle(TextResponsive.getFontStyle("h5"));
        main.endCurso.setStyle(TextResponsive.getFontStyle("h5"));
        main.initVacations.setStyle(TextResponsive.getFontStyle("h5"));
        main.endVacations.setStyle(TextResponsive.getFontStyle("h5"));

        main.courseRange1.setStyle(TextResponsive.getFontStyle("h4"));
        main.courseRange2.setStyle(TextResponsive.getFontStyle("h4"));
        main.courseRange3.setStyle(TextResponsive.getFontStyle("h4"));
        main.courseRange4.setStyle(TextResponsive.getFontStyle("h4"));

        if (main.generarCurso != null) main.generarCurso.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #dddddd; -fx-border-color: #dddddd; -fx-border-radius: 4px; -fx-background-radius: 4px;");

    }
}
