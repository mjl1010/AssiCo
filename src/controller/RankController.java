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

    private ArrayList<LocalDate> aListRankDates = new ArrayList<>();
    private String firstDay;

    private int cont_day = 0;

    /**
     * generador de fechas
     */
    @FXML
    private void generarDates() throws IOException {

        LocalDate init = datePicker_start.getValue();
        LocalDate end = datePicker_end.getValue();
        LocalDate date_temp = init;

        while (date_temp.isBefore(end)) {

            cont_day++;
            if (cont_day == 1) firstDay = date_temp.getDayOfWeek().name();
            aListRankDates.add(date_temp);
            date_temp = date_temp.plusDays(1);

        }
        
        datesComplement();
        openCalendary();

        
    }

    /**
     * complmenta la semana inicial
     * y final
     */
    private void datesComplement() {
        
    }

    /**
     * open calendary
     */
    public void openCalendary() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/intCalendary.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Calendary Stage");
        stage.setScene(new Scene(root, 850, 850));
        stage.show();
    }

    /**
     * close stage
     * @param stage
     */
    public static void closeStage(Stage stage){
        stage.close();
    }
    

    public ArrayList<LocalDate> getaListRankDates() {
        return aListRankDates;
    }
    
    public String getFirstDay() {
        return firstDay;
    }
    
}
