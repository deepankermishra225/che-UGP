package com.che.scheduler.service;

import com.che.scheduler.models.Schedule;
import com.che.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleService {

    private final ScheduleRepository repository ;

    @Autowired
    public ScheduleService(ScheduleRepository repository){
        this.repository = repository ;
    }

    public List<String> getFixSlots(String courseName){
        List<Schedule> scheduleList = this.repository.findByCourseName(courseName);
        List<String> slots = new ArrayList<>();

        scheduleList.forEach(schedule -> slots.add(schedule.getTimeTable()));
        return slots ;
    }

    public void saveCourseToSlot(String timeTable, String courseName , String slotType){
        Schedule schedule = new Schedule() ;
        schedule.setCourseName(courseName);
        schedule.setSlotType(slotType);

        schedule.setTimeTable(timeTable);

        this.repository.save(schedule) ;
    }

    public void deleteSlotForCourse(String timeTable, String courseName){
        List<Schedule> schedules = this.repository.findByCourseNameAndTimeTable(courseName, timeTable);
        schedules.forEach(schedule -> this.repository.delete(schedule)) ;
    }

    public void deleteAll(){
        this.repository.deleteAll();
    }
}
