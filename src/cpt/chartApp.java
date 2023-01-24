package cpt;

import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;


public class chartApp extends Application {


    /*private XYChart.Series <Integer, Double> fiveTo14;
    private XYChart.Series <Integer, Double> allAge;
    private XYChart.Series <Integer, Double> ageStandardized;
    private XYChart.Series <Integer, Double> fifteenTo49;
    private XYChart.Series <Integer, Double> fiftyTo69;
    private XYChart.Series <Integer, Double> seventyPlus;

    private HBox filtHBox;
    private HBox screenHBox;

    private TableView<dataPack> datapointTable;
    private TableColumn<dataPack, String> ageRangeCol;
    private TableColumn<dataPack, Integer> yearCol;
    private TableColumn<dataPack, Double> rateCol;


    private ScrollPane scrollPane;
    private GridPane grid;

*/
    private ArrayList<dataPack> dataPoints;    

    @Override public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Suicide death rate by age");
        //primaryStage.show();
        dataPoints = new ArrayList<dataPack>();
        readData(dataPoints);

    }

    public static void main(String[] args) {
        launch(args);
    }
        /*scrollPane = new ScrollPane();
        
        grid = new GridPane();
        scrollPane.setContent(grid);


        dataPoints = new ArrayList<dataPack>();
        // dataPointsHelper = new ArrayList<dataPackHelper>();
        readData(dataPoints);

        /*datapointTable = new TableView<>();
        ageRangeCol = new TableColumn<>("Age Range");
        yearCol = new TableColumn<>("Year");
        rateCol = new TableColumn<>("Suicide Rate");

        fiveTo14 = new XYChart.Series<>();
        allAge = new XYChart.Series<>();
        ageStandardized = new XYChart.Series<>();
        fifteenTo49 = new XYChart.Series<>();
        fiftyTo69 = new XYChart.Series<>();
        seventyPlus = new XYChart.Series<>();

    }
    

}

*/

    private static void readData(ArrayList<dataPack> dataPoints) throws IOException {

        // variables for file reading
        String str;
        String[] tempStr;

        // bufferedreader and dataLists prep
        BufferedReader file = new BufferedReader(new FileReader("src/cpt/data_shortened.csv"));

        // Read first line (junk and no data)
        str = file.readLine();

        // read the actual data
        while (str != null) {
            str = file.readLine();

            // Splits by commas and adds substrings to String array
            tempStr = str.split(",");

            // Create dataPack object
            dataPoints.add(new dataPack(tempStr[0], Double.parseDouble(tempStr[2]), Integer.parseInt(tempStr[1])));

            
        }

        file.close();

    }
}