package cpt;

import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class ScatterChartApp extends Application {

    private ScatterChart chart;
    private NumberAxis xAxisDeathRate1990;
    private NumberAxis yAxisDeathRate2019;

    public Parent createContent() {
        xAxisDeathRate1990 = new NumberAxis("X-Axis", 0d, 8.0d, 1.0d);
        yAxisDeathRate2019 = new NumberAxis("Y-Axis", 0.0d, 5.0d, 1.0d);
        final Series <Number, Number> series = new Series<>();
        series.setName("Series 1");
        series.getData().addAll(new Data(0.2, 3.5),
                                new Data(0.7, 4.6),
                                new Data(1.8, 1.7),
                                new Data(2.1, 2.8),
                                new Data(4.0, 2.2),
                                new Data(4.1, 2.6),
                                new Data(4.5, 2.0),
                                new Data(6.0, 3.0),
                                new Data(7.0, 2.0),
                                new Data(7.8, 4.0));
        chart = new ScatterChart(xAxis, yAxis);
        chart.getData().add(series);
        return chart;
    }


    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    /**
     * Java main for when running without JavaFX launcher
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}