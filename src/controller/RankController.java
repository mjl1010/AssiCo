package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Michael
 */
public class RankController {

    @FXML
    DatePicker datePicker_start;

    @FXML
    DatePicker datePicker_end;

    @FXML
    Button btnGenerarDates;

    private static ArrayList<LocalDate> aListRankDates = new ArrayList<>();
    private static String firstDay;
    private static LocalDate endDay;
    private static int contWeeks;

    private int cont_day = 0;


    /***** Gettes and Setters ****/

    public static ArrayList<LocalDate> getaListRankDates() {
        return aListRankDates;
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

        while (!date_temp.isAfter(end)) {
            if (date_temp.getDayOfWeek().name().equals("MONDAY")) {
                System.out.println("1 semana x Lunes");
                contWeeks++;
            }
            System.out.println("dia : " + date_temp.getDayOfWeek() + "-" + date_temp.getDayOfMonth());
            cont_day++;
            if (cont_day == 1) firstDay = date_temp.getDayOfWeek().name();
            aListRankDates.add(date_temp);
            date_temp = date_temp.plusDays(1);
        }

        System.out.println("\nsize list dates : " + aListRankDates.size());

        if (!firstDay.equals("MONDAY")) {
            System.out.println("Me debias 1 semana, parcero !");
            contWeeks++;
        }
        endDay = aListRankDates.get(aListRankDates.size()-1);
        openCalendary();

    }


    /**
     * open calendary
     */
    public void openCalendary() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/intCalendary.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Calendary Stage");
        stage.setScene(new Scene(root, 673, 478));
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
