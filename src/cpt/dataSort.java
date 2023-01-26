package cpt;

import java.util.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


public class dataSort {
    private ObservableList<dataPack> dataPoints;
    private int size;

    public dataSort(ObservableList<dataPack> dataPoints) {
        this.dataPoints = dataPoints;
        this.size = dataPoints.size();
    }


    public ObservableList<dataPack> getDataPoints() {
        return dataPoints;
    }

    public int getSize() {
        return size;
    }

    public void addData(dataPack data) {
        dataPoints.add(data);
    }

    // reverse for both ascendind and descending merge sort
    public void sort(String sortBy, boolean reverse) {
        mergeSort (new ArrayList<dataPack>(dataPoints), 0, size - 1, sortBy, reverse);
    }

    private void mergeSort(ArrayList<dataPack> preMerge, int from, int to, String sortBy, boolean reverse) {
        if (to - from >= 1) {
            int mid = (from + to) / 2;
            // Recursive call
            mergeSort(preMerge, from, mid, sortBy, reverse);
            mergeSort(preMerge, mid + 1, to, sortBy, reverse);

            merge(preMerge, from, mid, to, sortBy, reverse);
        }
    }

    private void merge (ArrayList<dataPack> preMerge, int from, int mid, int to, String sortBy, boolean reverse) {
        int i = from;
        int j = mid + 1;
        int k = from;


        while (i <= mid && j <= to) {
            boolean isTrue;
            if (sortBy.equals("age")) { // orts ascending and descending for age
                if (reverse) { 
                    isTrue = dataPoints.get(i).getAge().compareTo(dataPoints.get(j).getAge()) > 0;
                }
                else {
                    isTrue = dataPoints.get(i).getAge().compareTo(dataPoints.get(j).getAge()) < 0;
                }
            }
            else if (sortBy.equals("year")) {
                if (reverse) {
                    isTrue = dataPoints.get(i).getYear() > dataPoints.get(j).getYear();
                }
                else {
                    isTrue = dataPoints.get(i).getYear() < dataPoints.get(j).getYear();
                }
            }
            else {
                if (reverse) {
                    isTrue = dataPoints.get(i).getSuicideRate() > dataPoints.get(j).getSuicideRate();
                }
                else {
                    isTrue = dataPoints.get(i).getSuicideRate() < dataPoints.get(j).getSuicideRate();
                }
            }

            if (isTrue) {
                preMerge.set(k, dataPoints.get(i));
                ++i;
            }
            else {
                preMerge.set(k, dataPoints.get(j));
                j++;
            }
            k++;
        }

        while (i <= mid) {
            preMerge.set(k, dataPoints.get(i));
            i++;
            k++;
        }

        while (j <= to) {
            preMerge.set(k, dataPoints.get(j));
            j++;
            k++;
        }

        for (int ii = from; ii <= to; ii++) {
            dataPoints.set(ii, preMerge.get(ii));
        }
    }
}




