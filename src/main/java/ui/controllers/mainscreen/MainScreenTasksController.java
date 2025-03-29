package ui.controllers.mainscreen;

import database.dao.TaskDAO;
import enums.HyperlinkCellFields;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import objects.Task;
import ui.helpers.HyperlinkCell;

public class MainScreenTasksController {
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();
    @FXML
    private TableView<Task> tasksTableView;
    @FXML
    private TableColumn<Task, String> taskLinkColumn;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, String> developmentTaskLinkColumn;
    @FXML
    private TableColumn<Task, String> taskStatusColumn;

    public void initialize() {
        taskLinkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTaskLink()));
        taskLinkColumn.setCellFactory(_ -> new HyperlinkCell(HyperlinkCellFields.TASK_LINK));

        taskNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTaskName()));

        developmentTaskLinkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDevelopmentTaskLink()));
        developmentTaskLinkColumn.setCellFactory(_ -> new HyperlinkCell(HyperlinkCellFields.DEVELOPMENT_TASK_LINK));

        taskStatusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTaskStatus().getTaskStatusName()));

        tasksTableView.setItems(taskList);

        taskList.addAll(new TaskDAO().selectAll());
    }
}
