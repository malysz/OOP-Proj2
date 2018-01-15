package agh.cs.proj2;

public class History {
    private String fromDateTime;
    private String tillDateTime;
    private CurrentMeasurements measurements;

    public Object getMeasurements(){
        return measurements;
    }

    public String getFromDateTime(){
        return fromDateTime;
    }

    public String getTillDateTime() {
        return tillDateTime;
    }
}
