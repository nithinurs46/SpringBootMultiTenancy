package dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.entity.DBDetails;

@Repository
public interface DataSourceConfigRepository extends JpaRepository<DBDetails, String> {
}
