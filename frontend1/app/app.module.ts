import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SlotsComponent } from './slots/slots.component';
import { AgGridModule } from 'ag-grid-angular' ;
import { SlotService } from './slot.service';
import { CourseService } from './course.service';
import { TimeTableComponent } from './time-table/time-table.component';
import { FormComponent } from './form/form.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component' ;
import {NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import {ConfirmationDialogService} from './confirmation-dialog/confirmation-dialog.service';
import { LoginComponent } from './login/login.component' ;
import { AuthenticationService } from './authentication.service';
import {HttpInterceptorService} from './http-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    SlotsComponent,
    TimeTableComponent,
    FormComponent,
    ConfirmationDialogComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    NgbModalModule,
    AgGridModule.withComponents([])

  ],
  providers: [
            SlotService,
            ConfirmationDialogService,
            CourseService,
            AuthenticationService,
  ],
  bootstrap: [AppComponent],
  entryComponents: [ ConfirmationDialogComponent ]
})
export class AppModule { }
