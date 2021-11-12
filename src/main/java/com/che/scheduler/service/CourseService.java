package com.che.scheduler.service;

import com.che.scheduler.models.Course;
import com.che.scheduler.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository ;

    @Autowired
    public CourseService(CourseRepository repository){
        this.repository = repository;
    }

    public void saveCourse(Course course){
        this.repository.save(course);
    }

    public List<Course> getCourses(){
        return this.repository.findAll();
    }

    public List<Course> getCourseBySemester(String semester){
        return this.repository.findBySemester(semester) ;
    }

    public List<Course> getCourseBySlotType(String slotType){
        return this.repository.findBySlotType(slotType) ;
    }

    public List<Course> getCourseBySemesterAndSlotType(String semester , String slotType){
        return this.repository.findBySemesterAndSlotType(semester , slotType) ;
    }

    public void deleteCourseById(Integer Id){
        this.repository.deleteById(Id);
    }
}