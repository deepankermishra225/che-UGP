package com.che.scheduler.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DISPLAY_SLOT")
public class DisplaySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "Id")
    private Integer id ;

    @Column(name = "SLOT_TYPE")
    private String slotType ;

    @Column(name = "SLOT_NAME")
    private String slotName ;

    @Column(name = "SEMESTER")
    private String semester ;

    @Column(name = "TIME_TABLE")
    private String timeTable ;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id ;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(String timeTable) {
        this.timeTable = timeTable;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public DisplaySlot(){

    }

    public DisplaySlot(String semester, String timeSlot, String slotType, String slotName){
        setSemester(semester);
        setSlotType(slotType);
        setTimeTable(timeSlot);
        setSlotName(slotName);
    }

}
