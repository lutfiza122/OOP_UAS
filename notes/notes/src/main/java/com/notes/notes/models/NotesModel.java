package com.notes.notes.models;
import com.notes.notes.config.databaseConfig;
import com.notes.notes.entity.Notes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.SQLException;

public class NotesModel {
    protected Connection connection;

    public NotesModel() throws SQLException {
        this.connection = databaseConfig.getConnection();
    }
    public boolean insert(String note, String date, String status) throws SQLException {
        String SQL = "INSERT INTO note(note, date, status) VALUES (?, ?, ?)";
        var ps = this.connection.prepareStatement(SQL);
        ps.setString(1, note);
        ps.setString(2, date);
        ps.setString(3, status);
        ps.executeUpdate();
        return true;
    }
    public ObservableList<Notes> getAll(){
        ObservableList<Notes> notes = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM note";
        try {
            var ps = this.connection.prepareStatement(SQL);
            var rs = ps.executeQuery();
            while (rs.next()){
                notes.add(new Notes(
                        rs.getInt("id"),
                        rs.getString("note"),
                        rs.getString("date"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notes;
    }

    public boolean update(String note, String date, String status, int id) throws SQLException {
        String SQL = "UPDATE note SET note = ?, date = ?, status = ? WHERE id = ?";
        var ps = this.connection.prepareStatement(SQL);
        ps.setString(1, note);
        ps.setString(2, date);
        ps.setString(3, status);
        ps.setInt(4, id);
        ps.executeUpdate();
        return true;
    }
    public boolean delete(int id) throws SQLException {
        String SQL = "DELETE FROM note WHERE id = ?";
        var ps = this.connection.prepareStatement(SQL);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }
}
