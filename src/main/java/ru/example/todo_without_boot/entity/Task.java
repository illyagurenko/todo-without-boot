package ru.example.todo_without_boot.entity;

public class Task {
    private static int counterSequence = 0;
    private final int id;
    private final String title;
    private TaskStatus status;

    public Task( String title, TaskStatus status) {
        this.id = counterSequence++;
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

    public int getId() {
        return id;
    }
}
