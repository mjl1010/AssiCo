package model;

import entity.Master;
import entity.PlanificacionCalendarios;
import entity.Token;
import entity.Usuario;
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

    /**
     * connect to server
     */
    public static void connect(Window owner) {
        window = owner;
        try {
            //socket = new Socket("skimdoo.ddns.jazztel.es", 9090);
            socket = new Socket("192.168.5.229", 9090);
            dos = new ObjectOutputStream(socket.getOutputStream());
            dis = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            if (owner != null) AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error - AssiCo", "No s'ha pogut establir connexió amb el servidor!");
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
        } catch (IOException e) {
            //TODO Avisar que no se ha podido cerrar la conexión?
        }
    }

    /**
     * reiniciar conexión
     */
    public static void resetConnection() {
        closeConnection();
        connect(window);
    }

    /**
     * comprobarCuenta
     * @param data
     */
    public static Token comprobarCuenta(Map<String, String> data) {
        Dato dato = new Dato("comprobarCuenta", data);
        try {
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            return (Token) dato.getObject();
        } catch (Exception e) {
            //TODO Quitar ese mensaje cuando se ponga a producción
            e.printStackTrace();
            return null;
        }
    }

    /**
     * obtenerCalendario
     */
    public static ArrayList<PlanificacionCalendarios> getPlanificacionCalendarios(Usuario usuario, String cursoAcademico, Master master1, Master master2) {
        Map<String, Object> datos = new HashMap<>();
        datos.put("usuario", usuario);
        datos.put("cursoAcademico", cursoAcademico);
        datos.put("master1", master1);
        datos.put("master2", master2);

        Dato dato = new Dato("getPlanificacionCalendarios", datos);
        try {
            dos.writeObject(dato);
            dato = (Dato) dis.readObject();
            return (ArrayList<PlanificacionCalendarios>) dato.getObject();
        } catch (Exception e) {
            //TODO Quitar ese mensaje cuando se ponga a producción
            e.printStackTrace();
            System.out.println(e.getCause() + e.getMessage());
            AlertHelper.showAlert(Alert.AlertType.WARNING, window, "Error - AssiCo", "No s'ha pogut obtenir les dades de la planificació de calendaris.");
            return null;
        }
    }
}