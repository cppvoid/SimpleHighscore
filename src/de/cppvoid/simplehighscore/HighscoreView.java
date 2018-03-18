package de.cppvoid.simplehighscore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class HighscoreView extends Application {

    private TableView scoresView = new TableView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Highscore");
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);

        scoresView.getColumns().addAll(new TableColumn("Name"), new TableColumn("Score"));
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
                    } catch(Exception ex) {

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
