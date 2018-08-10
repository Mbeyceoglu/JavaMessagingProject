package com.mustafa;
enum Opr {
    Send, Calc, SendHelp, CalcHelp}
public class MessageParser {
    static Opr opr;
    public MessageParser(Opr opr) {
        MessageParser.opr = opr;
    }

    public String operation(String one, String two, String three, String four,String name) {
        switch (opr) {
            case Send:
                if (two.isEmpty() || three.isEmpty()){
                    return("errUse the SendHelp command to learn how to use it!");
                }
                else{
                return two+"_"+three;}

            case Calc:
                if (two.isEmpty()|| three.isEmpty() || four.isEmpty() ){
                    return("errUse the CalcHelp command to learn how to use it!");
                }
                else{
                System.out.println("calculate operation");
                double num1 = Double.parseDouble(three);
                double num2 = Double.parseDouble(four);
                double result;
                if(two.equals("Mul")){
                    result = num1 *num2;
                    return "result"+num1+" * "+num2+"= "+result; }
                else if (two.equals("Div")){
                    result = num1 /num2;
                    return "result"+num1+" / "+num2+"= "+result;}
                else if (two.equals("Add")){
                    result = num1 +num2;
                    return "result"+num1+" + "+num2+"= "+result;}
                else if (two.equals("Sub")){
                    result = num1 -num2;
                    return "result"+num1+" - "+num2+"= "+result;}
                else {
                    return "No command matched!!\nUse Div Mul Add or Sub";
                }
                }
            case SendHelp:
                String usage = ("usage = 'Send'_'client name'_'message String' \n" +
                        "Available Client List:");
                return usage;
            case CalcHelp:
               usage = ("usage = 'Calc'_'opreation'_'number1'_'number2' \n" +
                        "Opreation list:\n" +
                        "'Mul': Multiply\n" +
                        "'Div': Divide\n" +
                        "'Add': Addition\n" +
                        "'Sub': Substraction.");
               return usage;
                default:
                    return "errno proper enumerator found!";
        }
    }


}

