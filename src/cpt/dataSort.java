package cpt;

import java.util.*;


public class dataSort {
    private ArrayList<dataPack> dataPoints;
    private int size;

    public dataSort(ArrayList<dataPack> dataPoints) {
        this.dataPoints = dataPoints;
        this.size = dataPoints.size();
    }


    public ArrayList<dataPack> getDataPoints() {
        return dataPoints;
    }

    public int getSize() {
        return size;
    }
}



