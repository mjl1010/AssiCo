package utilities;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Michael
 */
public class VariablesAndMethodsUtils {

    /**** Variables ******/
    public static ArrayList<CalendarioBase> aCalendarioBase = new ArrayList<>();
    public static ArrayList<PlanificacionCalendarios> aPlanifCalend = new ArrayList<>();
    public static ArrayList<PlanificacionCalendarios> aPlanCalCurrentMonthMaster1 = new ArrayList<>();
    public static ArrayList<PlanificacionCalendarios> aPlanCalCurrentMonthMaster2 = new ArrayList<>();
    public static ArrayList<Sesion> aSession = new ArrayList<>();
    public static ObservableList<String> aTiposAula;
    private static ObservableList<String> aProfes; //TODO rellenar
    public static ArrayList<String> aDays = new ArrayList<>();
    public static ArrayList<String> aMonths = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static ArrayList<String> aListAsignaturas = new ArrayList<>();
    private static ArrayList<Docente> aDocentes = new ArrayList<>();

    public static final String PATH_PROPERTIES = "config/init.properties";
    public static Universidad uni;
    public static String curso;
    public static Master master1;
    public static Master master2;

    private static int sesionID = 0;
    private static final ArrayList<String> aTipoSession = new ArrayList<>();

    private static ArrayList<String> dateTest = new ArrayList<>();

    static {
        uni = new Universidad(1, "uoc");
        master1 = new Master(1, "M01", "master1");
        master2 = new Master(2, "M02", "master2");
        aTiposAula = FXCollections.observableArrayList(
                "A", "M", "P", "T", "V"
        );
        aTipoSession.add("J");
        aTipoSession.add("S");

        aDays.add("Lunes");
        aDays.add("Martes");
        aDays.add("Miercoles");
        aDays.add("Jueves");
        aDays.add("Viernes");
        aDays.add("Sabado");
        aDays.add("Domingo");

        aMonths.add("January");
        aMonths.add("February");
        aMonths.add("March");
        aMonths.add("April");
        aMonths.add("May");
        aMonths.add("June");
        aMonths.add("July");
        aMonths.add("August");
        aMonths.add("September");
        aMonths.add("October");
        aMonths.add("November");
        aMonths.add("December");
        
    }

    /**** Métodos ******/

    /**
     * set Planicador Calendario List
     *
     * @param aPlanCalCurrentMonthMaster1
     */
    public static void setaPlanCalCurrentMonthMaster1(ArrayList<PlanificacionCalendarios> aPlanCalCurrentMonthMaster1) {
        VariablesAndMethodsUtils.aPlanCalCurrentMonthMaster1 = aPlanCalCurrentMonthMaster1;
    }

    public static String getMonthInit() {
        String pcIDstring = String.valueOf(aPlanCalCurrentMonthMaster1.get(0).getCalendarioBase().getDia());
        String y = pcIDstring.substring(0, 4);
        String m = pcIDstring.substring(4, 6);
        String d = pcIDstring.substring(6, 8);
        String data = d + "/" + m + "/" + y;
        return LocalDate.parse(data, FORMATTER2).getMonth().name();
    }

    /**
     * getLocalDate_where_PlanifiCalendaID
     *
     * @param planifiCalendaID
     * @return
     */
    public static LocalDate getLocalDate_where_PlanifiCalendaID(int planifiCalendaID) {
        String pcIDstring = String.valueOf(planifiCalendaID);
        String y = pcIDstring.substring(0, 4);
        String m = pcIDstring.substring(4, 6);
        String d = pcIDstring.substring(6, 8);
        String data = d + "/" + m + "/" + y;
        return LocalDate.parse(data, FORMATTER2);
    }

    /**
     * registra data
     * para test
     */
    public static void addData() {
        regDocentes();
        regAsignaturas();
        regSesiones();
    }


    private static void regSesiones() {
        for (int i = 0; i < aListAsignaturas.size(); i++) {
            switch (i) {
                case 0:
                    addSession(i, 3, true, false);
                    break;
                case 1:
                    addSession(i, 14, false, false);
                    break;
                case 2:
                    addSession(i, 8, true, false);
                    break;
                case 3:
                    addSession(i, 11, true, false);
                    break;
                case 4:
                    addSession(i, 6, true, false);
                    break;
                case 5:
                    addSession(i, 6, true, false);
                    break;
                case 6:
                    addSession(i, 9, true, false);
                    break;
                case 7:
                    addSession(i, 9, false, false);
                    break;
                case 8:
                    addSession(i, 11, false, false);
                    break;
                case 9:
                    addSession(i, 1, true, false);
                    break;
                case 10:
                    addSession(i, 9, false, false);
                    break;
                case 11:
                    addSession(i, 16, false, false);
                    break;
                case 12:
                    addSession(i, 16, false, false);
                    break;
                case 13:
                    addSession(i, 18, true, false);
                    break;
                case 14:
                    addSession(i, 11, true, false);
                    break;
                case 15:
                    addSession(i, 9, true, false);
                    break;
                case 16:
                    addSession(i, 7, false, false);
                    break;
            }
        }
        Collections.shuffle(aSession);
    }


    private static void addSession(int indexAsign, int quantCont, boolean isComun,
                        boolean isDocenteDoble){
        Master master1_aux = master1, master2_aux = master2;
        Docente docente1 = aDocentes.get(new Random().nextInt(aDocentes.size()));
        Docente docente2 = aDocentes.get(new Random().nextInt(aDocentes.size())); //TODO validar repetir docenteID
        String contenido = "Asg" + (indexAsign + 1) + "cont";
        String aula = String.valueOf(Math.floor(Math.random() * (210 - 201 + 1) + 201));
        String tipoAula = aTiposAula.get(new Random().nextInt(aTiposAula.size()));

        if (!isComun) master2_aux = null;
        if (!isDocenteDoble) docente2 = null;

        for (int j = 0; j < quantCont; j++)
            aSession
                    .add(new Sesion(++sesionID, master1_aux, master2_aux, aListAsignaturas.get(indexAsign),
                            contenido + j, docente1, docente2, aula, tipoAula));
    }

    private static void regAsignaturas() {
        for (int i = 0; i < 17; i++)
            aListAsignaturas
                    .add("Asignatura" + (i + 1));
    }

    private static void regDocentes() {
        for (int i = 0; i < 40; i++)
            aDocentes
                    .add(new Docente(i + 1, "D" + (i + 1),
                            "profesor" + (i + 1)));
    }

}
