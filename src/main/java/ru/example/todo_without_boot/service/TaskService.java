package ru.example.todo_without_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.todo_without_boot.dao.TaskDao;
import ru.example.todo_without_boot.entity.Task;
import ru.example.todo_without_boot.entity.TaskStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao){
        this.taskDao = taskDao;
    }

    public List<Task> findAllTasks(String filterMode){
        List<Task> tasks = taskDao.findAllTasks();
        if( filterMode==null || filterMode.isBlank()){
            return tasks;
        }
        String filterToUpperCase = filterMode.toUpperCase();
        List<String> statuses = Arrays.stream(TaskStatus.values())
                .map(Enum::name)
                .toList();
        if(statuses.contains(filterToUpperCase)){
            return tasks.stream()
                    .filter(task -> task.getStatus() == TaskStatus.valueOf(filterToUpperCase))
                    .toList();

        }
        else{
            return tasks;
        }
    }
    public void saveRecord(String title){
        if(!title.isBlank()){
            taskDao.saveTask(new Task(title, TaskStatus.ACTIVE));
        }

    }

    public void updateTaskStatus(int id, TaskStatus status){
        taskDao.updateTaskStatus(id, status);
    }

    public void deleteTask(int id){
        taskDao.deleteTask(id);
    }
}
