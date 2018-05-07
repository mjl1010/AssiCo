package model;

import entity.CalendarioBase;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 *  Classe Deprecated
 *  @deprecated Ahora se usa DatosModel
 */
public class ClientExt {

    private static Socket socket;
    private static ObjectOutputStream dos;
    private static ObjectInputStream dis;

    private static Dato dato;

    /**
     * connect to server
     */
    public static void  connect(){
        try {
            socket = new Socket("192.168.1.38", 9090);
            dos = new ObjectOutputStream(socket.getOutputStream());
            dis = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * send_firstListBaseCalendar()
     * @param data
     */
    public static void send_firstListBaseCalendar(ArrayList<CalendarioBase> data) {
        dato = new Dato("firstListBaseCalendar", data);
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

    /**
     * close connections
     * with server
     */
    public static void closeConnection(){

        try {

            dis.close();
            dos.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
