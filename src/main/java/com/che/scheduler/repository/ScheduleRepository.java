package com.che.scheduler.repository;

import com.che.scheduler.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByCourseName(String courseName) ;
    List<Schedule> findByCourseNameAndSlotName(String courseName, String slotName);
    List<Schedule> findByCourseNameAndTimeTable(String courseName, String timeTable) ;

}
