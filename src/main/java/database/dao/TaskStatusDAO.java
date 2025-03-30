package database.dao;

import database.H2ConnectionManager;
import objects.TaskStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskStatusDAO implements DAO<TaskStatus> {

    @Override
    public void dropTable() {
        String query = "DROP TABLE IF EXISTS taskStatus";
        try (Statement statement = H2ConnectionManager.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS taskStatus (id INT PRIMARY KEY, statusName VARCHAR(255))";
        try (Statement statement = H2ConnectionManager.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(TaskStatus taskStatus) {
        String query = "INSERT INTO taskStatus (id, statusName) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = H2ConnectionManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, taskStatus.getTaskStatusID());
            preparedStatement.setString(2, taskStatus.getTaskStatusName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<TaskStatus> selectAll() {
        String query = "SELECT * FROM task";
        ArrayList<TaskStatus> result = new ArrayList<>();
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
    public TaskStatus selectByID(Integer taskStatusID) {
        String query = "SELECT * FROM taskStatus WHERE id = ?";
        TaskStatus result = null;
        try (PreparedStatement preparedStatement = H2ConnectionManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, taskStatusID);
            ResultSet row = preparedStatement.executeQuery();
            while (row.next()) {
                result = createFromRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private TaskStatus createFromRow(ResultSet row) throws SQLException {
        return new TaskStatus(row.getInt(1), row.getString(2));
    }
}
