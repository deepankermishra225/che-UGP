package com.che.scheduler.repository;

import com.che.scheduler.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Boolean existsByUsername(String username) ;

}
