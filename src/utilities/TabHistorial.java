package utilities;


import javafx.scene.control.*;

/**
 * Created by Michael
 */
public class TabHistorial {

    private TableView tv_historial;
    private ComboBox cbo_master;


    /**
     * Método Constructor
     * @param tv_historial
     */
    public TabHistorial(TableView tv_historial, ComboBox cbo_master) {
        this.tv_historial = tv_historial;
        this.cbo_master = cbo_master;
    }

    private void columnsSetting() {
        tv_historial.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv_historial.getSelectionModel().setCellSelectionEnabled(true);
        tv_historial.getColumns().addAll(generarColumna("Lunes"),
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
        return t;
    }

    public TableView getTv_historial() {
        return tv_historial;
    }

    public void setTv_historial(TableView tv_historial) {
        this.tv_historial = tv_historial;
    }

    public ComboBox getCbo_master() {
        return cbo_master;
    }

    public void setCbo_master(ComboBox cbo_master) {
        this.cbo_master = cbo_master;
    }
}
