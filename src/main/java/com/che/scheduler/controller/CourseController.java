package com.che.scheduler.controller;

import com.che.scheduler.models.Course;
import com.che.scheduler.models.CourseRequest;
import com.che.scheduler.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService ;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService ;
    }

    @PostMapping("/save/course")
    public void saveCourse(@RequestBody CourseRequest courseRequest){
        Course course = new Course(courseRequest) ;
        this.courseService.saveCourse(course);
    }

    @GetMapping("/courses/all")
    public List<Course> getCourses(){
        return this.courseService.getCourses() ;
    }

    @GetMapping("/courses/{semester}")
    public List<Course> getCourseBySemester(@PathVariable String semester){
        return this.courseService.getCourseBySemester(semester) ;
    }

    @GetMapping("/allcourses/{slotType}")
    public List<Course> getCourseBySlotType(@PathVariable String slotType){
        return this.courseService.getCourseBySlotType(slotType);
    }

    @GetMapping("/courses/{semester}/{slotType}")
    public List<Course> getCourseBySemesterAndSlotType(@PathVariable("semester") String semester,
                                                       @PathVariable("slotType") String slotType){
        return this.courseService.getCourseBySemesterAndSlotType(semester , slotType) ;
    }

    @DeleteMapping("/delete/course/{id}")
    public void deleteCourse(@PathVariable("id") Integer id){
        this.courseService.deleteCourseById(id);
    }


}
