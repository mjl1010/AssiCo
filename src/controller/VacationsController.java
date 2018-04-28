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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * Created by Michael
 */
public class VacationsController implements Initializable {


    @FXML
    DatePicker datePicker_start, datePicker_end;

    private static HashSet<LocalDate> hsVacations = new HashSet<>();
    private int cont_day = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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
            hsVacations.add(date_temp);
            date_temp = date_temp.plusDays(1);
        }

        filterHolydays();
        openCalendarHolydays();
    }

    /**
     * open Calendar Holy Days
     */
    private void openCalendarHolydays() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/intCalendarHolydays.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Scene scene = new Scene(root, 697, 515);
        stage.setTitle("Holydays Stage");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * filter vacaitions
     */
    private void filterHolydays() {

        ArrayList<LocalDate> aux = new ArrayList<>();

        System.out.println("before : " + RankController.getaListRankDates().size()) ;

        for (int i = 0; i < RankController.getaListRankDates().size(); i++) {
            if (!hsVacations.contains(RankController.getaListRankDates().get(i)))
                aux.add(RankController.getaListRankDates().get(i));
        }

        System.out.println("after : " + aux.size()) ;
        RankController.setaListRankDates(aux);
    }
}
