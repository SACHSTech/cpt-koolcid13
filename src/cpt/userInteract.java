package cpt;

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

public class userInteract extends Application {
    
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
