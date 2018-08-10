package com.mustafa;
import java.io.*;
import  java.net.*;
import java.util.*;

public class ServerProject {
    static Vector<ClientHandler> ar = new Vector();
    static List clientlist = new ArrayList();
    static int index;



    public void starter(int port) throws IOException {
        new Scanner(System.in);
        ServerSocket server = new ServerSocket(port);

        while(true) {
            Socket socket = server.accept();
            System.out.println("New client request received : " + socket);
            DataInputStream datain = new DataInputStream(socket.getInputStream());
            DataOutputStream dataout = new DataOutputStream(socket.getOutputStream());
            System.out.println("Creating a new handler for this client...");
            if (clientlist.isEmpty()) {
                dataout.writeUTF("You can choose any name you want!");
            } else {
                dataout.writeUTF("List of names that already taken:");
                for (Object aClientlist : clientlist) {
                    dataout.writeUTF(String.valueOf(aClientlist));
                }
            }
            dataout.writeUTF("Enter Client name:");
            String name = datain.readUTF();
            ClientHandler mtch = new ClientHandler(socket, "client " + name, datain, dataout);
            Thread clientthread = new Thread(mtch);
            System.out.println("Adding this client to active client list");
            if (NameChecker.nameChecker(clientlist, mtch.name)) {
                clientlist.add(mtch.name);
                ar.add(mtch);
                System.out.println("client added to the list as: " + mtch.name);
                index = ar.indexOf(mtch);
                System.out.println(index);
                clientthread.start();
                System.out.println("thread started!");
            } else {
                mtch.dataout.writeUTF("this name is already taken!");
            }
        }
    }
}
