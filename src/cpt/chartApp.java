package cpt;

import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;
import javafx.application.Application;
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
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


public class chartApp extends Application {


    /*private XYChart.Series <Integer, Double> fiveTo14;
    private XYChart.Series <Integer, Double> allAge;
    private XYChart.Series <Integer, Double> ageStandardized;
    private XYChart.Series <Integer, Double> fifteenTo49;
    private XYChart.Series <Integer, Double> fiftyTo69;
    private XYChart.Series <Integer, Double> seventyPlus;

    private HBox filtHBox;
    private HBox screenHBox;

    

*/

    private TableView<dataPack> datapointTable;
    private TableView<dataPack> datapointCell;
    private TableColumn<dataPack, String> ageRangeCol;
    private TableColumn<dataPack, Integer> yearCol;
    private TableColumn<dataPack, Double> rateCol;
    private TableColumn<dataPack, String> ageRangeCol2;
    private TableColumn<dataPack, Integer> yearCol2;
    private TableColumn<dataPack, Double> rateCol2;
    private dataSort niceData;
    private Stage popUp;

  

    @Override public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Suicide death rate by age");
        //primaryStage.show();

        ageRangeCol = new TableColumn<>("Age Range");
        ageRangeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        rateCol = new TableColumn<>("Suicide Rate");
        rateCol.setCellValueFactory(new PropertyValueFactory<>("suicideRate"));

        // create table and add cols
        datapointTable = new TableView<>();
        datapointTable.getColumns().addAll(ageRangeCol, yearCol, rateCol);

        
        niceData = new dataSort(readData());
        datapointTable.setItems(niceData.getDataPoints());

        datapointTable.setRowFactory( tv -> {
            TableRow<dataPack> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    
                    popUp = new Stage();
                    popUp.setTitle("Individual cell data");

                    ageRangeCol2 = new TableColumn<>("Age Range");
                    ageRangeCol2.setCellValueFactory(new PropertyValueFactory<>("age"));
                    yearCol2 = new TableColumn<>("Year");
                    yearCol2.setCellValueFactory(new PropertyValueFactory<>("year"));
                    rateCol2 = new TableColumn<>("Suicide Rate");
                    rateCol2.setCellValueFactory(new PropertyValueFactory<>("suicideRate"));

                    datapointCell = new TableView<>();
                    datapointCell.getColumns().addAll(ageRangeCol2, yearCol2, rateCol2);
                    datapointCell.getItems().add(row.getItem());

                    Scene cellScene = new Scene(datapointCell, 300, 50);
                    popUp.setScene(cellScene);
                    popUp.show();

                }
            });
            return row ;
        });

        for (int i = 0; i < niceData.getSize(); i ++) {
            dataPack data = niceData.getDataPoints().get(i);
            
        }

        // button to apply sort
        Button sortRate = new Button("Sort Suicide Rate");
        sortRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                niceData.sort("year", false);
            }
        });

        // create scene with table
        Scene scene = new Scene(datapointTable, 555, 555);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
/*       

        fiveTo14 = new XYChart.Series<>();
        allAge = new XYChart.Series<>();
        ageStandardized = new XYChart.Series<>();
        fifteenTo49 = new XYChart.Series<>();
        fiftyTo69 = new XYChart.Series<>();
        seventyPlus = new XYChart.Series<>();*/    


    private static ObservableList<dataPack> readData() throws IOException {

        // variables for file reading
        String str;
        String[] tempStr;

        // bufferedreader and dataLists prep
        BufferedReader file = new BufferedReader(new FileReader("src/cpt/data_shortened.csv"));

        ObservableList<dataPack> tempArrList;

        // Read first line (junk and no data)
        str = file.readLine();


        tempArrList = FXCollections.observableArrayList();
        // read the actual data
        while (str != null) {
            str = file.readLine();
            if (str == null || str.equals("")) {
                break;
            }

            // Splits by commas and adds substrings to String array
            tempStr = str.split(",");

            // Create dataPack object
            tempArrList.add(new dataPack(tempStr[0], Double.parseDouble(tempStr[2]), Integer.parseInt(tempStr[1])));

            
        }

        file.close();
        return tempArrList;

    }
}