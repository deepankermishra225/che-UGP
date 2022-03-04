package com.che.scheduler.controller;

import com.che.scheduler.configuration.ImapConnection;
import com.che.scheduler.models.Schedule;
import com.che.scheduler.models.Sem;
import com.che.scheduler.models.User;
import com.che.scheduler.service.AdminService;
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
    private final AdminService adminService ;
    private final ScheduleService scheduleService ;
    private final ImapConnection imapConnection ;

    @Autowired
    public SemController(SemService semService, ScheduleService scheduleService
    , ImapConnection imapConnection, AdminService adminService){
        this.semService = semService ;
        this.scheduleService = scheduleService ;
        this.imapConnection = imapConnection ;
        this.adminService = adminService ;
    }

    @PostMapping("/login")
    public String getLogin(@RequestBody User user){

        boolean status = imapConnection.login(user.getUsername(), user.getPassword());
            if(!status) return "false" ;
            status = adminService.checkIfAdmin(user.getUsername());

            if(status) return "admin" ;
            else return "true" ;
    }

    @PostMapping("/save/sem/form")
    public void saveSem(@RequestBody String semester){
        Sem sem = new Sem() ;
        sem.setSemester(semester);
        this.semService.saveSem(sem);
    }

    @PostMapping("/save/courseSlot/{courseName}/{slotType}/{slotName}")
    public void saveSlotToCourse(@RequestBody String timeTable, @PathVariable("courseName") String courseName,
                                 @PathVariable("slotType") String slotType, @PathVariable("slotName")String slotName){
        this.scheduleService.saveSlotToCourse(timeTable, courseName, slotType, slotName);
    }

    @GetMapping("/getSem")
    public String getSem(){
        return this.semService.getSem();
    }

    @GetMapping("/fixSlots/{courseName}")
    public List<Schedule> getFixedSlots(@PathVariable String courseName){
        return this.scheduleService.getFixSlots(courseName);
    }

    @DeleteMapping("/deleteSem")
    public void deleteSem(){
        this.semService.deleteSem();
    }

    @DeleteMapping("/deleteSlot/{slotName}/{courseName}")
    public void deleteSlotForCourse(@PathVariable("courseName") String courseName,
                                    @PathVariable("slotName") String slotName){

         this.scheduleService.deleteSlotForCourse(slotName, courseName);
    }


}
