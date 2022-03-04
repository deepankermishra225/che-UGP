import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CourseRequest} from './models/courseRequest' ;
import {Course} from './models/Course' ;
import {Sem} from './models/Sem' ;
import {Schedule} from './models/Schedule';

const URL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class CourseService {

  constructor(private http: HttpClient) {
     }

    saveCourse(courseRequest : CourseRequest):Observable<any>{
        return this.http.post<any>(`${URL}/save/course`, courseRequest, { responseType: 'text' as 'json'}) ;
    }

    saveSem(sem: string): Observable<any>{
        return this.http.post<any>(`${URL}/save/sem/form`, sem, { responseType: 'text' as 'json'} ) ;
    }

    saveSlotToCourse( timeTable: string , courseName: string , slotType: string, slotName: string): Observable<any>{
       return this.http.post<any>(`${URL}/save/courseSlot/${courseName}/${slotType}/${slotName}`, timeTable, { responseType: 'text' as 'json'});
    }

    saveUsername(username: string): Observable<any>{
          return this.http.post<any>(`${URL}/admin/save`, username , {responseType: 'text' as 'json'}) ;
      }

    getTimeTable(paramString: string): Observable<Schedule[]>{
      return this.http.get<Schedule[]>(`${URL}/home/generate/${paramString}`);
    }

    getCourses(): Observable<Course[]>{
       return this.http.get<Course[]>(`${URL}/courses/all`) ;
    }

    getCoursesByUsername(semester: string, username: string): Observable<Course[]>{
       return this.http.get<Course[]>(`${URL}/courses/all/${semester}/${username}`) ;
    }

    getSem(): Observable<string>{
        return this.http.get(`${URL}/getSem`, {responseType: 'text'}) ;
    }

    getCourseSlotBySemester(semester: string): Observable<Course[]>{
        return this.http.get<Course[]>(`${URL}/courses/${semester}`) ;
    }

    getCourseBySlotType(slotType: string): Observable<Course[]>{
        return this.http.get<Course[]>(`${URL}/allcourses/${slotType}`) ;
    }

    getCourseBySemesterAndSlotType(semester: string, slotType: string): Observable<Course[]>{
        return this.http.get<Course[]>(`${URL}/courses/${semester}/${slotType}`) ;
    }

    getFixSlots(courseName: string): Observable<Schedule[]>{
       return this.http.get<Schedule[]>(`${URL}/fixSlots/${courseName}`) ;
    }

    getAdmin(): Observable<string[]>{
        return this.http.get<string[]>(`${URL}/admin/all`) ;
    }

    deleteCourseById(id: number): Observable<any>{
        return this.http.delete<any>(`${URL}/delete/course/${id}`) ;
    }

    deleteSem(): Observable<any>{
       return this.http.delete<any>(`${URL}/deleteSem`) ;
    }

    deleteSlotForCourse(slotName: string, courseName: string): Observable<any>{
        return this.http.delete<any>(`${URL}/deleteSlot/${slotName}/${courseName}`);
    }

    deleteUsername(username: string): Observable<any> {
        return this.http.delete<any>(`${URL}/admin/delete/${username}`) ;
    }


}
