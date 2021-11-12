package com.che.scheduler.repository;

import com.che.scheduler.models.Sem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemRepository extends JpaRepository<Sem, String> {
}
