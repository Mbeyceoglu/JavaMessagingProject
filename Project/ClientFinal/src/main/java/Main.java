import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientFinal client= new ClientFinal();

        String host = client.getHost();
        int port = client.getPort();

        try {
            client.clientstarter(host,port);
        } catch (IOException e) {
            System.out.println("No such host or port exist");
            e.printStackTrace();
        }
    }
}
