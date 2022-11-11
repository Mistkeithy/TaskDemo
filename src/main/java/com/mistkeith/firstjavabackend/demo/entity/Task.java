package com.mistkeith.firstjavabackend.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private Integer status;
    @Column(name = "content")
    private String content;
    @Column(name = "startDate")
    private String startDate;
    @Column(name = "endDate")
    private String endDate;
    @Column(name = "destination")
    private String destination;
    @Column(name = "author")
    private String author;

    public Task(Long id,
            String name,
            Integer status,
            String content,
            String startDate,
            String endDate,
            String destination,
            String author) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
        this.author = author;
    }

    public boolean isEmpty() {
        if (id == null &&
                name.isEmpty() &&
                status == null &&
                content.isEmpty() &&
                startDate.isEmpty() &&
                endDate.isEmpty() &&
                destination.isEmpty() &&
                author.isEmpty())
            return true;
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getAuthor() {
        return author;
    }
}
