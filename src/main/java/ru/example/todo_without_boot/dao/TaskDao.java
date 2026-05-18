package ru.example.todo_without_boot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.example.todo_without_boot.entity.TaskStatus;
import ru.example.todo_without_boot.entity.Task;

import java.util.*;

@Repository
public class TaskDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Task> findAllTasks() {
        Query query = entityManager.createQuery("select t from Task t order by t.id asc");
        List<Task> tasks = query.getResultList();
        return tasks;
    }

    public void saveTask(Task task) {
        entityManager.persist(task);
    }

    public void updateTaskStatus(int id, TaskStatus status) {
        Query query = entityManager.createQuery("update Task set status = :status where id = :id");
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.executeUpdate();
    }

    public void deleteTask(int id) {
        Query query = entityManager.createQuery("delete from Task where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

}
