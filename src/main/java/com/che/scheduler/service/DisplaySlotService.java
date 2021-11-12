package com.che.scheduler.service;

import com.che.scheduler.models.DisplaySlot;
import com.che.scheduler.repository.DisplaySlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplaySlotService {

    private final DisplaySlotRepository repository ;

    @Autowired
    public DisplaySlotService(DisplaySlotRepository repository){
        this.repository = repository ;
    }

    public void saveDisplaySlot(DisplaySlot displaySlot){
        this.repository.save(displaySlot) ;
    }

    public List<DisplaySlot> getDisplaySlots(){
        return this.repository.findAll() ;
    }

    public List<DisplaySlot> getDisplaySlotBySemester(String semester){
        return this.repository.findBySemester(semester) ;
    }

    public List<DisplaySlot> getDisplaySlotBySlotType(String slotType){
        return this.repository.findBySlotType(slotType) ;
    }

    public List<DisplaySlot> getDisplaySlotBySemesterAndSlotType(String semester , String slotType){
        return this.repository.findBySemesterAndSlotType(semester , slotType) ;
    }

    public void deleteDisplaySlotById(Integer Id){
        this.repository.deleteById(Id);
    }

}
