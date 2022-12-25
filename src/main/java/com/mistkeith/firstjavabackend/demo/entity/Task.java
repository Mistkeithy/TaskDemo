package com.mistkeith.firstjavabackend.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task implements ITask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer status;
    private String content;
    private String startDate;
    private String endDate;
    private String assign;
    private String author;

    // Default constructor
    public Task() {
    }

    public Task(String name,
            Integer status,
            String content,
            String startDate,
            String endDate,
            String assign,
            String author) {
        this.name = name;
        this.status = status;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assign = assign;
        this.author = author;
    }

    @Override
    public Boolean isEmpty() {
        if (name.isEmpty() &&
                status == null &&
                content.isEmpty() &&
                startDate.isEmpty() &&
                endDate.isEmpty() &&
                assign.isEmpty() &&
                author.isEmpty())
            return true;
        return false;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setAssign(String assign) {
        this.assign = assign;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public String getAssign() {
        return assign;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}
