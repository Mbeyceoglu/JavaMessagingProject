//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mustafa;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Jumpstart {
    public Jumpstart() {
    }

    public static void main(String[] args) {
        System.out.println("some where to start...");
        ServerProject server = new ServerProject();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter port number to start server:");

        try {
            int port = input.nextInt();
            server.starter(port);
        } catch (IOException a) {
            System.out.println("This port number is already taken or not usable... \nTry again...");
        } catch (InputMismatchException a) {
            System.out.println("please enter integer");
        }

    }
}
