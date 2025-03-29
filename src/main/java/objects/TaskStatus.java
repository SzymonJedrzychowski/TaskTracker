package objects;

import java.util.ArrayList;

public class TaskStatus {

    private Integer taskStatusID;
    private String taskStatusName;
    private ArrayList<TaskStatus> parentStatusList;
    private ArrayList<TaskStatus> childStatusList;

    public TaskStatus(Integer taskStatusID, String taskStatusName) {
        this.taskStatusID = taskStatusID;
        this.taskStatusName = taskStatusName;
        this.parentStatusList = new ArrayList<>();
        this.childStatusList = new ArrayList<>();
    }

    public Integer getTaskStatusID() {
        return taskStatusID;
    }

    public void setTaskStatusID(Integer taskStatusID) {
        this.taskStatusID = taskStatusID;
    }

    public String getTaskStatusName() {
        return taskStatusName;
    }

    public void setTaskStatusName(String taskStatusName) {
        this.taskStatusName = taskStatusName;
    }

    public ArrayList<TaskStatus> getParentStatusList() {
        return parentStatusList;
    }

    public void setParentStatusList(ArrayList<TaskStatus> parentStatusList) {
        this.parentStatusList = parentStatusList;
    }

    public ArrayList<TaskStatus> getChildStatusList() {
        return childStatusList;
    }

    public void setChildStatusList(ArrayList<TaskStatus> childStatusList) {
        this.childStatusList = childStatusList;
    }
}
