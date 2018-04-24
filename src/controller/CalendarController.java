package controller;

import javafx.beans.value.WritableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.WeekDates;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Michael
 */
public class CalendarController implements Initializable {

    @FXML
    TableView<WeekDates> tvCalendar;

    private int cont_aListDate = -1;

    private ArrayList<TableCell> celdasSeleccionadas = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFilestoTable();
    }

    private static final String CELL_BG_RED = "-fx-background-color: red";
    private static final String CELL_BG_NONE = "-fx-background-color: green";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");


    private void addFilestoTable() {


        TableColumn<WeekDates, String> t1 = new TableColumn<>("Monday");
        t1.setCellValueFactory(cellData -> cellData.getValue().mondayProperty());
        t1.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);

                    setStyle(CELL_BG_NONE);
                    for (TableCell tableCell : celdasSeleccionadas) {
                        if (tableCell.getText().replaceAll("/", "").equalsIgnoreCase(item.replaceAll("/", ""))) {
                            setStyle(CELL_BG_RED);
                            System.out.println("updateTest " + item + " " + tableCell.getItem());
                        }
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty() && !(cell.getStyle().equals(CELL_BG_RED))) {
                    String userId = cell.getItem();
                    System.out.println("pintar celda " + cell + " | " + cell.getItem() + " de red");
                    cell.setStyle(CELL_BG_RED);
                    celdasSeleccionadas.add(cell);
                } else if (cell.getStyle().equals(CELL_BG_RED)) {

                    cell.setStyle("");
                }
            });
            return cell;
        });

//        t1.setCellFactory(c -> new DayCell());

        TableColumn<WeekDates, String> t2 = new TableColumn<>("Tuesday");
        t2.setCellValueFactory(cellData -> cellData.getValue().tuesdayProperty());
        t2.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()
                        && !(cell.getStyle().equals(CELL_BG_RED))) {
                    String userId = cell.getItem();
                    System.out.println("pintar celda " + cell + "de red");
                    cell.setStyle(CELL_BG_RED);

                } else if (cell.getStyle().equals(CELL_BG_RED))

                    cell.setStyle("");

            });
            return cell ;
        });

        TableColumn<WeekDates, String> t3 = new TableColumn<>("Wednesday");
        t3.setCellValueFactory(cellData -> cellData.getValue().wednesdayProperty());
        t3.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()
                        && !(cell.getStyle().equals(CELL_BG_RED))) {
                    String userId = cell.getItem();
                    System.out.println("pintar celda " + cell + "de red");
                    cell.setStyle(CELL_BG_RED);

                } else if (cell.getStyle().equals(CELL_BG_RED))

                    cell.setStyle("");

            });
            return cell ;
        });

        TableColumn<WeekDates, String> t4 = new TableColumn<>("ThursDay");
        t4.setCellValueFactory(cellData -> cellData.getValue().thursDayProperty());
        t4.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()
                        && !(cell.getStyle().equals(CELL_BG_RED))) {
                    String userId = cell.getItem();
                    System.out.println("pintar celda " + cell + "de red");
                    cell.setStyle(CELL_BG_RED);

                } else if (cell.getStyle().equals(CELL_BG_RED))

                    cell.setStyle("");

            });
            return cell ;
        });

        TableColumn<WeekDates, String> t5 = new TableColumn<>("Friday");
        t5.setCellValueFactory(cellData -> cellData.getValue().fridayProperty());
        t5.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()
                        && !(cell.getStyle().equals(CELL_BG_RED))) {
                    String userId = cell.getItem();
                    System.out.println("pintar celda " + cell + "de red");
                    cell.setStyle(CELL_BG_RED);

                } else if (cell.getStyle().equals(CELL_BG_RED))

                    cell.setStyle("");

            });
            return cell ;
        });

        TableColumn<WeekDates, String> t6 = new TableColumn<>("Saturday");
        t6.setCellValueFactory(cellData -> cellData.getValue().saturdayProperty());
        t6.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()
                        && !(cell.getStyle().equals(CELL_BG_RED))) {
                    String userId = cell.getItem();
                    System.out.println("pintar celda " + cell + "de red");
                    cell.setStyle(CELL_BG_RED);

                } else if (cell.getStyle().equals(CELL_BG_RED))

                    cell.setStyle("");

            });
            return cell ;
        });

        TableColumn<WeekDates, String> t7 = new TableColumn<>("Sunday");
        t7.setCellValueFactory(cellData -> cellData.getValue().sundayProperty());
        t7.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()
                        && !(cell.getStyle().equals(CELL_BG_RED))) {
                    String userId = cell.getItem();
                    System.out.println("pintar celda " + cell + "de red");
                    cell.setStyle(CELL_BG_RED);

                } else if (cell.getStyle().equals(CELL_BG_RED))

                    cell.setStyle("");

            });
            return cell ;
        });

        tvCalendar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tvCalendar.getSelectionModel().setCellSelectionEnabled(true);
        tvCalendar.getColumns().addAll(t1, t2, t3, t4, t5, t6, t7);

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

        for (; contWeeksReg < RankController.getContWeeks() - 1; contWeeksReg++) {
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

        WeekDates tb = new WeekDates(dates.get(++cont), dates.get(++cont), dates.get(++cont), dates.get(++cont),
                dates.get(++cont), dates.get(++cont), dates.get(++cont));

        tvCalendar.getItems().add(tb);
    }

    /**
     * week Registred
     */
    private void completWeekRegistred() {

        WeekDates tb = new WeekDates(
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter)),
                String.valueOf(RankController.getaListRankDates().get(++cont_aListDate).format(formatter))
        );

        tvCalendar.getItems().add(tb);
    }

    /**
     * incompletWeekRegistred()
     */
    private void incompletWeekRegistred() {

        ArrayList<String> dates = new ArrayList<>();

        while (!String.valueOf(RankController.getaListRankDates().get(++cont_aListDate)
                .getDayOfWeek()).equals("MONDAY")) {
            dates.add(String.valueOf(RankController.getaListRankDates().get(cont_aListDate).format(formatter)));
        }

        WeekDates tb = new WeekDates();
        tb.completeWeek(RankController.getFirstDay(), dates);
        tvCalendar.getItems().add(tb);

    }

    @FXML
    public void clickItem(MouseEvent event) {

//            tvCalendar.getSelectionModel().getSelectedCells().get(0)

//            tvCalendar.getSelectionModel().getSelectedCells().get(0).getTableColumn()
//                    .setCellFactory(c -> new DayCell());

        }
//    }

    public class DayCell extends TableCell<WeekDates, String> {

        {
            setOnMouseClicked(evt -> {
                if (!isEmpty() && getItem() != null && evt.getButton() == MouseButton.PRIMARY) {
                    WritableValue<String> property = (WritableValue<String>) getTableColumn().getCellObservableValue((WeekDates) getTableRow().getItem());
//                    property.setValue(!getItem());
                }
            });
        }


        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setStyle(null);
            } else {
                System.out.println("ahora estoy desde aqui");
                setStyle("-fx-background-color: red;");
            }
        }
    }
}
