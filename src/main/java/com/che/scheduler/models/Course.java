package com.che.scheduler.models;

import javax.persistence.*;

@Entity
@Table(name="COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "Id")
    private Integer id ;

    @Column(name="INFO")
    private String info ;

    @Column(name = "SLOT_TYPE")
    private String slotType ;

    @Column(name = "SEMESTER")
    private String semester ;

    @Column(name = "COURSE_NAME")
    private String courseName ;

    @Column(name = "STRENGTH")
    private Integer strength ;

    @Column(name = "INSTRUCTOR")
    private String instructor ;


    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Course(){
    }

    public Course(CourseRequest courseRequest){
        setInfo(courseRequest.getInfo());
        setCourseName(courseRequest.getCourseName());
        setStrength(courseRequest.getStrength());
        setSemester(courseRequest.getSemester());
        setSlotType(courseRequest.getSlotType());
        setInstructor(courseRequest.getInstructor());
    }
}