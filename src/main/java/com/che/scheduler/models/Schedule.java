package com.che.scheduler.models;

import javax.persistence.*;

@Entity
@Table(name="SCHEDULE")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "Id")
    private Integer id ;

    @Column(name="COLUMN_NAME")
    private String courseName ;

    @Column(name="SLOT_TYPE")
    private String slotType ;

    @Column(name="TIME_TABLE")
    private String timeTable ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Schedule(String courseName, String timeTable, String slotType){
        setTimeTable(timeTable);
        setSlotType(slotType);
        setCourseName(courseName);
    }

    public Schedule(){

    }
}
