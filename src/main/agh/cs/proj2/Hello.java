package agh.cs.proj2;

import com.google.gson.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

public class Hello {
    public static void main(String[] args) throws Exception{
        final String BASE_URL = "https://airapi.airly.eu/v1/";
        double latitude=50.06, longtitude=19.93;
        String finalURL = BASE_URL.concat("mapPoint/measurements?latitude=").concat(String.valueOf(latitude)).concat("&longitude=").concat(String.valueOf(longtitude)).concat(
                "&apikey=cc2f9c214494487d92fe3818a399adfb");
        System.out.println(finalURL);

        URL obj = new URL(finalURL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        int respondCode = connection.getResponseCode();
        System.out.println(respondCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine())!=null){
            response.append(inputLine);
        }
        in.close();
        System.out.print(response.toString());
        System.out.print("\n");
        deserializeUserSimple(response.toString());
    }

    /*private static void serializeUserSimple() {
        UserSimple user = new UserSimple("Norman", "norman@futurestud.io", 27, true);

        Gson gson = new Gson();
        String json = gson.toJson(user);
        System.out.println(json);
    }*/

    private static void deserializeUserSimple(String json){
        Measurements measurements = new Gson().fromJson(json, Measurements.class);
        CurrentMeasurements current = (CurrentMeasurements) measurements.getCurrentMeasurements();
        System.out.println(current.getPm25());
    }
}
