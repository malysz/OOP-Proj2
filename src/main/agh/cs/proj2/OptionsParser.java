package agh.cs.proj2;

public class OptionsParser {
    public static String[] parse(String[] args) throws IllegalAccessException {
        String[] results = new String[4];
        if((args.length==4 || args.length==5) && args[0].equals("--longtitude") && args[2].equals("--latitude")){
            try{
                StringToDouble(args[1]);
                results[0] = args[1];
                StringToDouble(args[3]);
                results[1] = args[3];
            } catch (NumberFormatException ex){
                System.out.println("Niewlasciwy argument "+ex.getMessage());
            }
            if(args.length == 5 && args[4].equals("--history"))
                results[3] = "true";
            else
                results[3] = null;

            return results;
        }
        else if((args.length==2 || args.length==3) && args[0].equals("--sensorID")){
            String sensorID;
            try{
                StringToInt(args[1]);
                results[2] = args[1];
            } catch (NumberFormatException ex) {
                System.out.println("Niewlasciwy argument "+ ex.getMessage());
            }
            if(args.length == 3 && args[2].equals("--history"))
                results[3] = "true";
            else
                results[3] = null;
            return results;
        }
        else throw new IllegalAccessException("Niewlasciwe argumenty ");
    }

    //functions wrote to check if the given numeric arguments(values of latitude/longtitude/sensorID) are numbers
    //and if they are not to throw an exception
    private static void StringToDouble(String str) throws NumberFormatException {
        Double.valueOf(str);
    }

    private static void StringToInt(String str) throws NumberFormatException {
        Integer.valueOf(str);
    }
}
