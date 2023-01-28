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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;



public class chartApp extends Application {

    private TableView<dataPack> datapointTable;
    private TableView<dataPack> datapointCell;
    private TableColumn<dataPack, String> ageRangeCol;
    private TableColumn<dataPack, Integer> yearCol;
    private TableColumn<dataPack, Double> rateCol;
    private TableColumn<dataPack, String> ageRangeCol2;
    private TableColumn<dataPack, Integer> yearCol2;
    private TableColumn<dataPack, Double> rateCol2;

    private LineChart<Integer, Double> lineChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private ObservableList<XYChart.Series<Integer, Double>> lineChartData;
    private PieChart pieChart;


    private XYChart.Series <Integer, Double> fiveTo14;
    private XYChart.Series <Integer, Double> allAge;
    private XYChart.Series <Integer, Double> fifteenTo49;
    private XYChart.Series <Integer, Double> fiftyTo69;
    private XYChart.Series <Integer, Double> seventyPlus;
    
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
        datapointTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ageRangeCol.prefWidthProperty().bind(datapointTable.widthProperty().multiply(0.3));
        ageRangeCol.setResizable(false);
        yearCol.prefWidthProperty().bind(datapointTable.widthProperty().multiply(0.3));
        yearCol.setResizable(false);
        rateCol.prefWidthProperty().bind(datapointTable.widthProperty().multiply(0.3));
        rateCol.setResizable(false);

        
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
                    datapointCell.getItems().clear();
                    datapointCell.getItems().add(row.getItem());
                    datapointCell.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                    Scene cellScene = new Scene(datapointCell, 300, 70);
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

        Scene lineScene = new Scene(showLineGraph());
        Stage lineStage = new Stage();
        lineStage.setScene(lineScene);
        lineStage.show();

        Scene pieScene = new Scene(showPieChart());
        Stage pieStage = new Stage();
        pieStage.setScene(pieScene);
        pieStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    } 


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

    public Parent showLineGraph() {
        
        xAxis = new NumberAxis("Year", 2010, 2019, 1);
        yAxis = new NumberAxis("Suicide Rate", 0, 7000, 500);
        lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Suicide rate by year");

        fiveTo14 = new XYChart.Series<>();
        fiveTo14.setName("5 - 14 years old");
        fifteenTo49 = new XYChart.Series<>();
        fifteenTo49.setName("50 - 69 years old");
        fiftyTo69 = new XYChart.Series<>();
        fiftyTo69.setName("70+ years old");
        seventyPlus = new XYChart.Series<>();
        seventyPlus.setName("All ages");
        allAge = new XYChart.Series<>();
        allAge.setName("15 - 49 years old");

        for (dataPack datapoint: niceData.getDataPoints()) {
            switch (datapoint.getAge()) {
                case "5 - 14 years old" :
                    fiveTo14.getData().add(new XYChart.Data<Integer, Double>(datapoint.getYear(), datapoint.getSuicideRate()));
                    break;
                case "15 - 49 years old" :
                    fifteenTo49.getData().add(new XYChart.Data<Integer, Double>(datapoint.getYear(), datapoint.getSuicideRate()));
                    break;
                case "50 - 69 years old" :
                    fiftyTo69.getData().add(new XYChart.Data<Integer, Double>(datapoint.getYear(), datapoint.getSuicideRate()));
                    break;
                case "70+ years old" :
                    seventyPlus.getData().add(new XYChart.Data<Integer, Double>(datapoint.getYear(), datapoint.getSuicideRate()));
                    break;
                default:
                    allAge.getData().add(new XYChart.Data<Integer, Double>(datapoint.getYear(), datapoint.getSuicideRate()));
                    break;
            }
        }

        lineChart.getData().addAll(fiveTo14, fifteenTo49, fiftyTo69, seventyPlus, allAge);

        return lineChart;


    }

    public Parent showPieChart() {
        PieChart finalPieChart;
        String ageRanges[] = {"5 - 14 years old", "15 - 49 years old", "50 - 69 years old", "70+ years old", "All ages"};
        double pieTotal[];
        ObservableList <PieChart.Data> pieChartData;

        
        pieTotal = new double[5];

        for (dataPack data: niceData.getDataPoints()) {
            for (int i = 0; i < 5; i++) {
                if (data.getAge().equals(ageRanges[i])) {
                    pieTotal[i] += data.getSuicideRate();
                }
            }
        }

        pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("5 - 14 years old", pieTotal[0]),
        new PieChart.Data("15 - 49 years old", pieTotal[1]),
        new PieChart.Data("50 - 69 years old", pieTotal[2]),
        new PieChart.Data("70+ years old", pieTotal[3]),
        new PieChart.Data("All ages", pieTotal[4])
        );

        
        finalPieChart = new PieChart(pieChartData);
        finalPieChart.setTitle("Suicide Rate by age");

        return finalPieChart;

    }

}
