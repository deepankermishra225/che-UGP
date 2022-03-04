import { Component, OnInit } from '@angular/core';
import { SlotService } from '../slot.service' ;
import { CourseService } from '../course.service' ;
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationDialogService } from '../confirmation-dialog/confirmation-dialog.service';
import { DisplaySlot } from '../models/displaySlot' ;
import {Schedule} from '../models/Schedule';
import {Course} from '../models/Course' ;
import { ColDef } from 'ag-grid-community';

@Component({
  selector: 'app-time-table',
  templateUrl: './time-table.component.html',
  styleUrls: ['./time-table.component.css']
})
export class TimeTableComponent implements OnInit {

   ugCourses: Course[] = [] ;
   ugSlots: DisplaySlot[] = [] ;
   pgCourses: Course[] = [] ;
   pgSlots: DisplaySlot[] = [] ;
   semester: string = '' ;
   gridApi: any ;
   rowData : Schedule[] = [] ;
   timeTable = false ;
   radioItems: Array<string>;
   model   = {option: 'shuffle'};
   paramString = 'shuffle';
   username: string = '';
   admins: string[] = [];

   constructor(private slotService: SlotService, private route: ActivatedRoute, private router: Router,
         private confirmationDialogService: ConfirmationDialogService, private courseService: CourseService) {
          this.radioItems = ['shuffle', 'strength'];
    } ;

  ngOnInit(): void {
     this.refreshData() ;
  }

  onGridReady(params: any){
       this.gridApi = params.api;
  }

   columnDefs: ColDef[] = [
            { field: 'courseName', sortable: true, filter: true },
            { field: 'slotType', sortable: true, filter: true },
            { field: 'slotName', sortable: true, filter: true, width: 500 }
        ];

   onBtnExport(){
         this.gridApi.exportDataAsCsv();
   }


  refreshData(): void{
     this.courseService.getSem().subscribe(sem => {
          this.semester = sem ;
          if(this.semester!='empty')this.getSlotsAndCourses(this.semester);
          else this.getSlotsAndCourses('even');
     });
     this.getAdmin() ;
  }

  getSlotsAndCourses(semester: string){
     this.slotService.getDisplaySlotBySemesterAndSlotType(semester, 'UG').subscribe(slots =>{
             this.ugSlots = slots ;
         });
         this.slotService.getDisplaySlotBySemesterAndSlotType(semester, 'PG').subscribe(slots =>{
             this.pgSlots = slots ;
         });

         this.courseService.getCourseBySemesterAndSlotType(semester, 'UG').subscribe(courses =>{
             this.ugCourses = courses ;
         });
         this.courseService.getCourseBySemesterAndSlotType(semester, 'PG').subscribe(courses =>{
                  this.pgCourses = courses ;
         });
  }

  genEven(): void{
     this.courseService.saveSem('even').subscribe(response=> {
       this.refreshData() ;
     });
  }

  confirmGenEven():void {
          this.confirmationDialogService.confirm('Please Confirm..', 'Generate form for even Semester ?')
               .then((confirmed) => {
               if(confirmed)this.genEven() ;
               }).catch(() => this.refreshData());
  };

  genOdd(): void{
       this.courseService.saveSem('odd').subscribe(response=> {
         this.refreshData() ;
       });
  }

  changeParam(event: any): void {
      this.paramString = event.target.value ;
  }

  confirmGenOdd():void {
            this.confirmationDialogService.confirm('Please Confirm..', 'Generate form for odd Semester ?')
                 .then((confirmed) => {
                 if(confirmed)this.genOdd() ;
                 }).catch(() => this.refreshData());
  };

  deleteSem(): void {
       this.courseService.deleteSem().subscribe(response =>{
          this.refreshData() ;
       }) ;
  }

  confirmDelete():void {
          this.confirmationDialogService.confirm('Are You Sure?', 'All the priorities will be lost if you discard...')
               .then((confirmed) => {
               if(confirmed)this.deleteSem() ;
               }).catch(() => this.refreshData());
  };

  generate(){
     this.timeTable=true ;
     this.courseService.getTimeTable(this.paramString).subscribe(timeTable =>{
        this.rowData = timeTable ;
     })
  }

  confirmSaveUsername(){
      this.confirmationDialogService.confirm('Please Confirm..', this.username + ' will be added to admin list')
                     .then((confirmed) => {
                     if(confirmed)this.saveUsername() ;
                     }).catch(() => this.refreshData());
  }

  confirmDeleteUsername(user: string){
     this.confirmationDialogService.confirm('Are You Sure?', user + ' will be lost...')
                   .then((confirmed) => {
                   if(confirmed)this.deleteUsername(user) ;
                   }).catch(() => this.refreshData());
  }

  saveUsername(){
    if(this.username.length>0){
        this.courseService.saveUsername(this.username).subscribe( response => {
            this.username = '' ;
            this.refreshData() ;
      })
    }
  }

  getAdmin(){
      this.courseService.getAdmin().subscribe( data => {
          this.admins = data ;
      })
  }
  deleteUsername(username: string){
     this.courseService.deleteUsername(username).subscribe( data => {
          this.refreshData() ;
     })
  }

}
