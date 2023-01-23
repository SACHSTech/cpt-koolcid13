package cpt;

import java.util.*;
import java.io.*;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class chartApp extends Application {


    private XYChart.Series <Integer, Double> fiveTo14;
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




    /**
     * Java main for when running without JavaFX launcher
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    @Override public void start(Stage primaryStage) throws Exception {

        datapointTable = new TableView<>();
        ageRangeCol = new TableColumn<>("Age Range");
        yearCol = new TableColumn<>("Year");
        rateCol = new TableColumn<>("Suicide Rate");

        fiveTo14 = new XYChart.Series<>();
        allAge = new XYChart.Series<>();
        ageStandardized = new XYChart.Series<>();
        fifteenTo49 = new XYChart.Series<>();
        fiftyTo69 = new XYChart.Series<>();
        seventyPlus = new XYChart.Series<>();


        datapointTable.getColumns().addAll(ageRangeCol, yearCol, rateCol);

    }

    private ObservableList<dataPack> importData() throws IOException {
        
        // variables for file reading
        
        ObservableList<dataPack> dataList;
        ObservableList<dataPack> dataListHelper;
        String str;
        String[] tempStr;

        // bufferedreader and dataLists prep
        BufferedReader file = new BufferedReader(new FileReader("src/cpt/suicide-death-rate-by-age.csv"));
        dataList = FXCollections.observableArrayList();
        dataListHelper = FXCollections.observableArrayList();


        // Read first line (junk and no data)
        str = file.readLine();

        // read the actual data
        while (str != null) {
            str = file.readLine();

            // Splits by commas and adds substrings to String array
            tempStr = str.split(",");

            // Create dataPack object and add to observable list
            dataListHelper.add(new dataPack(tempStr[1], Integer.parseInt(split[0]), Double.parseDouble(split[2])));
        }

        // Close the file
        file.close();

        // Return the list of DataPoints
        return dataList;

    }


}