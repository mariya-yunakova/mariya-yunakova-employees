package pair.task.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pair.task.module.Pair;
import pair.task.module.PairProcessor;

import java.io.File;
import java.util.Map;

public class PairApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Long lasting Pair of Employees");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        ObservableList<PairProperties> tableData = FXCollections.observableArrayList();
        Button button = createButton(stage, fileChooser, tableData);
        TableView table = createTable(tableData);

        VBox vBox = new VBox(button, table);
        Scene scene = new Scene(vBox, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private Button createButton(Stage stage, FileChooser fileChooser,
                                ObservableList<PairProperties> tableData) {
        Button button = new Button("Select CSV File");
        button.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            tableData.clear();

            PairProcessor processor = new PairProcessor();
            processor.processPairs(selectedFile);
            Pair topPair = processor.getTopPair();
            if(topPair != null) {
                for (Map.Entry<String, Integer> item : topPair.getDurationsPerProject().entrySet()) {
                    tableData.add(new PairProperties(topPair.getFirst(), topPair.getSecond(),
                            item.getKey(), item.getValue()));
                }
            }
        });
        return button;
    }

    private TableView createTable(ObservableList<PairProperties> tableData) {
        TableView table = new TableView();
        table.setEditable(false);

        TableColumn firstEmployee = new TableColumn("Employee ID #1");
        firstEmployee.setCellValueFactory(
                new PropertyValueFactory<PairProperties, String>("firstEmployee"));

        TableColumn secondEmployee = new TableColumn("Employee ID #2");
        secondEmployee.setCellValueFactory(
                new PropertyValueFactory<PairProperties, String>("secondEmployee"));

        TableColumn projectId = new TableColumn("Project ID");
        projectId.setCellValueFactory(
                new PropertyValueFactory<PairProperties, String>("projectId"));

        TableColumn days = new TableColumn("Days worked");
        days.setCellValueFactory(
                new PropertyValueFactory<PairProperties, String>("days"));

        table.getColumns().addAll(firstEmployee, secondEmployee, projectId, days);
        table.setItems(tableData);
        return table;
    }

}
