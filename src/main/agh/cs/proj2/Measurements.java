package agh.cs.proj2;

import java.util.List;

public class Measurements {
    private CurrentMeasurements currentMeasurements;
    private List<History> history = null;

    public Object getCurrentMeasurements(){
        return currentMeasurements;
    }

    public List<History> getHistory(){
        return history;
    }
}
