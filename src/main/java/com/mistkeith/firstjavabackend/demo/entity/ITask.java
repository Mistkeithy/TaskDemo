package com.mistkeith.firstjavabackend.demo.entity;

public interface ITask {

    public Boolean isEmpty();

    public void setName(String name);

    public void setStatus(Integer status);

    public void setContent(String content);

    public void setStartDate(String startDate);

    public void setEndDate(String endDate);

    public void setAssign(String assign);

    public void setAuthor(String author);

    public Long getId();

    public String getName();

    public Integer getStatus();

    public String getContent();

    public String getStartDate();

    public String getEndDate();

    public String getAssign();

    public String getAuthor();
}
