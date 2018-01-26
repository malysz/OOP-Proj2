package agh.cs.proj2;

import com.google.gson.Gson;

import java.util.List;

public class Printer {
    private String json;
    private Measurement measurement;

    public Printer(String json){
        this.json = json;
        this.measurement = new Gson().fromJson(json, Measurement.class);
    }

    public void printData(){
        System.out.println("Current measurements: ");
        System.out.println("air quality index (CAQI): " + (int)measurement.getCurrentMeasurements().getAirQualityIndex());
        System.out.println("pm1: " + (int)measurement.getCurrentMeasurements().getPm1() + "μg/m3   |" +
                "   pm2.5: " + (int)measurement.getCurrentMeasurements().getPm25() + "μg/m3   " + (int)measurement.getCurrentMeasurements().getPm25()*100/25 + "%   |" +
                "   pm10: " + (int)measurement.getCurrentMeasurements().getPm10() + "μg/m3   " + (int)measurement.getCurrentMeasurements().getPm10()*100/50 + "%");
        System.out.print("Temperature: "); System.out.format("%.1f", Double.valueOf(measurement.getCurrentMeasurements().getTemperature())); System.out.print("°C   |");
        System.out.print("   Pressure: "); System.out.format("%.0f", Double.valueOf(measurement.getCurrentMeasurements().getPressure()/100)); System.out.print("hPa   |");
        System.out.print("   Humidity: "); System.out.format("%.0f", Double.valueOf(measurement.getCurrentMeasurements().getHumidity())); System.out.print("%\n\n");

    }

    public void printHistory(){
        System.out.println("History: ");
        List<History> history = measurement.getHistory();
        for(History historyData : history){
            System.out.println(historyData.getFromDateTime());
            System.out.print("   air Quality index (CAQI): " + (int)historyData.getMeasurements().getAirQualityIndex() +
                    "pm1: " + (int)measurement.getCurrentMeasurements().getPm1() + "μg/m3   |" +
                    "   pm2.5: " + (int)measurement.getCurrentMeasurements().getPm25() + "μg/m3   |" +
                    "   pm10: " + (int)measurement.getCurrentMeasurements().getPm10() + "μg/m3   \n"
            );
            System.out.println(historyData.getTillDateTime()+"\n");
        }
    }

    public void printASCIIart(){
        int CAQI = setCaqiLvl(measurement.getCurrentMeasurements().getAirQualityIndex());
        String ANSI_RESET = "\u001B[0m";
        String ANSI_COLOR;
        String text;
        switch (CAQI){
            case 1:
                ANSI_COLOR = "\u001B[92m";
                text = "V E R Y    G O O D";
                break;
            case 2:
                ANSI_COLOR = "\u001B[32m";
                text = "G O O D ";
                break;
            case 3:
                ANSI_COLOR = "\u001B[93m";
                text = "A V E R A G E ";
                break;
            case 4:
                ANSI_COLOR = "\u001B[33m";
                text = "!! B A D !! ";
                break;
            case 5:
                ANSI_COLOR = "\u001B[31m";
                text = "!!! T E R R I B L E !!! ";
                break;
            default:
                text = "unknown ";
                ANSI_COLOR = ANSI_RESET;
        }
        text = spacer(text);
        System.out.print( ANSI_COLOR+
         "                          ....\n"+
         "                       .*++^^++*.\n"+
         "                     .+^^^++++^^^+.\n"+
         "                    *^^+..    ..+^^*\n"+
         "                   *^+*          .+^*    .*****..\n"+
         "                  .^+.            .+^* .+^^^^^^^+*.\n"+
         "                *^*                  ..            *^*\n"+
         "            .*^^^+.                                 .++++^^+*\n"+
         "         .^^+.                                              .+++.\n"+
         "       +^^^.                                                .^*\n"+
         "       +++                                                  .^+\n"+
         "        +^."); System.out.print(text); System.out.print("*^*\n"+  //48
         "       .^+.                                              .^+.\n"+
         "       *^^*.                                          .*^^.\n"+
         "       .+^++****************************************+^^+.\n"+
         "        .*+^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^+*.\n"+
        ANSI_RESET);
        System.out.println("");
    }

    private int setCaqiLvl(double airQuality){
        if(airQuality > 100)
            return 5;
        if(airQuality > 75)
            return 4;
        if(airQuality > 50)
            return 3;
        if(airQuality > 25)
            return 2;
        else
            return 1;
    }

    private String spacer(String str){
        String result=str;
        String whiteSpace = " ";
        int count = (48 - str.length()) /2;
        for(int i=0; i<count; i++){
            result = whiteSpace.concat(result).concat(whiteSpace);
        }
        return result;
    }
}
