import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SlotsComponent } from './slots/slots.component' ;
import { TimeTableComponent } from './time-table/time-table.component' ;
import { FormComponent } from './form/form.component' ;
import { LoginComponent } from './login/login.component' ;
import {LoginGuard} from './login/loginGuard';
import {AuthGuard} from './AuthGuard';
import {AdminAuthGuard} from './AdminAuthGuard' ;

const routes: Routes = [
   { path: '', redirectTo: 'login', pathMatch: 'full' },
   { path: 'login' , component: LoginComponent , canActivate: [LoginGuard]},
   { path: 'home' , component: TimeTableComponent, canActivate: [AdminAuthGuard]},
   {path: 'slots', component: SlotsComponent, canActivate: [AdminAuthGuard] },
   {path: 'form' , component: FormComponent, canActivate: [AuthGuard]},
   {path: 'logout', component: LoginComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
