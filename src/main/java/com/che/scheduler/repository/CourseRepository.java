package com.che.scheduler.repository;

import com.che.scheduler.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course>findBySemester(String semester);

    List<Course> findBySlotType(String slotType) ;

    List<Course> findBySemesterAndSlotType(String semester , String slotType) ;
}