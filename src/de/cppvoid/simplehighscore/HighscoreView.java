package de.cppvoid.simplehighscore;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Locale;

public class HighscoreView extends Application {

    private TableView scoresView = new TableView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Locale.setDefault(Locale.ENGLISH);

        primaryStage.setTitle("Highscore");
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);

        TableColumn nameColumn = new TableColumn("Name");
        TableColumn scoreColumn = new TableColumn("Score");
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);

        scoresView.getColumns().addAll(nameColumn, scoreColumn);
        scoresView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Button loadFromXmlFileButton = new Button("Load from xml file");

        loadFromXmlFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML File","*xml");
                fileChooser.getExtensionFilters().add(extensionFilter);

                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null) {
                    Highscore highscore = new Highscore();
                    try {
                        highscore.loadFromXMLFile(file.getAbsolutePath());

                        nameColumn.setCellValueFactory(new PropertyValueFactory<Score, String>("name"));
                        scoreColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("score"));

                        ObservableList<Score> scores = FXCollections.observableArrayList(highscore.getScores());
                        scoresView.setItems(scores);
                        scoresView.getSortOrder().add(scoreColumn);

                    } catch(Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error while loading xml file");
                        alert.setHeaderText(ex.getClass().toString());
                        alert.setContentText(ex.getMessage());

                        alert.showAndWait();
                    }
                }
            }
        });

        final BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setCenter(scoresView);
        pane.setBottom(loadFromXmlFileButton);

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
