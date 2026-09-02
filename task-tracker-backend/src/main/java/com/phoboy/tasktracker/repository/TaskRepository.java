package com.phoboy.tasktracker.repository;

import com.phoboy.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override
    <S extends Task> S save(S entity);

    List<Task> findTasksByUserIdOrderByIdDesc(Long userId);

    List<Task> findTasksByUserIdAndCreatedAtBetween(Long userId, Instant from, Instant to);

    Optional<Task> findByUserIdAndId(Long userId, Long taskId);

}
