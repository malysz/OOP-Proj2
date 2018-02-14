package agh.cs.proj2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Scanner;
import java.net.URL;

public class Query {
    private  String longtitude;
    private String latitude;
    private boolean history;
    private String sensorID;
    private final int option;
    private String url;
    private final String apikey;


    public Query(String longtitude, String latitude, boolean history, int option){
        this.history = history;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.option = option;
        if(System.getenv().get("API_KEY")!=null){
            this.apikey = System.getenv().get("API_KEY");
        }
        else {
            System.out.println("Brak klucza API w zmiennych srodowiskowych. \n Wprowadz klucz API:  ");
            Scanner input = new Scanner(System.in);
            this.apikey = input.next();
        }
    }

    public Query(String sensorID, boolean history, int option){
        this.sensorID = sensorID;
        this.history = history;
        this.option = option;
        if(System.getenv().get("API_KEY")!=null){
            this.apikey = System.getenv().get("API_KEY");
        }
        else {
            System.out.println("Brak klucza API w zmiennych srodowiskowych. \n Wprowadz klucz API:  ");
            Scanner input = new Scanner(System.in);
            this.apikey = input.next();
        }
    }

    public void setQuerryUrl(){
        String url;
        String baseUrl = "https://airapi.airly.eu/v1/";
        if(option==1){
            if(history==false)
                url = baseUrl.concat("mapPoint/measurements?latitude=").concat(latitude)
                        .concat("&longitude=").concat(longtitude)
                        .concat("&apikey=").concat(apikey);
            else
                url = baseUrl.concat("mapPoint/measurements?latitude=").concat(latitude)
                        .concat("&longitude=").concat(longtitude)
                        .concat("&apikey=").concat(apikey)
                        .concat("&history");
        }
        else{ //option=2
            if(history==false)
                url = baseUrl.concat("sensor/measurements?sensorId=").concat(sensorID)
                        .concat("&apikey=").concat(apikey);
            else
                url = baseUrl.concat("sensor/measurements?sensorId=").concat(sensorID)
                        .concat("&apikey=").concat(apikey)
                        .concat("&history");
        }

        this.url=url;
    }

    public String makeQuery() throws IOException, AirlyCustomException {

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        int respondCode = connection.getResponseCode();
        if(respondCode == 200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine())!=null){
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        else{
            String message="";
            if(respondCode == 403){
                message = "Error 403. Api-key not found";
            }
            else if(respondCode == 400){
                message = "Error 400. Input validation error";
            }
            throw new AirlyCustomException(message);
        }
    }
}
