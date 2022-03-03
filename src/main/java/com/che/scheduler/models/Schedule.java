package com.che.scheduler.models;

import javax.persistence.*;

@Entity
@Table(name="SCHEDULE")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "Id")
    private Long id ;

    @Column(name="COURSE_NAME")
    private String courseName ;

    @Column(name="SLOT_TYPE")
    private String slotType ;

    @Column(name="TIME_TABLE")
    private String timeTable ;

    @Column(name="PREF")
    private Integer pref ;

    @Column(name="slotName")
    private String slotName ;

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public Integer getPref() {
        return pref;
    }

    public void setPref(Integer pref) {
        this.pref = pref;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public String getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(String timeTable) {
        this.timeTable = timeTable;
    }

    public Schedule(String courseName, String slotName, String slotType){
        setSlotName(slotName);
        setSlotType(slotType);
        setCourseName(courseName);
        setPref(0);
    }

    public Schedule(){

    }
}
