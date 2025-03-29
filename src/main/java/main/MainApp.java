package main;

import database.H2ConnectionManager;
import database.dao.TaskDAO;
import database.dao.TaskStatusDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.Task;
import objects.TaskStatus;

import java.util.Objects;

public class MainApp extends Application {

    private static Stage primaryStage;

    public static void startMainScreen() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("/view/mainscreen/MainScreen.fxml")));
        primaryStage.setScene(new Scene(root));
    }

    public static void main(String[] args) {
        initialiseData();
        launch(args);
        H2ConnectionManager.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        startMainScreen();
        primaryStage.setTitle("Work Task Tracker");
        primaryStage.show();
    }

    private static void initialiseData() {
        TaskStatusDAO taskStatusDAO = new TaskStatusDAO();
        TaskDAO taskDAO = new TaskDAO();

        taskDAO.dropTable();
        taskStatusDAO.dropTable();

        TaskStatus taskStatus1 = new TaskStatus(1, "Status 1");
        TaskStatus taskStatus2 = new TaskStatus(2, "Status 2");
        taskStatusDAO.createTable();
        taskStatusDAO.insert(taskStatus1);
        taskStatusDAO.insert(taskStatus2);

        taskDAO.createTable();
        taskDAO.insert(new Task(1, "Test Task 1", "https://test/ABC-1", "https://test/ABC-2", taskStatus1));
        taskDAO.insert(new Task(2, "Test Task 2", "https://test/ABC-3", "https://test/ABC-4", taskStatus2));
    }
}