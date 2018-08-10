package com.mustafa;

public class MessageSlicer {
    String one;
    String two;
    String three;
    String four;

    public void msgSlicer(String message){

        String[] messageArray = message.split("_");
        int i = 0;
        try {
            one = messageArray[0];
            two = messageArray[1];
            three = messageArray[2];
            four = messageArray[3];
        }catch (ArrayIndexOutOfBoundsException ignored){

        }



    }


}
