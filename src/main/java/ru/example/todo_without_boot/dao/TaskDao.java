package ru.example.todo_without_boot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.example.todo_without_boot.entity.TaskStatus;
import ru.example.todo_without_boot.entity.Task;

import java.util.*;

@Repository
public class TaskDao {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public TaskDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Task> findAllTasks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("select t from Task t");
            List<Task> tasks = query.getResultList();

            entityManager.getTransaction().commit();

            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return Collections.emptyList();
        }
        finally {
            entityManager.close();
        }
    }

    public void saveTask(Task task){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
    }

    public void updateTaskStatus(int id, TaskStatus status){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("update Task set status = :status where id = :id");
            query.setParameter("id", id);
            query.setParameter("status", status);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
    }

    public void deleteTask(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("delete from Task where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
    }

}
