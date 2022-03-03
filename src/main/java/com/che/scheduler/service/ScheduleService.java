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

    public List<Schedule> getFixSlots(String courseName){
        List<Schedule> scheduleList = this.repository.findByCourseName(courseName);
        scheduleList.sort(Comparator.comparing(Schedule::getPref));

        return scheduleList ;
    }

    public void saveSlotToCourse(String timeTable, String courseName , String slotType, String slotName){
        List<Schedule> scheduleList = this.repository.findByCourseName(courseName);
        Integer pref = scheduleList.size()+1 ;
        Schedule schedule = new Schedule() ;
        schedule.setCourseName(courseName);
        schedule.setSlotType(slotType);
        schedule.setPref(pref);
        schedule.setTimeTable(timeTable);
        schedule.setSlotName(slotName);

        this.repository.save(schedule) ;
    }

    public void deleteSlotForCourse(String slotName, String courseName){
        List<Schedule> schedules = this.repository.findByCourseNameAndSlotName(courseName, slotName);
        List<Schedule> scheduleList = this.repository.findByCourseName(courseName);
        Integer rank = schedules.get(0).getPref() ;
        scheduleList.forEach(schedule -> {
            if(schedule.getPref()>rank){
                this.repository.delete(schedule);
                schedule.setPref(schedule.getPref()-1);
                this.repository.save(schedule);
            }
        });
        schedules.forEach(schedule -> this.repository.delete(schedule)) ;
    }

    public void deleteAll(){
        this.repository.deleteAll();
    }
}
