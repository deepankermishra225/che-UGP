package com.che.scheduler.service;

import com.che.scheduler.models.*;
import com.che.scheduler.repository.CourseRepository;
import com.che.scheduler.repository.DisplaySlotRepository;
import com.che.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AlgoService {

    private final ScheduleRepository scheduleRepository ;
    private final CourseRepository courseRepository ;
    private final DisplaySlotRepository displaySlotRepository ;
    private final SemService semService ;

    Map<String, List<String>> clash = new HashMap<>() ;
    Map<String, Boolean> markCourses = new HashMap<>() ;
    Map<String, Boolean> markSlots = new HashMap<>() ;
    Map<String, Integer> priority = new HashMap<>() ;
    Map<String, String> slotToCourse = new HashMap<>() ;
    // get all the preferences for a course
    Map<String, List<String>> coursePref = new HashMap<>() ;

    @Autowired
    public AlgoService(ScheduleRepository scheduleRepository, CourseRepository courseRepository,
                       DisplaySlotRepository displaySlotRepository, SemService semService){
        this.scheduleRepository = scheduleRepository ;
        this.courseRepository = courseRepository ;
        this.displaySlotRepository = displaySlotRepository ;
        this.semService = semService ;
    }

    public void assignPriority(){
        // slotType + course-info --> rank
        priority.put("DC"+"PG & UG 3rd Year", 1) ;
        priority.put("DC"+"PG & UG 4th Year", 2) ;
        priority.put("DC"+"PG Only",3) ;
        priority.put("OE"+"PG & UG 3rd Year", 4) ;
        priority.put("OE"+"PG & UG 4th Year", 5) ;
        priority.put("DE"+"PG & UG 3rd Year", 4) ;
        priority.put("DE"+"PG & UG 4th Year", 5) ;
        priority.put("OE"+"UG Only", 6) ;
        priority.put("DE"+"UG Only", 6) ;
        priority.put("OE"+"PG Only", 7);
        priority.put("DE"+"PG Only", 7);
    }

    // get an array from String of timeTable
    public List<Time> fromStringToList(String s, Integer idx){
        // Time Table String will be in the format
        // "<3-letter-day><space><hour><hour>:<minute><minute>-<hour><hour>:<minute><minute><space>

        List<Time> timing = new ArrayList<>();
        for(int i=0 ; i<7 ; i++){
             Time t = new Time() ;
             t.setHour(12);
             t.setMinute(0);
             t.setSecond(0);
             timing.add(t) ;
        }

        for(int i=0 ; i<s.length() ; i+=16){
            String day = s.substring(i, i+3) ;
            String hour = "" ;
            String minute = "";
            Time t = new Time() ;
            if(idx==1){
                hour = s.substring(i+10, i+12);
                minute = s.substring(i+13, i+15);
            }else{
                hour = s.substring(i+4, i+6);
                minute = s.substring(i+7, i+9);
            }

            t.setHour(Integer.parseInt(hour));
            t.setMinute(Integer.parseInt(minute));
            t.setSecond(0);

            switch (day) {
                case "Mon":
                    timing.set(0, t);
                    break;
                case "Tue":
                    timing.set(1, t);
                    break;
                case "Wed":
                    timing.set(2, t);
                    break;
                case "Thu":
                    timing.set(3, t);
                    break;
                case "Fri":
                    timing.set(4, t);
                    break;
                case "Sat":
                    timing.set(5, t);
                    break;
                case "Sun":
                    timing.set(6, t);
                    break;
            }
        }

        return timing ;
    }

    public Boolean equals(Time t1, Time t2){
        return t1.getHour().equals(t2.getHour()) && t1.getMinute().equals(t2.getMinute());
    }

    // to check if first event occurs earlier (or at the same time) than the other
    public Boolean isLess(Time t1, Time t2){

        boolean status = false ;
        if(t1.equals(t2)) status = true ;
        else if(t1.getHour().equals(t2.getHour()) && t1.getMinute()<=t2.getMinute())status = true ;
        else if(t1.getHour()<t2.getHour()) status = true;

        return status ;
    }

    public Boolean checkTimes(Time start1, Time end1, Time start2, Time end2){

        boolean status = false ;


        // since 12 hour format is used if hour < 8 class won't be scheduled at 7 in the morning lol
        if(start1.getHour()<8)start1.setHour(start1.getHour()+12);
        if(start2.getHour()<8)start2.setHour(start2.getHour()+12);
        if(end1.getHour()<8)end1.setHour(end1.getHour()+12);
        if(end2.getHour()<8)end2.setHour(end2.getHour()+12);

        // start1<= start2 <= end1
        if(isLess(start1, start2) && isLess(start2, end1) && !equals(start2,end1))status = true ;
        // start1 <= end2 <= end1
        if(isLess(start1, end2) && isLess(end2, end1) && !equals(end2, start1)) status = true ;
        // start2<= start1 <= end2
        if(isLess(start2, start1) && isLess(start1, end2) && !equals(start1,end2))status = true ;
        // start2 <= end1 <= end2
        if(isLess(start2, end1) && isLess(end1, end2) && !equals(start2,end1)) status = true ;

        return status ;
    }

    public Boolean checkClash(String first, String second){

        List<Time> startFt = new ArrayList<>(fromStringToList(first, 0)) ;
        List<Time> endFt = new ArrayList<>(fromStringToList(first, 1)) ;
        List<Time> startSt = new ArrayList<>(fromStringToList(second,0)) ;
        List<Time> endSt = new ArrayList<>(fromStringToList(second, 1)) ;

        boolean status = false ;

        for(int i=0 ; i<startFt.size() ; i++){
            if(!equals(startFt.get(i), endFt.get(i)) && !equals(startSt.get(i), endSt.get(i))) {
               if(checkTimes(startFt.get(i), endFt.get(i), startSt.get(i), endSt.get(i)))status = true ;
            }
        }

        return status ;
    }

    public void enter(String slot, String clashSlot){
        List<String> data = clash.computeIfAbsent(slot, k-> new ArrayList<>() ) ;
        data.add(clashSlot);
    }

    public void getAllClashes(List<DisplaySlot> slots){

        for(int i = 0; i<slots.size() ; i++){
            for(int j=i+1 ; j<slots.size() ; j++){

                 if(checkClash(slots.get(i).getTimeTable(), slots.get(j).getTimeTable())) {
                     enter(slots.get(i).getSlotName(), slots.get(j).getSlotName()) ;
                     enter(slots.get(j).getSlotName(), slots.get(i).getSlotName());
                    // System.out.println(slots.get(i).getSlotName()+":"+slots.get(j).getSlotName());
                 }
            }
        }

    }

    public List<Schedule> generateTimeTable(String paramString){

        assignPriority();

        List<DisplaySlot> slots = this.displaySlotRepository.findBySemester(semService.getSem()) ;
        getAllClashes(slots);


        List<Schedule> choices = this.scheduleRepository.findAll() ;
        List<Course> courses = this.courseRepository.findBySemester(semService.getSem()) ;

        // map to store visited courses and slots

        courses.forEach(course -> markCourses.put(course.getCourseName(), false));


        slots.forEach(slot->{
            markSlots.put(slot.getSlotName(), false);
        });

        // sort all choices based on preferences
        choices.sort(Comparator.comparing(Schedule::getPref));

        choices.forEach(choice -> {
            List<String> data = coursePref.computeIfAbsent(choice.getCourseName(),
                    k-> new ArrayList<>());
                    data.add(choice.getSlotName());
        });

        // if randomly done else sort based on batch strength here
        if(paramString.equals("shuffle"))Collections.shuffle(courses);
        else {
            courses.sort((lhs, rhs) -> {
                if(lhs.getStrength() > rhs.getStrength()) return -1 ;
                else return 0 ;
            }) ;
        }

        courses.sort((lhs, rhs) -> {
            if (priority.get(lhs.getSlotType() + lhs.getInfo()) <
                    priority.get(rhs.getSlotType() + rhs.getInfo())) return -1;
            else return 0;
        });


        List<Schedule> timeTable= new ArrayList<>();
        Map<String , Boolean> vis = new HashMap<>() ;

        courses.forEach(course -> {
            if(!vis.containsKey(course.getSlotType()+course.getInfo())) {
                vis.put(course.getSlotType()+course.getInfo(), true) ;
                timeTable.addAll(generatorFromPriority(courses, course.getSlotType(), course.getInfo())) ;
            }
        });

        Map<String , Boolean> vis1 = new HashMap<>() ;
        courses.forEach(course -> {
            if(!vis1.containsKey(course.getSlotType()+course.getInfo())) {
                vis1.put(course.getSlotType()+course.getInfo(), true) ;
                timeTable.addAll(assignRandomlyWithoutClash(courses, course.getSlotType(), course.getInfo())) ;
            }
        });

        List<Course>compulsory = new ArrayList<>();
        Map<String , Boolean>allDc = new HashMap<>() ;
        courses.forEach(course -> {
            if(course.getSlotType().equals("DC") && !markCourses.get(course.getCourseName())){
                compulsory.add(course);
            }
            if(course.getSlotType().equals("DC")) allDc.put(course.getCourseName(), true);
        });


        compulsory.forEach(course -> {
            for(int i=timeTable.size()-1 ; i>=0 ; i--){
                if(timeTable.get(i).getSlotName().startsWith("PG")){
                    String courseName = timeTable.get(i).getCourseName() ;
                    if(!allDc.containsKey(courseName)){
                         markCourses.put(courseName, false);
                         markCourses.put(course.getCourseName(), true);
                         slotToCourse.put(timeTable.get(i).getSlotName(), course.getCourseName()) ;
                         timeTable.set(i, new Schedule(course.getCourseName(),timeTable.get(i).getSlotName(), "DC" )) ;
                    }
                }
            }
        });

        Map<String , Boolean> vis3 = new HashMap<>() ;
        courses.forEach(course -> {
            if(!vis3.containsKey(course.getSlotType()+course.getInfo())) {
                vis3.put(course.getSlotType()+course.getInfo(), true) ;
                timeTable.addAll(assignWithClash(courses, slots, course.getSlotType(), course.getInfo())) ;
            }
        });

        return timeTable ;
    }

    public List<Schedule> generatorFromPriority(List<Course> allCourses, String slotType, String info){
        List<Course> courses = new ArrayList<>() ;

        allCourses.forEach(course -> {
            if(course.getInfo().equals(info) && course.getSlotType().equals(slotType))courses.add(course);
        });


        List<Schedule> timeTable= new ArrayList<>();

        courses.forEach(course -> {
            String courseName = course.getCourseName();
            if(!markCourses.get(courseName) && coursePref.containsKey(courseName)){
                List<String> preferences = coursePref.get(courseName);
                preferences.forEach(slot->{
                    if(!markSlots.get(slot) && !markCourses.get(courseName)){
                        boolean check = true ;

                        if(clash.containsKey(slot)){
                            List<String> courseClash = clash.get(slot);
                            for (String s : courseClash) {
                                if (markSlots.get(s)) check = false;
                            }
                        }

                        if(check) {
                            markSlots.put(slot, true);
                            markCourses.put(courseName, true);
                            timeTable.add(new Schedule(courseName, slot, slotType));
                            slotToCourse.put(slot, course.getSlotType()) ;
                        }
                    }
                });
            }
        });

        return timeTable ;
    }

    public List<Schedule> assignRandomlyWithoutClash(List<Course> allCourses, String slotType, String info){

        List<Course> courses = new ArrayList<>() ;

        allCourses.forEach(course -> {
            if(course.getInfo().equals(info) && course.getSlotType().equals(slotType))courses.add(course);
        });

        List<Schedule> timeTable = new ArrayList<>() ;

        for (Course course : courses) {
            String courseName = course.getCourseName() ;
            if(markCourses.get(courseName))continue ;

            for(Map.Entry<String, Boolean> entry: markSlots.entrySet()){
                if(entry.getValue())continue ;
                if(entry.getKey().startsWith("DC"))continue ;
                if(course.getSlotType().equals("DC") && !entry.getKey().startsWith("PG"))continue ;
                boolean check = true ;
                if(clash.containsKey(entry.getKey())){
                    List<String> courseClash = clash.get(entry.getKey());
                    for (String s : courseClash) {
                        if (markSlots.get(s)) check = false;
                        if(s.startsWith("DC") && course.getInfo().equals("UG Only"))check = false ;
                        if(!course.getInfo().equals("UG Only") && slotToCourse.containsKey("DC"))check = false ;
                    }
                }

                if(check) {
                    markSlots.put(entry.getKey(), true);
                    markCourses.put(courseName, true);
                    timeTable.add(new Schedule(courseName, entry.getKey(), slotType));
                    slotToCourse.put(entry.getKey(), course.getSlotType()) ;
                    break ;
                }


            }
        }


        return timeTable ;
    }

    public List<Schedule> assignWithClash(List<Course> allCourses, List<DisplaySlot>slots, String slotType, String info){

        List<Course> courses = new ArrayList<>() ;
        allCourses.forEach(course -> {
            if(!markCourses.get(course.getCourseName())
                    && course.getInfo().equals(info) && course.getSlotType().equals(slotType))courses.add(course) ;
        });
        List<Schedule> timeTable = new ArrayList<>();

        courses.sort((lhs, rhs) -> {
            if (priority.get(lhs.getSlotType() + lhs.getInfo()) <
                    priority.get(rhs.getSlotType() + rhs.getInfo())) return -1;
            else return 0;
        });

        for(Course course: courses){

            int clashes = 1000 ;

            for(DisplaySlot slot: slots){
                if(markSlots.get(slot.getSlotName()))continue ;
                if(slot.getSlotName().startsWith("DC"))continue ;
                if(course.getSlotType().equals("DC") && !slot.getSlotType().equals("PG"))continue ;
                int temp = 0 ;
                boolean ok = true ;
                if(clash.containsKey(slot.getSlotName())){
                    List<String> courseClash = clash.get(slot.getSlotName()) ;

                    for(String s: courseClash){
                        if(!markSlots.get(s))continue ;
                        temp++ ;
                        if (s.startsWith("DC") && course.getInfo().equals("UG Only")) {
                            ok = false;
                            break;
                        }
                        if(!course.getInfo().equals("UG Only") && slotToCourse.containsKey("DC")){
                            ok = false ;
                            break ;
                        }
                    }
                }
                if(ok && temp<clashes) clashes = temp ;
            }

            for(DisplaySlot slot: slots){
                if(markSlots.get(slot.getSlotName()))continue ;
                if(slot.getSlotName().startsWith("DC"))continue ;
                if(course.getSlotType().equals("DC") && !slot.getSlotType().equals("PG"))continue ;
                int temp = 0 ;
                boolean ok = true ;
                if(clash.containsKey(slot.getSlotName())){
                    List<String> courseClash = clash.get(slot.getSlotName()) ;

                    for(String s: courseClash){
                        if(!markSlots.get(s))continue ;
                        temp++ ;
                        if (s.startsWith("DC") && course.getInfo().equals("UG Only")) {
                            ok = false;
                            break;
                        }
                        if(!course.getInfo().equals("UG Only") && slotToCourse.containsKey("DC")){
                            ok = false ;
                            break ;
                        }
                    }
                }
                if(ok && temp==clashes){
                    markSlots.put(slot.getSlotName(), true);
                    markCourses.put(course.getCourseName(), true);
                    timeTable.add(new Schedule(course.getCourseName(), slot.getSlotName(), slotType)) ;
                    slotToCourse.put(slot.getSlotName(), course.getSlotType()) ;
                    break ;
                }
            }


        }

        return timeTable ;
    }



}
