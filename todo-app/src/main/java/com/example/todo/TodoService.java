package com.example.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TodoService {

    private final List<TodoItem> items = new ArrayList<>();
    private int nextId = 1;

    public TodoItem add(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("标题不能为空");
        }
        TodoItem item = new TodoItem(nextId++, title.trim());
        items.add(item);
        return item;
    }

    public List<TodoItem> listAll() {
        return Collections.unmodifiableList(items);
    }

    public List<TodoItem> listPending() {
        List<TodoItem> pending = new ArrayList<>();
        for (TodoItem item : items) {
            if (!item.isCompleted()) {
                pending.add(item);
            }
        }
        return pending;
    }

    public Optional<TodoItem> findById(int id) {
        for (TodoItem item : items) {
            if (item.getId() == id) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public boolean complete(int id) {
        Optional<TodoItem> item = findById(id);
        if (!item.isPresent()) {
            return false;
        }
        item.get().setCompleted(true);
        return true;
    }

    public boolean remove(int id) {
        return items.removeIf(item -> item.getId() == id);
    }

    public int count() {
        return items.size();
    }

    public int countCompleted() {
        int count = 0;
        for (TodoItem item : items) {
            if (item.isCompleted()) {
                count++;
            }
        }
        return count;
    }
}
