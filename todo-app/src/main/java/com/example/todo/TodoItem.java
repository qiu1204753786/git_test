package com.example.todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoItem {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final int id;
    private final String title;
    private final LocalDateTime createdAt;
    private boolean completed;

    public TodoItem(int id, String title) {
        this.id = id;
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = completed ? "[x]" : "[ ]";
        return String.format("%s #%d %s  (%s)",
                status, id, title, createdAt.format(FORMATTER));
    }
}
