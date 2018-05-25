package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import entity.CalendarioBase;
import model.DatosModel;
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
import java.util.*;

import static utilities.VariablesAndMethodsUtils.*;

/**
 * Created by Michael
 */
public class CourseRangeController implements Initializable {
    private static CourseRangeController main;

    @FXML
    TextField nameCurso;

    @FXML
    DatePicker initCurso, endCurso, initVacations, endVacations;

    @FXML
    Label courseRange0, courseRange1, courseRange2, courseRange3, courseRange4;

    @FXML
    Button generarCurso, generarCursoBack;


    private static String firstDayName;

    private static int contWeeks;
    private int cont_day = 0;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    private Properties p;
    public static Map<String, Object> datosForm = new HashMap<>();
    private static HashSet<String> hsVacations = new HashSet<>();
    private int cont_vacation_day = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        if (HeaderController.main.titulo != null) HeaderController.main.titulo.setText("Rangos de fechas del curso y vacaciones");
        if (!datosForm.isEmpty()) {
            nameCurso.setText((String) datosForm.get("nameCurso"));
            initCurso.setValue((LocalDate) datosForm.get("initCurso"));
            initVacations.setValue((LocalDate) datosForm.get("initVacations"));
            endVacations.setValue((LocalDate) datosForm.get("endVacations"));
            endCurso.setValue((LocalDate) datosForm.get("endCurso"));
        }
        refreshText();
    }



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
        datosForm.put("nameCurso", nameCurso.getText());
        datosForm.put("initCurso", initCurso.getValue());
        datosForm.put("initVacations", initVacations.getValue());
        datosForm.put("endVacations", endVacations.getValue());
        datosForm.put("endCurso", endCurso.getValue());

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


    /**
     *
     * @param date_temp
     * @return obj CalendarBase
     */
    public static CalendarioBase generarObjectCalendarBase(LocalDate date_temp) {
        return new CalendarioBase(Integer.parseInt(date_temp.format(DateTimeFormatter.BASIC_ISO_DATE)),
                uni,
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(date_temp),
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(date_temp),
                date_temp.getDayOfWeek().getValue(),
                false,
                false,
                true,
                (date_temp.getYear()-1) + "-" + date_temp.getYear(),
                date_temp.format(FORMATTER));
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
        SesionsCalendarController.nombre_curso = nameCurso.getText();
        MainLogin.openStage(getClass().getResource("/view/intHolydaysCalendar.fxml"), "Planificaciones - Selector de días festivos", null);
        ((Stage) generarCurso.getScene().getWindow()).close();
    }


    @FXML
    private void back() {
        datosForm.put("nameCurso", nameCurso.getText());
        datosForm.put("initCurso", initCurso.getValue());
        datosForm.put("initVacations", initVacations.getValue());
        datosForm.put("endVacations", endVacations.getValue());
        datosForm.put("endCurso", endCurso.getValue());
        MainLogin.openStage(getClass().getResource("/view/intSelectCourse.fxml"), "Planificaciones - Selector de cursos", null);
        ((Stage) generarCurso.getScene().getWindow()).close();
    }

    public static void refreshText() {
        if (main == null) return;

        main.nameCurso.setStyle(TextResponsive.getFontStyle("h5"));
        main.initCurso.setStyle(TextResponsive.getFontStyle("h5"));
        main.endCurso.setStyle(TextResponsive.getFontStyle("h5"));
        main.initVacations.setStyle(TextResponsive.getFontStyle("h5"));
        main.endVacations.setStyle(TextResponsive.getFontStyle("h5"));

        main.courseRange0.setStyle(TextResponsive.getFontStyle("h4"));
        main.courseRange1.setStyle(TextResponsive.getFontStyle("h4"));
        main.courseRange2.setStyle(TextResponsive.getFontStyle("h4"));
        main.courseRange3.setStyle(TextResponsive.getFontStyle("h4"));
        main.courseRange4.setStyle(TextResponsive.getFontStyle("h4"));

        main.generarCurso.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #9fff3f; -fx-border-color: transparent; -fx-background-radius: 4px;");
        main.generarCursoBack.setStyle(TextResponsive.getFontStyle("h5") + " -fx-text-fill: #000000; -fx-background-color: #ff5959; -fx-border-color: transparent; -fx-background-radius: 4px;");

    }
}
