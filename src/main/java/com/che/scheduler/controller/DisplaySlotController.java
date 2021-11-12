package com.che.scheduler.controller;

import com.che.scheduler.models.DisplaySlot;
import com.che.scheduler.models.Slot;
import com.che.scheduler.service.DisplaySlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DisplaySlotController {

    private final DisplaySlotService displaySlotService;

    @Autowired
    public DisplaySlotController(DisplaySlotService displaySlotService) {
        this.displaySlotService = displaySlotService;
    }

    @PostMapping("/save")
    public void saveDisplaySlot(@RequestBody Slot slot) {
        DisplaySlot displaySlot = new DisplaySlot(slot);
        this.displaySlotService.saveDisplaySlot(displaySlot);
    }

    @GetMapping("/slots/all")
    public List<DisplaySlot> getDisplaySlots() {
        return this.displaySlotService.getDisplaySlots();
    }

    @GetMapping("/slots/{semester}")
    public List<DisplaySlot> getDisplaySlotBySemester(@PathVariable String semester) {
        return this.displaySlotService.getDisplaySlotBySemester(semester);
    }

    @GetMapping("/allslots/{slotType}")
    public List<DisplaySlot> getDisplaySlotBySlotType(@PathVariable String slotType) {
        return this.displaySlotService.getDisplaySlotBySlotType(slotType);
    }

    @GetMapping("/slots/{semester}/{slotType}")
    public List<DisplaySlot> getDisplaySlotBySemesterAndSlotType(@PathVariable("semester") String semester, @PathVariable("slotType") String slotType) {
        return this.displaySlotService.getDisplaySlotBySemesterAndSlotType(semester, slotType);
    }

    @DeleteMapping("/delete/slot/{id}")
    public void deleteDisplaySlotById(@PathVariable("id") Integer id) {
         this.displaySlotService.deleteDisplaySlotById(id);
    }

}
