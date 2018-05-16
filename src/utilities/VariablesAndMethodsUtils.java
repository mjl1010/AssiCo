package utilities;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.GridSesion;
import start.MainLogin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Michael
 */
public class VariablesAndMethodsUtils {

    // Varibales
    public static ArrayList<CalendarioBase> aCalendarioBase = new ArrayList<>();
    public static ArrayList<DiaPlanificado> aPlanifCalend = new ArrayList<>();
    public static ArrayList<DiaPlanificado> aPlanCalCurrentMonthMaster1 = new ArrayList<>();
    public static ArrayList<DiaPlanificado> aPlanCalCurrentMonthMaster2 = new ArrayList<>();
    public static ArrayList<Sesion> aSession = new ArrayList<>();
    public static ObservableList<String> aTiposAula;
    public static ArrayList<String> aDays = new ArrayList<>();
    public static ArrayList<String> aMonths = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static ArrayList<String> aListAsignaturas = new ArrayList<>();
    public static ArrayList<Docente> aDocentes = new ArrayList<>();
    public static ObservableList<String> aDocentesID = FXCollections.observableArrayList();
    public static String PATH_PROPERTIES = "config.properties";
    public static Universidad uni;
    public static String curso;
    public static Master master1;
    public static Master master2;

    private static int sesionID = 0;
    private static final ArrayList<String> aTipoSession = new ArrayList<>();

    // var crud ses


    /**
     * static function
     */
    static {
        uni = new Universidad(1, "uoc");
        master1 = new Master(1, "M01", "master1");
        master2 = new Master(2, "M02", "master2");
        master1.setMasterVinculado(master2);
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

        addData();

    }

    /**** Métodos ******/



    /**
     * close stage
     * @param stage
     */
    public static void closeStage(Stage stage){
        stage.close();
    }

    /**
     * close stage
     * @param stage
     */
    public static void closeStage(Window stage){
        ((Stage) stage).close();
    }

    /**
     * add sesion into PlanificacionList
     * @param sesion
     * @param calBaseID
     */
    public static void addSesionToPlanifList(Sesion sesion, int calBaseID, Master master){
        for (DiaPlanificado dp :
            aPlanifCalend) {
            if (dp.getMaster().equals(master) &&
                    dp.getCalendarioBase().getDia() == calBaseID) {
                dp.setSesion(sesion);
                break;
            }
        }
    }

    /**
     *
     * @param masterID
     * @return
     */
    public static Master getMasterSes(String masterID) {
        Master m;
        if (master1.getCode().equals(masterID)) m = master1;
        else m = master2;
        return m;
    }

    /**
     * getSesion
     * @param sesionID
     * @return
     */
    public static Sesion getSesion(int sesionID) {
        Sesion ses = null;
        for (Sesion s :
                aSession) {
            if (s.getId() == sesionID){
                ses = s;
                break;
            }
        }
        return ses;
    }

    /**
     * getCalBasID
     * @param dateFormat
     * @return
     */
    public static int getCalBasID(String dateFormat) {
        int calBasID = -1;
        for (CalendarioBase cb :
                aCalendarioBase) {
            if (cb.getIdDate().equalsIgnoreCase(dateFormat)) calBasID = cb.getDia();
        }
        return calBasID;
    }

    /**
     * remove sesion into PlanificacionList
     * @param calBaseID
     */
    public static void removeSesionToPlanifList(int calBaseID, Master master){
        for (DiaPlanificado dp :
                aPlanifCalend) {
            if (dp.getMaster().equals(master) &&
                    dp.getCalendarioBase().getDia() == calBaseID) {
                dp.setSesion(null); //TODO validar para sesiones comunes
                break;
            }
        }
    }




    /**
     * set Planicador Calendario List
     *
     * @param aPlanCalCurrentMonthMaster1
     */
    public static void setaPlanCalCurrentMonthMaster1(ArrayList<DiaPlanificado> aPlanCalCurrentMonthMaster1) {
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
        sortListSession();
    }

    private static void sortListSession() {
        Collections.sort(aSession, new Comparator<Sesion>() {
            @Override
            public int compare(Sesion o1, Sesion o2) {
                return o1.getId() - o2.getId();
            }
        });
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
        String aula = String.valueOf(201 + (int)(Math.random() * 210));
        String tipoAula = aTiposAula.get(new Random().nextInt(aTiposAula.size()));

        if (!isComun) master2_aux = null;
        if (!isDocenteDoble) docente2 = null;

        for (int j = 0; j < quantCont; j++) {
            Sesion sesion = new Sesion(++sesionID, master1_aux, master2_aux, aListAsignaturas.get(indexAsign),
                    contenido + j, docente1, docente2, aula, tipoAula);

            sesion.setColorFondo(MainLogin.generateRandomColor());
            sesion.setColorTexto("#000000");

            aSession.add(sesion);
        }

    }

    private static void regAsignaturas() {
        for (int i = 0; i < 17; i++)
            aListAsignaturas
                    .add("Asignatura" + (i + 1));
    }

    private static void regDocentes() {
        String code = "";
        String prof_name = "";
        for (int i = 0; i < 40; i++){
            code = "D"+String.valueOf((i+1));
            prof_name = "profesor" + String.valueOf(i+1);
            aDocentes
                    .add(new Docente(i + 1, code, prof_name));
            aDocentesID
                    .add(code);
        }
    }

    public static void printSesionReg_TEST(){
        System.out.println("/**** LISTA DE SESIONES RERIGSTRADAS *****/");
        for (DiaPlanificado dp :
                aPlanifCalend) {
            if (dp.getSesion() != null)
                System.out.println("dp : " + dp);
        }
    }

}
