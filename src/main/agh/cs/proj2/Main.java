package agh.cs.proj2;

import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        boolean history=false;
        Query query;
        try{
            String[] arguments = OptionsParser.parse(args);
            if(arguments[0]!=null){
                if(arguments[3]!=null) history = true;
                query = new Query(arguments[0], arguments[1], history, 1);
            }
            else {
                if(arguments[3]!=null) history = true;
                query = new Query(arguments[2], history, 2);
            }

            query.setQuerryUrl();
            String response = query.makeQuery();
            Printer printer = new Printer(response);
            printer.printData();
            printer.printASCIIart();
            if(history)
                printer.printHistory();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
