import {Time } from './Time'

export class Slot {

   semester?: string ;
   slotType?: string ;
   mondayFrom?: Time ;
   mondayTo?: Time ;

   tuesdayFrom?: Time ;
   tuesdayTo?: Time ;

   wednesdayFrom?: Time ;
   wednesdayTo?: Time ;

   thursdayFrom?: Time ;
   thursdayTo?: Time ;

   fridayFrom?: Time ;
   fridayTo?: Time ;

   saturdayFrom?: Time ;
   saturdayTo?: Time ;

   sundayFrom?: Time ;
   sundayTo?: Time ;
}
