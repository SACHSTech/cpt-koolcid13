package cpt;

import java.io.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
* A program that visualizes rates of suicide worldwide over the past 10 years among different age groups using line chart and pie chart graphs
* @author: Avin A.
*
*/


public class chartApp extends Application {
    // declare table variables for main and individual data tables
    private TableView<dataPack> datapointTable;
    private TableView<dataPack> datapointCell;
    private TableColumn<dataPack, String> ageRangeCol;
    private TableColumn<dataPack, Integer> yearCol;
    private TableColumn<dataPack, Double> rateCol;
    private TableColumn<dataPack, String> ageRangeCol2;
    private TableColumn<dataPack, Integer> yearCol2;
    private TableColumn<dataPack, Double> rateCol2;

    // declare Hbox and Vbox
    private HBox screenHBox;
    private VBox databaseVBox;

    // declare line chart variables
    private LineChart<Integer, Double> lineChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    
    private XYChart.Series <Integer, Double> fiveTo14;
    private XYChart.Series <Integer, Double> allAge;
    private XYChart.Series <Integer, Double> fifteenTo49;
    private XYChart.Series <Integer, Double> fiftyTo69;
    private XYChart.Series <Integer, Double> seventyPlus;
    
    // declare dataset var, chart objects, tab vars
    private dataSort niceData;
    private Parent mainLineChart;
    private Parent mainPieChart;
    private TabPane tabPane;
    private Tab lineTab;
    private Tab pieTab;
    
    // stage and scene declaration
    private Stage popUpStage;
    private Scene mainScene;
    private Scene popUpScene;

    /**
     * start method
     * 
     * @param primaryStage the primary stage
     */

    @Override public void start(Stage primaryStage) throws Exception {

        // initialize hbox and vbox vars
        screenHBox = new HBox(1);
        databaseVBox = new VBox(20);

        // create table and columns
        datapointTable = new TableView<>();
        datapointCell = new TableView<>();
        ageRangeCol = new TableColumn<>("Age Range");
        yearCol = new TableColumn<>("Year");
        rateCol = new TableColumn<>("Suicide Rate");
        ageRangeCol2 = new TableColumn<>("Age Range");
        yearCol2 = new TableColumn<>("Year");
        rateCol2 = new TableColumn<>("Suicide Rate");
        
        // create line chart variable objects and set name
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

        // read data to dataset var
        niceData = new dataSort(readData());

        // call functions to show graphs
        mainLineChart = showLineGraph();
        mainPieChart = showPieChart();
        
        // tab initializations
        tabPane = new TabPane();
        lineTab = new Tab("Line chart", mainLineChart);
        pieTab = new Tab("Pie chart", mainPieChart);

        // stage and scene initializations
        popUpStage = new Stage();
        mainScene = new Scene(screenHBox);
        popUpScene = new Scene(datapointCell, 400, 80);

        // fill columns
        ageRangeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        rateCol.setCellValueFactory(new PropertyValueFactory<>("suicideRate"));
        ageRangeCol2.setCellValueFactory(new PropertyValueFactory<>("age"));
        yearCol2.setCellValueFactory(new PropertyValueFactory<>("year"));
        rateCol2.setCellValueFactory(new PropertyValueFactory<>("suicideRate"));

        // adjust width of tables
        ageRangeCol.prefWidthProperty().bind(datapointTable.widthProperty().multiply(0.4));
        yearCol.prefWidthProperty().bind(datapointTable.widthProperty().multiply(0.15));
        rateCol.prefWidthProperty().bind(datapointTable.widthProperty().multiply(0.2));
        ageRangeCol.setResizable(false);
        yearCol.setResizable(false);
        rateCol.setResizable(false);

        ageRangeCol2.prefWidthProperty().bind(datapointCell.widthProperty().multiply(0.3));
        yearCol2.prefWidthProperty().bind(datapointCell.widthProperty().multiply(0.3));
        rateCol2.prefWidthProperty().bind(datapointCell.widthProperty().multiply(0.3));
        ageRangeCol2.setResizable(false);
        yearCol2.setResizable(false);
        rateCol2.setResizable(false);
        
        // add data that was read to tables
        datapointTable.getColumns().addAll(ageRangeCol, yearCol, rateCol);
        datapointCell.getColumns().addAll(ageRangeCol2, yearCol2, rateCol2);
        // remove additional columns
        datapointTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        datapointCell.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // fill table with the data points
        datapointTable.setItems(niceData.getDataPoints());

        // add tabs to tabpane
        tabPane.getTabs().addAll(lineTab, pieTab);

        // add nodes
        databaseVBox.getChildren().addAll(datapointTable);
        screenHBox.getChildren().addAll(tabPane, databaseVBox);

        // stage config
        popUpStage.setTitle("Individual cell data");
        popUpStage.setScene(popUpScene);

        // double click detect for individual data cells popup
        datapointTable.setRowFactory( tv -> {
            TableRow<dataPack> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    
                    datapointCell.getItems().clear();
                    datapointCell.getItems().add(row.getItem());

                    popUpStage.show();

                }
            });
            return row ;
        });

        // primary stage config
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Suicide Rate over Time");
        primaryStage.show();


    }

    /**
     * The main method
     * 
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * reads csv file
     * 
     * @return data converted to observablelist form
     */
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

        // return statement
        return tempArrList;
    }

    /**
     * creates line graph for the data points
     * 
     * @return line graph (parent obj)
     */
    public Parent showLineGraph() {

        // var initialization
        xAxis = new NumberAxis("Year", 2010, 2019, 1);
        yAxis = new NumberAxis("Suicide Rate", 0, 7000, 500);

        lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Suicide rate by year");

        // add data to line chart
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

        // return statement
        return lineChart;
    }

    /**
     * creates pie graph for the data points
     * 
     * @return pie graph (parent obj)
     */
    public Parent showPieChart() {

        // var initialization
        PieChart pieChart;
        String ageRanges[] = {"5 - 14 years old", "15 - 49 years old", "50 - 69 years old", "70+ years old", "All ages"};
        double pieTotal[] = new double[5];
        ObservableList <PieChart.Data> pieChartData;

        // add data to pie chart
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

        // pie chart initialization
        pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Suicide Rate by age");

        // return statement
        return pieChart;

    }

}
