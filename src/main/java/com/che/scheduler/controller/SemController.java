package com.che.scheduler.controller;

import com.che.scheduler.models.Sem;
import com.che.scheduler.service.ScheduleService;
import com.che.scheduler.service.SemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SemController {

    private final SemService semService ;
    private final ScheduleService scheduleService ;

    @Autowired
    public SemController(SemService semService, ScheduleService scheduleService){

        this.semService = semService ;
        this.scheduleService = scheduleService ;
    }

    @PostMapping("/save/sem/form")
    public void saveSem(@RequestBody String semester){
        Sem sem = new Sem() ;
        sem.setSemester(semester);
        this.semService.saveSem(sem);
    }

    @PostMapping("/save/courseSlot/{courseName}/{slotType}")
    public void saveSlotToCourse(@RequestBody String timeTable, @PathVariable("courseName") String courseName,
                                 @PathVariable("slotType") String slotType){
        this.scheduleService.saveCourseToSlot(timeTable, courseName, slotType);
    }

    @GetMapping("/getSem")
    public String getSem(){
        return this.semService.getSem();
    }

    @GetMapping("/fixSlots/{courseName}")
    public List<String> getFixedSlots(@PathVariable String courseName){
        return this.scheduleService.getFixSlots(courseName);
    }

    @DeleteMapping("/deleteSem")
    public void deleteSem(){
        this.semService.deleteSem();
    }

    @DeleteMapping("/deleteSlot/{timeTable}/{courseName}")
    public void deleteSlotForCourse(@PathVariable("timeTable") String timeTable,
                                    @PathVariable("courseName") String courseName){

         this.scheduleService.deleteSlotForCourse(timeTable, courseName);
    }


}
