package com.che.scheduler.service;

import com.che.scheduler.models.Course;
import com.che.scheduler.models.DisplaySlot;
import com.che.scheduler.models.Schedule;
import com.che.scheduler.repository.CourseRepository;
import com.che.scheduler.repository.DisplaySlotRepository;
import com.che.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AlgoService {

    private final ScheduleRepository scheduleRepository ;
    private final CourseRepository courseRepository ;
    private final DisplaySlotRepository displaySlotRepository ;

    @Autowired
    public AlgoService(ScheduleRepository scheduleRepository, CourseRepository courseRepository,
                       DisplaySlotRepository displaySlotRepository){
        this.scheduleRepository = scheduleRepository ;
        this.courseRepository = courseRepository ;
        this.displaySlotRepository = displaySlotRepository ;
    }

    public List<Schedule> getTimeTable(String slotType){

        List<Schedule> preferences = this.scheduleRepository.findBySlotType(slotType);
        List<DisplaySlot> slots = this.displaySlotRepository.findBySlotType(slotType);
        List<Course> courses = this.courseRepository.findBySlotType(slotType) ;

        Map<String, Boolean> markCourses = new HashMap<>() ;
        courses.forEach(course -> markCourses.put(course.getCourseName(), false));

        Map<String, Boolean> markSlots = new HashMap<>() ;
        slots.forEach(slot -> markSlots.put(slot.getTimeTable(), false));

        List<Schedule> timeTableSchedule = new ArrayList<>() ;

        Map<String, Set<String>> courseMap = new HashMap<>() ;
        preferences.forEach(preference->{
            Set<String> set = courseMap.computeIfAbsent(preference.getTimeTable(), k -> new HashSet<>());
            set.add(preference.getCourseName()) ;
        } );





        // adding remaining courses
        courses.forEach(course -> {
            if(!markCourses.get(course.getCourseName())){
                slots.forEach(slot-> {
                    if(!markSlots.get(slot.getTimeTable())){
                       markSlots.put(slot.getTimeTable(),true);
                       markCourses.put(course.getCourseName(), true);
                       timeTableSchedule.add(new Schedule(course.getCourseName(), slot.getTimeTable(), slotType)) ;
                    }
                });
            }
        });

        return timeTableSchedule ;
    }

    public List<Schedule> generateTimeTable(){

        List<Schedule> ugTimeTable = getTimeTable("UG");
        List<Schedule> pgTimeTable = getTimeTable("PG");

        List<Schedule> finalTimeTable = new ArrayList<>(ugTimeTable);
        finalTimeTable.addAll(pgTimeTable);

        return finalTimeTable ;
    }


}
