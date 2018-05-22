package utilities;


import entity.CalendarioBase;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import static utilities.VariablesAndMethodsUtils.*;

/**
 * Created by Michael
 */
public class TabConfirmaciones {

    private TableView tv_conf;
    private GridPane gp_m1, gp_m2;

    private Label lblTitle1,
                    lblTitle2;

    private Label lblTitle1_val,
            lblTitle2_val;


    private ArrayList<String> celdasVacation = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String cursoAcademico;

    private HashSet<String> hsVacations;

    /**
     * Método Constructor
     * @param tv_conf
     * @param gp_m1
     * @param gp_m2
     */
    public TabConfirmaciones(TableView tv_conf, GridPane gp_m1, GridPane gp_m2) {
        this.tv_conf = tv_conf;
        this.gp_m1 = gp_m1;
        this.gp_m2 = gp_m2;
        lblTitle1 = new Label("Master 1");
        lblTitle2 = new Label("Master 2");
        lblTitle1_val = new Label(master1.getCode());
        lblTitle2_val = new Label(master2.getCode());
        this.gp_m1.setAlignment(Pos.CENTER);
        this.gp_m2.setAlignment(Pos.CENTER);
        configLbl();

        fillHsVacation();
        fillCeldasVacations();
    }

    private void columnsSetting() {
        tv_conf.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv_conf.getSelectionModel().setCellSelectionEnabled(true);
        tv_conf.getColumns().addAll(generarColumna("Lunes"),
                generarColumna("Martes"),
                generarColumna("Miércoles"),
                generarColumna("Jueves"),
                generarColumna("Viernes"),
                generarColumna("Sábado"),
                generarColumna("Domingo"));
    }

    private TableColumn<WeekDates, String> generarColumna(String diaSemana) {
        TableColumn<WeekDates, String> t = new TableColumn<>(diaSemana);

        switch (diaSemana) {
            case "Lunes":
                t.setCellValueFactory(cellData -> cellData.getValue().mondayProperty());
                break;

            case "Martes":
                t.setCellValueFactory(cellData -> cellData.getValue().tuesdayProperty());
                break;

            case "Miércoles":
                t.setCellValueFactory(cellData -> cellData.getValue().wednesdayProperty());
                break;

            case "Jueves":
                t.setCellValueFactory(cellData -> cellData.getValue().thursDayProperty());
                break;

            case "Viernes":
                t.setCellValueFactory(cellData -> cellData.getValue().fridayProperty());
                break;

            case "Sábado":
                t.setCellValueFactory(cellData -> cellData.getValue().saturdayProperty());
                break;

            case "Domingo":
                t.setCellValueFactory(cellData -> cellData.getValue().sundayProperty());
                break;
        }

        t.setCellFactory(tc -> {
            TableCell<WeekDates, String> cell = new TableCell<WeekDates, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                    setStyle(TextResponsive.getFontStyle("h6"));

//                    if ((item != null && celdasVacation.contains(item)))
//                        setStyle(CELL_BG_LIGHTGREY);
//
//                    if (item != null && !celdasSeleccionadas.isEmpty() && celdasSeleccionadas.contains(item)
//                            && !celdasVacation.contains(item)) {
//                        setStyle(CELL_BG_RED);
//                    }
                }
            };
            cell.setOnMouseClicked(e -> {
//                if (!cell.isEmpty() && !(cell.getStyle().equals(CELL_BG_RED))
//                        && !celdasVacation.contains(cell.getText())) {
//                    cell.setStyle(CELL_BG_RED);
//                    celdasSeleccionadas.add(cell.getText());
//                } else if (cell.getStyle().equals(CELL_BG_RED)) {
//                    celdasSeleccionadas.remove(cell.getText());
//                    cell.setStyle(TextResponsive.getFontStyle("h6"));
//                }
            });
            return cell;
        });

        return t;
    }

    private void fillHsVacation() {
        for (CalendarioBase cb : aCalendarioBase) {
            if (cb.isSummer() || cb.isFestivo())
                hsVacations.add(cb.getIdDate());
        }
    }

    private void fillCeldasVacations() {
        Iterator it = hsVacations.iterator();
        while (it.hasNext()) {
            celdasVacation.add(String.valueOf(it.next()));
        }
    }

    private void configLbl() {
        gp_m1.add(lblTitle1, 0, 0);
        gp_m1.add(lblTitle1_val, 1, 0);
    }

    /**** Getters and Setters ****/

    public TableView getTv_conf() {
        return tv_conf;
    }

    public void setTv_conf(TableView tv_conf) {
        this.tv_conf = tv_conf;
    }

    public GridPane getGp_m1() {
        return gp_m1;
    }

    public void setGp_m1(GridPane gp_m1) {
        this.gp_m1 = gp_m1;
    }

    public GridPane getGp_m2() {
        return gp_m2;
    }

    public void setGp_m2(GridPane gp_m2) {
        this.gp_m2 = gp_m2;
    }

    public Label getLblTitle1() {
        return lblTitle1;
    }

    public void setLblTitle1(Label lblTitle1) {
        this.lblTitle1 = lblTitle1;
    }

    public Label getLblTitle2() {
        return lblTitle2;
    }

    public void setLblTitle2(Label lblTitle2) {
        this.lblTitle2 = lblTitle2;
    }
}
