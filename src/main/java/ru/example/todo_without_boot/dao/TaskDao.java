package ru.example.todo_without_boot.dao;

import org.springframework.stereotype.Repository;
import ru.example.todo_without_boot.entity.TaskStatus;
import ru.example.todo_without_boot.entity.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class TaskDao {
    private final List<Task> tasks = new ArrayList<>(Arrays.asList(
            new Task("Take a shower", TaskStatus.ACTIVE),
            new Task("Buy flowers", TaskStatus.DONE),
            new Task("Go to the gym", TaskStatus.ACTIVE)
    ));

    public List<Task> findAllTasks() {
        return new ArrayList<>(tasks);
    }
    public void saveTask(Task task){
        tasks.add(task);
    }

    public void updateTaskStatus(int id, TaskStatus status){
        for(Task task : tasks){
            if(task.getId() == id){
                task.setStatus(status);
                break;
            }
        }
    }

    public void deleteTask(int id){
        tasks.removeIf(task -> task.getId() == id);
    }

}
