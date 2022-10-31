package com.aman.repository;

import com.aman.domain.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

public interface RecordRepository extends JpaRepository<Record, Long>, JpaSpecificationExecutor<Record> {
    Optional<Record> findByName(String name);
}
