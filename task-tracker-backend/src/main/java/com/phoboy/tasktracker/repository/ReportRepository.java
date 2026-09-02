package com.phoboy.tasktracker.repository;

import com.phoboy.tasktracker.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    @Override
    <S extends Report> S save(S entity);

    @Override
    <S extends Report> S saveAndFlush(S entity);
}
