package com.notes.notes.entity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Notes {
    public IntegerProperty id;
    private StringProperty note;
    private StringProperty date;
    private StringProperty status;

    public Notes(Integer id, String note, String date, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.note = new SimpleStringProperty(note);
        this.date = new SimpleStringProperty(date);
        this.status = new SimpleStringProperty(status);
    }
    public Notes(String note, String date, String status) {
        this.note = new SimpleStringProperty(note);
        this.date = new SimpleStringProperty(date);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
