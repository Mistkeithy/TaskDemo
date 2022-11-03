package com.mistkeith.firstjavabackend.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mistkeith.firstjavabackend.demo.entity.Task;

public class TaskOperation {

    private List<Task> taskList = new ArrayList<Task>();

    public TaskOperation(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void search(String searching) {
        if (searching == null || searching.isEmpty())
            return;
        searching = searching.toLowerCase();
        List<Task> searchResults = new ArrayList<Task>();
        for (Task task : taskList) {
            // search filter
            if (task.getName().toLowerCase().contains(searching) ||
                    task.getStatus().toString().toLowerCase().contains(searching) ||
                    task.getContent().toString().toLowerCase().contains(searching) ||
                    task.getStartDate().toLowerCase().contains(searching) ||
                    task.getEndDate().toLowerCase().contains(searching) ||
                    task.getDestination().toLowerCase().contains(searching) ||
                    task.getAuthor().toLowerCase().contains(searching))
                searchResults.add(task);
        }
        taskList = searchResults;
    }

    public void sort(String sorting) {
        if (sorting == null || sorting.isEmpty())
            return;
        switch (sorting) {
            case "byName":
                Collections.sort(taskList, (one, two) -> ((Task) two).getName()
                        .compareTo(((Task) one).getName()));
                break;
            case "byStatus":
                Collections.sort(taskList, (one, two) -> ((Task) two).getStatus()
                        .compareTo(((Task) one).getStatus()));
                break;
            case "byStartDate":
                Collections.sort(taskList, (one, two) -> ((Task) two).getStartDate()
                        .compareTo(((Task) one).getStartDate()));
                break;
            case "byEndDate":
                Collections.sort(taskList, (one, two) -> ((Task) two).getEndDate()
                        .compareTo(((Task) one).getEndDate()));
                break;
            case "byDestination":
                Collections.sort(taskList, (one, two) -> ((Task) two).getDestination()
                        .compareTo(((Task) one).getDestination()));
                break;
            case "byAuthor":
                Collections.sort(taskList, (one, two) -> ((Task) two).getAuthor()
                        .compareTo(((Task) one).getAuthor()));
                break;
        }
    }

    public void reverse() {
        Collections.reverse(taskList);
    }

    public void setTasks(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTasks() {
        return taskList;
    }

}
