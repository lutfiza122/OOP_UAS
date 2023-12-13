package com.notes.notes.controllers;

import com.notes.notes.entity.Notes;
import com.notes.notes.models.NotesModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class NotesController implements Initializable {
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnClear;
    private NotesModel notesModel;
    @FXML
    private TableView<Notes> tb_notes;

    @FXML
    private TableColumn<Notes, Integer> col_id;
    @FXML
    private TableColumn<Notes, String> col_notes;
    @FXML
    private TableColumn<Notes, String> col_date;

    @FXML
    private TableColumn<Notes, String> col_status;

    @FXML
    public TextArea tf_note;

    @FXML
    public DatePicker tf_date;

    @FXML
    public ComboBox<String> cb_status;

    @FXML
    private Label lbl_id;
    @FXML
    public void clearAction() {
        lbl_id.setText("");
        tf_note.setText("");
        tf_date.setValue(LocalDate.now());
        cb_status.setValue("");
        btnCRUD(true);
    }
    @FXML
    void editAction() throws SQLException {
        notesModel.update(tf_note.getText(), tf_date.getValue().toString(), cb_status.getValue(), Integer.parseInt(lbl_id.getText()));
        clearAction();
        fetchNotes();
    }
    @FXML
    public void simpanAction() {
        LocalDate date = tf_date.getValue();
        try {
            notesModel.insert(tf_note.getText(), date.toString(), cb_status.getValue());
            clearAction();
            fetchNotes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void deleteAction() throws SQLException {
        notesModel.delete(Integer.parseInt(lbl_id.getText()));
        clearAction();
        fetchNotes();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            notesModel = new NotesModel();
            fetchNotes();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnCRUD(true);
        cb_status.setPromptText("Pilih Status");
        cb_status.getItems().addAll("Belum Selesai", "Selesai");
        tf_date.setValue(LocalDate.now());

        tb_notes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tf_note.setText(newSelection.getNote());
                tf_date.setValue(LocalDate.parse(newSelection.getDate()));
                cb_status.setValue(newSelection.getStatus());
                lbl_id.setText(String.valueOf(newSelection.getId()));
                btnCRUD(false);
            }
        });
    }
    private void btnCRUD(boolean status){
        btnEdit.setDisable(status);
        btnDelete.setDisable(status);
        btnSave.setDisable(!status);
    }
    private void fetchNotes(){
        ObservableList<Notes> notes= notesModel.getAll();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_notes.setCellValueFactory(new PropertyValueFactory<>("note"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tb_notes.setItems(null);
        tb_notes.setItems(notes);
    }
}