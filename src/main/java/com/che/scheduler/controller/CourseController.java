package com.che.scheduler.controller;

import com.che.scheduler.models.Course;
import com.che.scheduler.models.CourseRequest;
import com.che.scheduler.models.Schedule;
import com.che.scheduler.service.AlgoService;
import com.che.scheduler.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService ;
    private final AlgoService algoService ;

    @Autowired
    public CourseController(CourseService courseService, AlgoService algoService){
        this.courseService = courseService ;
        this.algoService = algoService ;
    }

    @PostMapping("/save/course")
    public void saveCourse(@RequestBody CourseRequest courseRequest){
        Course course = new Course(courseRequest) ;
        this.courseService.saveCourse(course);
    }

    @GetMapping("/home/generate/{paramString}")
    public List<Schedule> getTimeTable(@PathVariable String paramString){
        return this.algoService.generateTimeTable(paramString) ;
    }

    @GetMapping("/courses/all")
    public List<Course> getCourses(){
        return this.courseService.getCourses() ;
    }

    @GetMapping("/courses/all/{semester}/{username}")
    public List<Course> getCoursesByUsername(@PathVariable("semester")String semester,
            @PathVariable("username") String username){
        return this.courseService.getCoursesByInstructor(semester, username) ;
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
