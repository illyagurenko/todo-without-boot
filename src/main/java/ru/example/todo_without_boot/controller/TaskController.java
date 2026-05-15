package ru.example.todo_without_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.todo_without_boot.entity.Task;
import ru.example.todo_without_boot.entity.TaskStatus;
import ru.example.todo_without_boot.service.TaskService;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/")
    public String redirectToHomePage() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String getMainPage(Model model){
        List<Task> tasks = taskService.findAllTasks();
        int numOfDoneTasks = (int)tasks.stream()
                        .filter(task -> task.getStatus() == TaskStatus.DONE).count();
        int numOfActiveTasks = (int)tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.ACTIVE).count();
        model.addAttribute("tasks", tasks);
        model.addAttribute("numOfDoneTasks", numOfDoneTasks);
        model.addAttribute("numOfActiveTasks", numOfActiveTasks);
        return "main-page";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(@RequestParam("title") String title){
        taskService.saveRecord(title);
        return "redirect:/home";

    }
}
