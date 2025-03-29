package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {

    private static Stage primaryStage;

    public static void startMainScreen() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("/view/mainscreen/MainScreen.fxml")));
        primaryStage.setScene(new Scene(root));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        startMainScreen();
        primaryStage.setTitle("Work Task Tracker");
        primaryStage.show();
    }
}