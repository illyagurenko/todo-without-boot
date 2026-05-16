package ru.example.todo_without_boot.entity.dto;

import ru.example.todo_without_boot.entity.Task;
import ru.example.todo_without_boot.entity.TaskStatus;

import java.util.List;

public class TaskContainerDto {
    private final List<Task> tasks;
    private final int numberOfDoneTasks;
    private final int numberOfActiveTasks;

    public TaskContainerDto(List<Task> tasks, int numberOfDoneTasks, int numberOfActiveTasks) {
        this.tasks = tasks;
        this.numberOfDoneTasks = numberOfDoneTasks;
        this.numberOfActiveTasks = numberOfActiveTasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getNumberOfDoneTasks() {
        return numberOfDoneTasks;
    }

    public int getNumberOfActiveTasks() {
        return numberOfActiveTasks;
    }
}
