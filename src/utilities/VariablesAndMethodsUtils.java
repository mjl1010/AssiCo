package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *  Created by Michael
 */
public class VariablesAndMethodsUtils {

    /**** Variables ******/

    private static ArrayList<PlanificadorCalendarios> listPlanifCalend = new ArrayList<>();
    public static ObservableList<String> optionsTiposAula;
    private static ObservableList<String> optionsProfes; //TODO rellenar
    public static ArrayList<String> days = new ArrayList<>();
    public static ArrayList<String> months = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static ArrayList<String> listAsignaturas = new ArrayList<>();
    private static ArrayList<Docente> aDocentes = new ArrayList<>();

    static {
        optionsTiposAula = FXCollections.observableArrayList(
                        "A",
                        "M",
                        "P",
                        "T",
                        "V"
                );

        for (int i = 0; i < 14; i++) {
            listAsignaturas.add("Asignatura"+(i+1));
        }

        for (int i = 0; i < 35; i++) {
            aDocentes.add(new Docente(i+1, "D" + (i+1), "profesor" + (i+1)));
        }

        days.add("Lunes");
        days.add("Martes");
        days.add("Miercoles");
        days.add("Jueves");
        days.add("Viernes");
        days.add("Sabado");
        days.add("Domingo");

        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
    }

    /**** Métodos ******/

    /**
     * set Planicador Calendario List
     * @param listPlanifCalend
     */
    public static void setListPlanifCalend(ArrayList<PlanificadorCalendarios> listPlanifCalend) {
        VariablesAndMethodsUtils.listPlanifCalend = listPlanifCalend;
    }

    public static String getMonthInit(){
        String pcIDstring = String.valueOf(listPlanifCalend.get(0).getCalendarioBase().getDia());
        String y = pcIDstring.substring(0, 4);
        String m = pcIDstring.substring(4, 6);
        String d = pcIDstring.substring(6, 8);
        String data = d + "/" + m + "/" + y;
        return LocalDate.parse(data, FORMATTER2).getMonth().name();
    }

    /**
     * getLocalDate_where_PlanifiCalendaID
     * @param planifiCalendaID
     * @return
     */
    public static LocalDate getLocalDate_where_PlanifiCalendaID(int planifiCalendaID){
        String pcIDstring = String.valueOf(planifiCalendaID);
        String y = pcIDstring.substring(0, 4);
        String m = pcIDstring.substring(4, 6);
        String d = pcIDstring.substring(6, 8);
        String data = d + "/" + m + "/" + y;
        return LocalDate.parse(data, FORMATTER2);
    }

    /**
     * metodo de test
     */
    public void test_fillData(){

        Universidad uni = new Universidad(1, "uoc");
        Master master1 = new Master(1, "M01", "master1");
        Master master2 = new Master(2, "M02", "master2");
        Sesion sesion;

        for (int i = 0; i < 114; i++) {
            sesion = new Sesion();
        }

    }
}
