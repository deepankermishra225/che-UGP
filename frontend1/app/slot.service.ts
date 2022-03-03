import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Slot} from './models/Slot';
import {DisplaySlot} from './models/displaySlot' ;

const URL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class SlotService {

  constructor(private http: HttpClient) {
   }

   saveDisplaySlot(slot: Slot) : Observable<any> {
     return this.http.post<any>(`${URL}/save`, slot, {responseType: 'text' as 'json'}) ;
   }

   getDisplaySlots() : Observable<DisplaySlot[]> {
     return this.http.get<DisplaySlot[]>(`${URL}/slots/all` ) ;
   }

   getDisplaySlotBySemester(semester: string): Observable<DisplaySlot[]>{
      return this.http.get<DisplaySlot[]>(`${URL}/slots/${semester}`) ;
   }

   getDisplaySlotBySlotType(slotType: string): Observable<DisplaySlot[]>{
       return this.http.get<DisplaySlot[]>(`${URL}/allslots/${slotType}`) ;
   }

   getDisplaySlotBySemesterAndSlotType(semester: string, slotType: string): Observable<DisplaySlot[]>{
       return this.http.get<DisplaySlot[]>(`${URL}/slots/${semester}/${slotType}`) ;
   }

   deleteDisplaySlotById(id: number): Observable<any>{
      return this.http.delete<any>(`${URL}/delete/slot/${id}`) ;
   }


}
