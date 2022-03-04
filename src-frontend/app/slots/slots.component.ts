import { Component, OnInit } from '@angular/core';
import { Slot } from '../models/Slot' ;
import { DisplaySlot } from '../models/displaySlot' ;
import { Default_Slot } from '../models/Time' ;
import { SlotService } from '../slot.service' ;
import { CourseService } from '../course.service' ;
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationDialogService } from '../confirmation-dialog/confirmation-dialog.service';
import {CourseRequest } from '../models/courseRequest' ;
import {Course} from '../models/Course' ;

const COURSEREQ =  {
                      courseName: '',
                      slotType: 'DE',
                      semester: 'even',
                      strength: 0,
                      info: 'UG Only',
                      instructor: '',
                    };

@Component({
  selector: 'app-slots',
  templateUrl: './slots.component.html',
  styleUrls: ['./slots.component.css']
})
export class SlotsComponent implements OnInit {


  slot: Slot = {
         semester: 'even' ,
         slotType: 'DE' ,

         mondayFrom: Default_Slot ,
         mondayTo: Default_Slot ,

         tuesdayFrom: Default_Slot ,
         tuesdayTo: Default_Slot ,

         wednesdayFrom: Default_Slot ,
         wednesdayTo: Default_Slot ,

         thursdayFrom: Default_Slot ,
         thursdayTo: Default_Slot ,

         fridayFrom: Default_Slot ,
         fridayTo: Default_Slot ,

         saturdayFrom: Default_Slot ,
         saturdayTo: Default_Slot ,

         sundayFrom: Default_Slot ,
         sundayTo: Default_Slot ,
  } ;

  availableSlots: DisplaySlot[] = [] ;

  course: CourseRequest = COURSEREQ;
  savedCourses: Course[] = [] ;


  constructor(private slotService: SlotService, private route: ActivatedRoute, private router: Router,
       private confirmationDialogService: ConfirmationDialogService, private courseService: CourseService) {
  } ;

  ngOnInit(): void {
    this.refreshData() ;
  };

  refreshData(): void {
     this.slotService.getDisplaySlots().subscribe( availableSlots => {
          this.availableSlots = availableSlots ;
        }) ;
        this.courseService.getCourses().subscribe( courses =>{
           this.savedCourses = courses ;
        });
       this.course.courseName = '';
        this.course.strength = 0 ;
        this.course.instructor = '';
  } ;

  changeSlot(event: any): void{
     this.slot.slotType = event.target.value ;
   } ;

   changeSem(event: any): void{
     this.slot.semester = event.target.value ;
   }

   changeCourseSlot(event: any): void{
        this.course.slotType = event.target.value ;

   }

   changeCourseSem(event: any): void{
       this.course.semester = event.target.value ;
   }
   changeCourseInfo(event: any): void{
          this.course.info = event.target.value ;
   }

   saveSlotDetails(): void{
     this.slotService.saveDisplaySlot(this.slot).subscribe( response => {
      this.router.navigate(['\home']) ;
//          this.refreshData() ;
     }
     ) ;
   }

   saveCourseDetails(): void{

    if(this.course.courseName && this.course.courseName?.length>0 &&
         this.course.instructor && this.course.instructor?.length>0){
      this.courseService.saveCourse(this.course).subscribe( response => {
          this.saveConfirmation() ;
      }) ;
      }else this.errorDialogBox() ;

   }

   errorDialogBox(): void{
      this.confirmationDialogService.confirm('Error!..', 'You need to enter both course name and instructor id')
             .then( confirmed => {
              this.refreshData() ;
             }).catch(() => this.refreshData()) ;
   }


   saveConfirmation(): void{
     this.confirmationDialogService.confirm('Saved!..', 'Click Ok or Cancel to Continue..')
       .then( confirmed => {
        this.refreshData() ;
       }).catch(() => this.refreshData()) ;
   }


   deleteSlot(id: number): void{
       this.slotService.deleteDisplaySlotById(id).subscribe( response => {
       this.refreshData() ;
       } ) ;
   };

   confirmDeleteSlot(id: number):void {
        this.confirmationDialogService.confirm('Please Confirm..', 'Delete this Time Slot.. ?')
             .then((confirmed) => {
             if(confirmed)this.deleteSlot(id) ;
             }).catch(() => this.refreshData());
     };

   deleteCourse(id: number): void{
     this.courseService.deleteCourseById(id).subscribe( response => {
            this.refreshData() ;
            } ) ;
   };

   confirmDeleteCourse(id: number):void {
           this.confirmationDialogService.confirm('Please Confirm..', 'Delete this Time Slot.. ?')
                .then((confirmed) => {
                if(confirmed)this.deleteCourse(id) ;
                }).catch(() => this.refreshData());
        };
}
