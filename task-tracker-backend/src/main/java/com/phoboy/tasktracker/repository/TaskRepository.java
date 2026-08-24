package com.phoboy.tasktracker.repository;

import com.phoboy.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override
    <S extends Task> S save(S entity);

    List<Task> findTasksByUserId(Long userId);
}
