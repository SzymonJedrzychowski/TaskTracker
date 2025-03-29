package ui.controllers.mainscreen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.util.ArrayList;

public class MainScreenTimerController {
    private final ArrayList<Integer> pauseTimes = new ArrayList<>();
    private final ArrayList<Integer> resumeTimes = new ArrayList<>();
    @FXML
    private Label timerLabel;
    @FXML
    private Button startWorkButton;
    @FXML
    private Button stopWorkButton;
    @FXML
    private Button pauseWorkButton;
    @FXML
    private Button resumeWorkButton;
    private Timeline timeline;
    private Integer startWorkDate;
    private Integer stopWorkDate;

    @FXML
    private void startWorkAction() {
        timerLabel.setText("0:00:00");

        startWorkDate = LocalTime.now().toSecondOfDay();
        pauseTimes.clear();
        resumeTimes.clear();
        stopWorkDate = null;
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), _ -> timerLabel.setText(getWorkTime())));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        startWorkButton.setDisable(true);
        stopWorkButton.setDisable(false);
        pauseWorkButton.setDisable(false);
        resumeWorkButton.setDisable(true);
    }

    @FXML
    private void stopWorkAction() {
        stopWorkDate = LocalTime.now().toSecondOfDay();
        timeline.stop();
        startWorkButton.setDisable(false);
        stopWorkButton.setDisable(true);
        pauseWorkButton.setDisable(true);
        resumeWorkButton.setDisable(true);
    }

    @FXML
    void pauseAction() {
        timeline.stop();
        pauseTimes.add(LocalTime.now().toSecondOfDay());
        resumeWorkButton.setDisable(false);
        pauseWorkButton.setDisable(true);
    }

    @FXML
    private void resumeAction() {
        timeline.play();
        resumeTimes.add(LocalTime.now().toSecondOfDay());
        resumeWorkButton.setDisable(true);
        pauseWorkButton.setDisable(false);
    }

    private String getWorkTime() {
        int currentTime = LocalTime.now().toSecondOfDay();
        int totalTime = (stopWorkDate == null ? currentTime : stopWorkDate) - startWorkDate;
        int timeToRemove = 0;
        for (int i = 0; i < resumeTimes.size(); i++) {
            timeToRemove += (resumeTimes.get(i) - pauseTimes.get(i));
        }

        if (pauseTimes.size() > resumeTimes.size()) {
            timeToRemove += currentTime - pauseTimes.getLast();
        }

        totalTime = totalTime - timeToRemove;

        int hours = totalTime / 3600;
        int minutes = totalTime / 60;
        int seconds = totalTime % 60;
        return hours + ":" + (minutes > 9 ? minutes : "0" + minutes) + ":" + (seconds > 9 ? seconds : "0" + seconds);
    }
}
