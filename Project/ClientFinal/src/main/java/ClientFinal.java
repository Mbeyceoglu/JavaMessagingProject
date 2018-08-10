// Java implementation for multithreaded chat client
// Save file as Client.java

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientFinal extends Thread {

    public int getPort(){
        int port;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter port number: ");
        port = input.nextInt();
        return port;
    }
    public String getHost(){
        String host;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter host address:");
        host = input.next();
        return host;
    }


    public void clientstarter(String host,int port) throws IOException {
        final Scanner input = new Scanner(System.in);

        InetAddress ip = InetAddress.getByName(host);

        final Socket socket = new Socket(ip, port);

        final DataInputStream datain = new DataInputStream(socket.getInputStream());
        final DataOutputStream dataout = new DataOutputStream(socket.getOutputStream());

        // sendMessage thread
        Thread sendMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {
                while (true) {

                    // read the message to deliver.
                    String msg = input.nextLine();

                    try {
                        // write on the output stream
                        dataout.writeUTF(msg);
                    } catch (SocketException s ){
                    break;
                } catch (EOFException e){
                    break;
                } catch (IOException e) {
                    break;
                }
                }
            }
        });
        // readMessage thread
        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {

                while (true) {
                    try {
                        // read the message sent to this client
                        String msg = datain.readUTF();
                        System.out.println(msg);
                    } catch (SocketException s ){
                     break;
                    } catch (EOFException e){
                     break;
                    } catch (IOException e) {
                        break;
                    }

                }
            }
        });

        sendMessage.start();
        readMessage.start();

    }
}
