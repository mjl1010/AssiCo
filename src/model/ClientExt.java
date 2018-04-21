package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by J Michael on 15/04/2018.
 */
public class ClientExt {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int id = 2;

    /**
     *
     */
    public void connect(){

        try {
            socket = new Socket("localhost", 9090);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            System.out.println(id + " envía saludo");
            dos.writeUTF("date");
            System.out.println("Servidor devuelve saludo: " + dis.readUTF());
            dis.close();
            dos.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
