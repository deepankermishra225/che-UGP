package com.che.scheduler.service;

import com.che.scheduler.models.Sem;
import com.che.scheduler.repository.SemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemService {

    private final SemRepository repository ;
    private final ScheduleService scheduleService ;

    @Autowired
    public SemService(SemRepository repository, ScheduleService scheduleService){

        this.repository = repository ;
        this.scheduleService = scheduleService ;
    }

    public void saveSem(Sem sem){
        this.repository.save(sem);
    }

    public String getSem(){
       if(this.repository.existsById("even")){
           return "even" ;
       }else if(this.repository.existsById("odd")){
           return "odd";
       }else return "empty" ;
    }

    public void deleteSem(){
        this.scheduleService.deleteAll() ;
        this.repository.deleteAll();
    }
}
