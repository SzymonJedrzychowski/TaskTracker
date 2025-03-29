package objects;

public class Task {

    private String taskName;
    private String taskLink;
    private String developmentTaskLink;
    private TaskStatus taskStatus;

    public Task(String taskName, String taskLink, String developmentTaskLink, TaskStatus taskStatus) {
        this.taskName = taskName;
        this.taskLink = taskLink;
        this.developmentTaskLink = developmentTaskLink;
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskLink() {
        return taskLink;
    }

    public void setTaskLink(String taskLink) {
        this.taskLink = taskLink;
    }

    public String getDevelopmentTaskLink() {
        return developmentTaskLink;
    }

    public void setDevelopmentTaskLink(String developmentTaskLink) {
        this.developmentTaskLink = developmentTaskLink;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
