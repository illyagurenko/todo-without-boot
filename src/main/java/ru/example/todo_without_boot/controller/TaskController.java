package ru.example.todo_without_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.todo_without_boot.entity.Task;
import ru.example.todo_without_boot.entity.TaskStatus;
import ru.example.todo_without_boot.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/test")
    public String getMainPage(Model model){
        List<Task> tasks = taskService.findAllTasks();
        int numOfDoneTasks = (int)tasks.stream()
                        .filter(task -> task.getStatus() == TaskStatus.DONE).count();
        int numOfActiveTasks = (int)tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.ACTIVE).count();
        model.addAttribute("numOfDoneTasks", numOfDoneTasks);
        model.addAttribute("numOfActiveTasks", numOfActiveTasks);
        return "main-page";
    }
}
