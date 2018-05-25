package model;

import entity.*;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import utilities.AlertHelper;
import utilities.VariablesAndMethodsUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static utilities.VariablesAndMethodsUtils.uni;

/**
 * @apiNote Para usar los métodos de esta clase hay que conectarse, usar el método y desconectarse
 * @author mjl1010
 */
public class DatosModel {
    private static Socket socket;
    private static ObjectOutputStream dos;
    private static ObjectInputStream dis;
    private static Window window;
    private static Token token;

    // API

    /**
     * Conectar con el servidor para peticiones simples y públicas como verificación de datos primarios.
     * @param owner
     */
    public static void connect(Window owner) {
        connect(owner, token);
    }

    /**
     * Conectar con el servidor para qualquier tipo de cosa, pasándole el token de sesión, claro.
     * @apiNote Para obtener el Token, o bien lo 'heredas' de la clase anterior, o bien usas connect(windows) sin pasarle el token, lo cogerá de la sesión anterior, habiendo hecho un connect with token o comprobarCuenta.
     * @param owner
     * @param toke
     */
    public static void connect(Window owner, Token toke) {
        window = owner;
        token = toke;
        try {
            Properties p = new Properties();
            p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
            String server = p.getProperty("server");
            // HAY QUE PONER LA DIRECCIÓN IP DEL SERVIDOR
            socket = new Socket(server, 9090);
            //socket = new Socket("192.168.1.37", 9090);
            dos = new ObjectOutputStream(socket.getOutputStream());
            dis = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            if (owner != null) AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "¡No se ha podido conectar con el servidor!");
        }
    }

    /**
     * close connections with server
     */
    public static void closeConnection() {
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (Exception e) {}
    }

    /**
     * reiniciar conexión
     */
    public static void resetConnection() {
        closeConnection();
        connect(window, null);
    }

    // COMPROBACIONES

    /**
     * comprobarToken verifica si tiene una sesión válida para poder usar los métodos que requieran estar autentificado
     */
    public static boolean comprobarToken() {
        if (token == null) return false;
        Dato dato = new Dato("comprobarToken", token);
        try {
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            boolean result = (boolean) dato.getObject();
            closeConnection();
            connect(window);
            return result;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * comprobarCuenta
     * @param usuario Nombre del usuario de la cuenta
     * @param clave Clave del usuario de la cuenta
     * @return Token
     */
    public static Token comprobarCuenta(String usuario, String clave) {
        Map<String, String> datos = new HashMap<>();
        datos.put("usuario", usuario);
        datos.put("clave", clave);

        Dato dato = new Dato("comprobarCuenta", datos);
        try {
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            Token t = (Token) dato.getObject();
            if (t != null) token = t;
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    // GETTERS

    /**
     * comprobarToken verifica si tiene una sesión válida para poder usar los métodos que requieran estar autentificado
     */
    public static Token getToken(String sToken) {
        if (token != null) return token;
        Dato dato = new Dato("getToken", sToken);
        try {
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            Token t = (Token) dato.getObject();
            if (t != null) token = t;
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * obtener Masters de la Universidad
     */
    public static ArrayList<Master> getMasters(Universidad universidad) {
        if (!comprobarToken()) return null;

        Dato dato = new Dato("getMasters", universidad);
        try {
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            return (ArrayList<Master>) dato.getObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * obtenerCalendario
     */
    public static ArrayList<DiaPlanificado> getPlanificacionCalendarios(Usuario usuario, String cursoAcademico, Master master1, Master master2) {
        if (!comprobarToken()) return null;

        Map<String, Object> datos = new HashMap<>();
        datos.put("usuario", usuario);
        datos.put("cursoAcademico", cursoAcademico);
        datos.put("master1", master1);
        datos.put("master2", master2);

        Dato dato = new Dato("getPlanificacionCalendarios", datos);
        try {
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            return (ArrayList<DiaPlanificado>) dato.getObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * obtenerCalendario
     */
    public static ArrayList<String> getCursos(Universidad universidad) {
//        if (!comprobarToken()) return null;

        Dato dato = new Dato("getCursos", universidad);
        try {
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            return (ArrayList<String>) dato.getObject();
        } catch (Exception e) {
            return null;
        }
    }

    // SETTERS

    /**
     * Send Token to logout
     * @param token
     */
    public static boolean logoutToken(Token token) {
        Dato dato = new Dato("logoutToken", token);
        try {
            dos.writeObject(dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * sendInitialDateCalendar()
     * @param aCalBase, aPlanifDia
     */
    public static void sendInitialDateCalendar(ArrayList<CalendarioBase> aCalBase,
                                               ArrayList<DiaPlanificado> aPlanifDia) {
        HashMap<String, ArrayList> hmFirstDateCalendar = new HashMap<>();
        hmFirstDateCalendar.put("calendarioBase", aCalBase);
        hmFirstDateCalendar.put("planificacionDia", aPlanifDia);
        Dato dato = new Dato("firstListBaseCalendar", hmFirstDateCalendar);
        try {
            System.out.println("antes de enviar : " + aPlanifDia.get(0));
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            boolean result = (boolean) dato.getObject();
            closeConnection();
            connect(window);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    /**
     * send planificacion master list
     */
    public static void send_ListPlanif(ArrayList<DiaPlanificado> aDiaPlanif) {

        Dato dato = new Dato("updatePlanificacionCalendar", aDiaPlanif);
        try {
            dos.writeObject(dato);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean result = (boolean) dato.getObject();
        closeConnection();
        connect(window);

    }
}