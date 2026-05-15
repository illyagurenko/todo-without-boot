package ru.example.todo_without_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.todo_without_boot.dao.TaskDao;
import ru.example.todo_without_boot.entity.Task;
import ru.example.todo_without_boot.entity.TaskStatus;

import java.util.List;

@Service
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao){
        this.taskDao = taskDao;
    }

    public List<Task> findAllTasks(){
        return taskDao.findAllTasks();
    }
    public void saveRecord(String title){
        taskDao.saveTask(new Task(title, TaskStatus.ACTIVE));
    }
}
