
package com.mustafa;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements  Runnable{


    String name;
    private DataInputStream datain;
    DataOutputStream dataout;
    private Socket socket;
    private boolean isloggedin;


    // constructor
    ClientHandler(Socket socket, String name,
                  DataInputStream datain, DataOutputStream dataout) {
        this.datain = datain;
        this.dataout = dataout;
        this.name = name;
        this.socket = socket;
        this.isloggedin = true;
    }


    @Override
    public void run() {

        String received;
        while (true) {
            try {
                received = datain.readUTF();


                System.out.println(received);

                if (received.equals("quit")) {
                    ServerProject.ar.remove(ServerProject.clientlist.indexOf(this.name));
                    ServerProject.clientlist.remove(this.name);
                    this.isloggedin = false;
                    this.socket.close();
                    break;
                }
                else {


                    MessageSlicer msl = new MessageSlicer();
                    msl.msgSlicer(received);


                    try {
                        MessageParser msg = new MessageParser(MessageParser.opr = Opr.valueOf(msl.one));
                        String a = (msg.operation(msl.one, msl.two, msl.three, msl.four,this.name));
                        String[] aarray = a.split("_");

                        for (com.mustafa.ClientHandler mc : ServerProject.ar) {
                            if (aarray[0].equals("All") && mc.isloggedin){
                                mc.dataout.writeUTF(this.name + ": " + aarray[1]);
                            }
                            if (mc.name.equals(aarray[0]) && mc.isloggedin) {
                                    mc.dataout.writeUTF(this.name + ": " + aarray[1]);
                            }
                        }

                        if (received.equals("SendHelp")){
                            dataout.writeUTF(a);
                            for (int i =0 ;i < ServerProject.ar.size();i++){
                                dataout.writeUTF(String.valueOf(ServerProject.ar.get(i).name));
                                dataout.flush();
                            }
                        }

                        if (received.equals("CalcHelp")){
                            dataout.writeUTF(a);
                        }
                        if (a.startsWith("result")){
                            dataout.writeUTF(a.substring(6));
                        }
                        if (a.startsWith("err")){
                         dataout.writeUTF(a.substring(3));
                        }
                    }
                    catch (StringIndexOutOfBoundsException s){
                        dataout.writeUTF("new error");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (IllegalArgumentException a){
                        dataout.writeUTF("no proper enum!");
                    }
                    catch (NullPointerException ignored){
                    }
                }
            } catch (EOFException a){
                System.out.println("client closed unexpected!");
                ServerProject.ar.remove(ServerProject.clientlist.indexOf(this.name));
                ServerProject.clientlist.remove(this.name);
                this.isloggedin = false;
                try {
                    this.socket.close();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            // closing resources
            this.datain.close();
            this.dataout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/*


git hub
github
.gitgignore

target not included

code convention

hash map

hearth beat


switch case default
*/