package cpt;

import java.io.*;
import java.util.*;

public class userInteract {
    
    public static ArrayList<dataReader> dataList = new ArrayList<dataReader>();
    public static void main(String[] args) throws IOException {

        userInteract userInteract = new userInteract();
    }

    public userInteract() throws IOException{

        BufferedReader key = new BufferedReader(new FileReader("smoking-death-rate-1990-2017.csv"));

        String str = key.readLine();

        while (str != null) {
            String[] readStr = str.split(",");
            dataReader country = new dataReader(readStr[0], Integer.parseInt(readStr[2]), Double.parseDouble(readStr[3]), Double.parseDouble(readStr[5]), Integer.parseInt(readStr[6]));
            dataList.add(country);
            str = key.readLine();
        }
        key.close();
    }

}
