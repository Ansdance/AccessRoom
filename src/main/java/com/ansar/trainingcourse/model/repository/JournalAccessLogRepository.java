package com.ansar.trainingcourse.model.repository;

import com.ansar.trainingcourse.model.entity.JournalAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JournalAccessLogRepository extends JpaRepository<JournalAccessLog, Long> {

}
