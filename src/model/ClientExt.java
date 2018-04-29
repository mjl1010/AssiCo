package model;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 */
public class ClientExt {

    private static Socket socket;
    private static ObjectOutputStream dos;
    private static ObjectInputStream dis;

    private static Message message;

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
        message = new Message("firstListBaseCalendar", data);
        try {
            dos.writeObject(message);

            message = null;
            message = (Message) dis.readObject(); // while hasta que responda

            System.out.printf("Servidor devuelve : MessageCode =  %s , Message = %s", message.getMessageCode()
                    , message.getObject());

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
