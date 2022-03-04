import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationDialogService } from '../confirmation-dialog/confirmation-dialog.service';
import { SlotService } from '../slot.service' ;
import { CourseService } from '../course.service' ;
import { Course } from '../models/Course' ;
import {DisplaySlot} from '../models/displaySlot';
import { AuthenticationService } from '../authentication.service';
import {Schedule} from '../models/Schedule';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {


  username?: string ;
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  selectedRows: Course[] = [] ;
  allSlots: DisplaySlot[] = [] ;
  semester: string = '';
  courses: Course[] = [] ;
  fixSlots: Schedule[] = [] ;
  freeSlots: DisplaySlot[] = [] ;
  addSlots: string[] = [] ;
  chosenCourse: string = '';
  slotType: string = '';

  constructor(private slotService: SlotService, private route: ActivatedRoute, private router: Router,
               private confirmationDialogService: ConfirmationDialogService, private courseService: CourseService,
               private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
     this.username = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)! ;
     this.refreshData() ;
  }


  refreshData(): void {

    this.courseService.getSem().subscribe( sem =>{
      this.semester = sem ;
      this.courseService.getCoursesByUsername(sem, this.username!).subscribe( courses =>{
            this.courses = courses ;
          });
      this.slotService.getDisplaySlotBySemester(sem).subscribe( slots => {
          this.allSlots = slots ;
      });
    }) ;

    this.chosenCourse = '';
    this.slotType='';
    this.fixSlots=[] ;
    this.freeSlots=[] ;
  }

   getList(course: any, slotType: any): void{

      this.chosenCourse = course ;
      this.slotType = slotType ;
      this.courseService.getFixSlots(course).subscribe( fixSlots => {
          this.fixSlots = fixSlots ;
          this.freeSlots = this.allSlots.filter( slot =>{
            if(fixSlots.some(fix =>{
            return slot.slotName === fix.slotName;})) return false ;
            else {
                return true ;
            }
          }) ;
      })

   }

   confirmAddSlot(timeTable: string, slotName: string): void {
     this.confirmationDialogService.confirm('Please Confirm..', 'Add this Time Slot.. ?')
                     .then((confirmed) => {
                     if(confirmed)this.addDetails(timeTable, slotName) ;
                     }).catch(() => this.refreshData());
   }

   addDetails(timeTable: string, slotName: string): void {
       this.courseService.saveSlotToCourse(timeTable , this.chosenCourse, this.slotType, slotName).subscribe(response =>{
       this.getList(this.chosenCourse, this.slotType);
       });
   }

    confirmDeleteSlot(slotName: string): void {
           this.confirmationDialogService.confirm('Please Confirm..', 'Delete this Time Slot.. ?')
                .then((confirmed) => {
                if(confirmed)this.removeDetails(slotName) ;
                }).catch(() => this.refreshData());
        };

   removeDetails(slotName: string): void {
        this.courseService.deleteSlotForCourse(slotName, this.chosenCourse).subscribe(response => {
         this.getList(this.chosenCourse, this.slotType);
        })
   }



}
