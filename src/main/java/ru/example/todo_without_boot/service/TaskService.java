package ru.example.todo_without_boot.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.todo_without_boot.dao.TaskDao;
import ru.example.todo_without_boot.entity.Task;
import ru.example.todo_without_boot.entity.TaskStatus;
import ru.example.todo_without_boot.entity.dto.TaskContainerDto;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao){
        this.taskDao = taskDao;
    }

    public TaskContainerDto findAllTasks(String filterMode){
        List<Task> tasks = taskDao.findAllTasks();
        int numOfDoneTasks = (int)tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.DONE).count();
        int numOfActiveTasks = (int)tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.ACTIVE).count();

        if( filterMode==null || filterMode.isBlank()){
            return new TaskContainerDto(tasks, numOfDoneTasks, numOfActiveTasks);
        }
        String filterToUpperCase = filterMode.toUpperCase();
        List<String> statuses = Arrays.stream(TaskStatus.values())
                .map(Enum::name)
                .toList();
        if(statuses.contains(filterToUpperCase)){
            List<Task> filterTasks =  tasks.stream()
                    .filter(task -> task.getStatus() == TaskStatus.valueOf(filterToUpperCase))
                    .toList();
            return new TaskContainerDto(filterTasks, numOfDoneTasks, numOfActiveTasks);
        }
        else{
            return new TaskContainerDto(tasks, numOfDoneTasks, numOfActiveTasks);
        }
    }
    public void saveRecord(String title){
        if(title != null && !title.isBlank()){
            taskDao.saveTask(new Task(title));
        }

    }

    public void updateTaskStatus(int id, TaskStatus status){
        taskDao.updateTaskStatus(id, status);
    }

    public void deleteTask(int id){
        taskDao.deleteTask(id);
    }
}
