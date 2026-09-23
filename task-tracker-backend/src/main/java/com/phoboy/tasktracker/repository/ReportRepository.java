package com.phoboy.tasktracker.repository;

import com.phoboy.tasktracker.model.Report;

import com.phoboy.tasktracker.shared.dto.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    @Override
    <S extends Report> S save(S entity);

    @Override
    <S extends Report> S saveAndFlush(S entity);

    int deleteAllByStatus(ReportStatus reportStatus);

    @Modifying
    @Query(value = "UPDATE Report r SET r.status = :reportStatus WHERE r.id = :id")
    int updateReportStatus(@Param("id") UUID uuid,
                           @Param("reportStatus") ReportStatus reportStatus);
}
