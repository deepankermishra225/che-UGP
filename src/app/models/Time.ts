export class Time {
  hour?: number ;
  minute?: number ;
  second?: number ;
}

export class SlotTime {
  from?: Time ;
  to?: Time;
}

export const TIMES: Time[] = [
  {hour:0 , minute:0, second:0 } ,
  {hour:0 , minute:0, second:0 } ,
  {hour:0 , minute:0, second:0 } ,
  {hour:0 , minute:0, second:0 } ,
  {hour:0 , minute:0, second:0 } ,
  {hour:0 , minute:0, second:0 }
] ;

export const Default_Slot : Time = {hour:0 , minute:0, second:0 } ;

export const SLOTS: SlotTime[] = [
  {
    from: {hour:0 , minute:0, second:0 } ,
    to: {hour:0 , minute:0, second:0 }
  },
  {
      from: {hour:0 , minute:0, second:0 } ,
      to: {hour:0 , minute:0, second:0 }
  },
  {
      from: {hour:0 , minute:0, second:0 } ,
      to: {hour:0 , minute:0, second:0 }
  },
  {
     from: {hour:0 , minute:0, second:0 } ,
     to: {hour:0 , minute:0, second:0 }
  },
  {
     from: {hour:0 , minute:0, second:0 } ,
     to: {hour:0 , minute:0, second:0 }
  },
  {
     from: {hour:0 , minute:0, second:0 } ,
     to: {hour:0 , minute:0, second:0 }
  },
  {
     from: {hour:0 , minute:0, second:0 } ,
     to: {hour:0 , minute:0, second:0 }
  }

]
