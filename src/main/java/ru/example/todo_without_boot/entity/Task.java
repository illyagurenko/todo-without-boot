package ru.example.todo_without_boot.entity;

public class Task {
    private final String title;
    private TaskStatus status;

    public Task(String title, TaskStatus status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
