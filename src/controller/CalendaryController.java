package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.TableRow;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


/**
 * Created by Michael
 */
public class CalendaryController implements Initializable{

    @FXML
    TableView<TableRow> tvCalendary;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFilestoTable();
    }

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

        ObservableList<TableRow> data = FXCollections.observableArrayList(
                new TableRow("15-15-1550", "15-15-1550", "15-15-1550", "15-15-1550",
                        "15-15-1550", "15-15-1550", "15-15-1550")
        );

        tvCalendary.setItems(data);





    }
}
