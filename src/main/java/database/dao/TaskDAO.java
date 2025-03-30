package database.dao;

import database.H2ConnectionManager;
import objects.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskDAO implements DAO<Task> {

    @Override
    public void dropTable() {
        String query = "DROP TABLE IF EXISTS task";
        try (Statement statement = H2ConnectionManager.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS task (id INT PRIMARY KEY, taskName VARCHAR(255), taskLink VARCHAR(255), developmentTaskLink VARCHAR(255), taskStatusID INT NOT NULL, FOREIGN KEY(taskStatusID) REFERENCES taskStatus(ID))";
        try (Statement statement = H2ConnectionManager.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Task task) {
        String query = "INSERT INTO task (id, taskName, taskLink, developmentTaskLink, taskStatusID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = H2ConnectionManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, task.getTaskID());
            preparedStatement.setString(2, task.getTaskName());
            preparedStatement.setString(3, task.getTaskLink());
            preparedStatement.setString(4, task.getDevelopmentTaskLink());
            preparedStatement.setInt(5, task.getTaskStatus().getTaskStatusID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Task> selectAll() {
        String query = "SELECT * FROM task";
        ArrayList<Task> result = new ArrayList<>();
        try (Statement statement = H2ConnectionManager.getConnection().createStatement()) {
            ResultSet row = statement.executeQuery(query);
            while (row.next()) {
                result.add(createFromRow(row));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Task selectByID(Integer taskID) {
        String query = "SELECT * FROM task WHERE id = ?";
        Task result = null;
        try (PreparedStatement preparedStatement = H2ConnectionManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, taskID);
            ResultSet row = preparedStatement.executeQuery();
            while (row.next()) {
                result = createFromRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Task createFromRow(ResultSet row) throws SQLException {
        return new Task(row.getInt(1),
                row.getString(2),
                row.getString(3),
                row.getString(4),
                new TaskStatusDAO().selectByID(row.getInt(5)));
    }

}
