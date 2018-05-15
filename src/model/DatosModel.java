package model;

import entity.*;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import utilities.AlertHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            socket = new Socket("skimdoo.ddns.jazztel.es", 9090);
            //socket = new Socket("localhost", 9090);
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
            return (Token) dato.getObject();
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
     * send_firstListBaseCalendar()
     * **TODO add PlanificaciosList
     * @param data
     */
    public static void send_firstListBaseCalendar(ArrayList<CalendarioBase> data) {
        Dato dato = new Dato("firstListBaseCalendar", data);
        try {
            dos.writeObject(dato);

            dato = null;
            dato = (Dato) dis.readObject(); // while hasta que responda

            System.out.printf("Servidor devuelve : MessageCode =  %s , Dato = %s", dato.getMessageCode()
                    , dato.getObject());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}