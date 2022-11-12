package com.mistkeith.firstjavabackend.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mistkeith.firstjavabackend.demo.entity.Task;
import com.mistkeith.firstjavabackend.demo.repository.ITaskRepository;
import com.mistkeith.firstjavabackend.utils.TaskOperation;

@RestController
@CrossOrigin
public class TaskController {

    @Autowired
    private final ITaskRepository taskRepository;

    public TaskController(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Get all tasks
     * 
     * @param search  find match by string
     * @param sort    byName, byStatus, byStartDate, byEndDate, byDestination,
     *                byAuthor
     * @param reverse reverse all get data
     * @return JSON
     */
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks(String search,
            String sort,
            boolean reverse) {

        // Load taskRepository to Task util
        TaskOperation taskOperation = new TaskOperation(this.taskRepository.findAll());

        // Search & Sort
        taskOperation.search(search);
        taskOperation.sort(sort);
        if (reverse) // reverse list
            taskOperation.reverse();

        return new ResponseEntity<>(taskOperation.getTasks(), HttpStatus.CREATED);
    }

    /**
     * Create tasks
     * 
     * @param newTasks
     * @return null
     */
    @PostMapping("/tasks")
    ResponseEntity<Void> newTasks(
            @RequestBody List<Task> newTasks) {

        // Request is empty
        if (newTasks.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);

        try {
            // Add tasks to db
            for (Task task : newTasks)
                taskRepository.save(
                        new Task(task.getName(),
                                task.getStatus(),
                                task.getContent(),
                                task.getStartDate(),
                                task.getEndDate(),
                                task.getDestination(),
                                task.getAuthor()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }

    /**
     * Edit tasks
     * 
     * @param editTasks
     * @return null
     */
    @PutMapping("/tasks")
    ResponseEntity<Void> editTasks(
            @RequestBody List<Task> editTasks) {

        // Request is empty
        if (editTasks.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);

        try {
            // Edit some tasks
            for (Task task : editTasks) {
                Task _task = taskRepository
                        .findById(task.getId())
                        .get();
                _task.setName(
                        task.getName());
                _task.setContent(
                        task.getContent());
                _task.setStartDate(
                        task.getStartDate());
                _task.setEndDate(
                        task.getEndDate());
                _task.setDestination(
                        task.getDestination());
                _task.setStatus(
                        task.getStatus());
                _task.setAuthor(
                        task.getAuthor());
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }

    /**
     * Remove tasks
     * 
     * @param ids
     * @return null
     */
    @DeleteMapping("/tasks")
    ResponseEntity<?> removeTasks(
            @RequestParam Long[] ids) {

        // Request is empty
        if (ids == null)
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);

        // Remove tasks by Id
        for (Long id : ids)
            if (taskRepository.findById(id)
                    .get().getId() == id)
                taskRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(null);
    }

    /**
     * Get task by id
     * 
     * @param id
     * @return null
     */
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(
            @PathVariable(value = "id") Long id) {

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent())
            return ResponseEntity.ok(task.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /**
     * Edit task by id
     * 
     * @param id
     * @param editTask
     * @return null
     */
    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> editTaskById(
            @PathVariable(value = "id") Long id,
            @RequestBody Task editTask) {

        // Request is empty
        if (editTask.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);

        // Edit tasks
        try {
            Task task = taskRepository
                    .findById(id).get();
            task.setName(
                    editTask.getName());
            task.setContent(
                    editTask.getContent());
            task.setStartDate(
                    editTask.getStartDate());
            task.setEndDate(
                    editTask.getEndDate());
            task.setDestination(
                    editTask.getDestination());
            task.setStatus(
                    editTask.getStatus());
            task.setAuthor(
                    editTask.getAuthor());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    /**
     * Remove task by id
     * 
     * @param id
     * @return null
     */
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> removeTaskById(
            @PathVariable(value = "id") Long id) {

        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
    }

}