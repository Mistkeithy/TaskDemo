package com.mistkeith.firstjavabackend.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
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

    private List<Task> taskList = new ArrayList<Task>();

    public final ITaskRepository taskRepository;

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
    public List<Task> getTasks(String search,
            String sort,
            boolean reverse) {

        // Load taskRepository to Task util
        TaskOperation taskOperation = new TaskOperation(this.taskRepository.findAll());

        // Load taskList to Task util
        // TaskOperation taskOperation = new TaskOperation(taskList);

        // Search & Sort
        taskOperation.search(search);
        taskOperation.sort(sort);
        if (reverse) // reverse list
            taskOperation.reverse();

        return taskOperation.getTasks();
    }

    /**
     * Create tasks
     * 
     * @param newTasks
     * @return null
     */
    @PostMapping("/tasks")
    ResponseEntity<?> newTasks(@RequestBody List<Task> newTasks) {

        // Request is empty
        if (newTasks.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        // Find the last ID
        Long lastId = taskList.get(taskList.size() - 1).getId();

        // Add these new elements to an array (ignore)
        for (Task task : newTasks) {
            lastId++;
            task.setId(lastId); // force order by Id
            taskList.add(task);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(null);
    }

    /**
     * Edit tasks
     * 
     * @param editTasks
     * @return null
     */
    @PutMapping("/tasks")
    ResponseEntity<?> editTasks(@RequestBody List<Task> editTasks) {

        // Request is empty
        if (editTasks.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        // The temporary list
        List<Task> tempList = new ArrayList<Task>();

        for (Task task : taskList) {
            for (Task editTask : editTasks)
                if (task.getId() == editTask.getId()) // task matching by Id
                    task = editTask;
            tempList.add(task);
        }

        taskList = tempList;

        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    /**
     * Remove tasks
     * 
     * @param ids
     * @return null
     */
    @DeleteMapping("/tasks")
    ResponseEntity<?> removeTasks(@RequestParam Long[] ids) {

        // Request is empty
        if (ids == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        // The temporary list
        List<Task> tempList = new ArrayList<Task>();

        for (Task task : taskList) // send the match to tempList<Task>
            for (Long id : ids)
                if (task.getId().longValue() == id) { // task matching by Id
                    tempList.add(task);
                    break; // stop iteration if found
                }
        for (Task task : tempList) // remove match from taskList<Task>
            taskList.remove(task);

        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    /**
     * Get task by id
     * 
     * @param id
     * @return null
     */
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long id) {

        for (Task task : taskList)
            if (task.getId().longValue() == id) // task matching by Id
                return ResponseEntity.ok(task);

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
    public ResponseEntity<?> editTaskById(@PathVariable(value = "id") Long id,
            @RequestBody Task editTask) {

        // Request is empty
        if (editTask.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        editTask.setId(id); // force Id assigment

        // The temporary list
        List<Task> tempList = new ArrayList<Task>();

        for (Task task : taskList) {
            if (task.getId().longValue() == id) // task matching by Id
                task = editTask;
            tempList.add(task);
        }

        taskList = tempList;

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove task by id
     * 
     * @param id
     * @return null
     */
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> removeTaskById(@PathVariable(value = "id") Long id) {

        for (Task task : taskList)
            if (task.getId().longValue() == id) { // task matching by Id
                taskList.remove(task);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}