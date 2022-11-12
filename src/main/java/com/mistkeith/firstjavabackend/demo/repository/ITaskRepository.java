package com.mistkeith.firstjavabackend.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mistkeith.firstjavabackend.demo.entity.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

}
