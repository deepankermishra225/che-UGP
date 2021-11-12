package com.che.scheduler.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DISPLAY_SLOT")
public class DisplaySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "Id")
    private Integer id ;

    @Column(name = "SLOT_TYPE")
    private String slotType ;

    @Column(name = "SEMESTER")
    private String semester ;

    @Column(name = "TIME_TABLE")
    private String timeTable ;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id ;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(String timeTable) {
        this.timeTable = timeTable;
    }

    public DisplaySlot(){

    }

    public DisplaySlot(Slot slot){


        String timeSlot = "";

        if(!Objects.equals(slot.getMondayFrom().getHour(), slot.getMondayTo().getHour())||
                (!Objects.equals(slot.getMondayFrom().getMinute(), slot.getMondayTo().getMinute()))){
            Time from = slot.getMondayFrom() ;
            Time to = slot.getMondayTo() ;

            if(from.getHour()==0)from.setHour(12); ;
            if(from.getHour()>12)from.setHour(from.getHour()-12);
            if(to.getHour()==0)to.setHour(12);
            if(to.getHour()>12)to.setHour(to.getHour()-12);

            timeSlot+= "Mon " ;
            if(from.getHour()<10)timeSlot+="0" ;
            timeSlot+= from.getHour() + ":" ;
            if(from.getMinute()<10)timeSlot+="0";
            timeSlot+= from.getMinute() + "-" ;
            if(to.getHour()<10)timeSlot+="0" ;
            timeSlot+= to.getHour()+ ":" ;
            if(to.getMinute()<10)timeSlot+="0";
            timeSlot+= to.getMinute()+ " " ;
        }
        if(!Objects.equals(slot.getTuesdayFrom().getHour(), slot.getTuesdayTo().getHour())||
                (!Objects.equals(slot.getTuesdayFrom().getMinute(), slot.getTuesdayTo().getMinute()))){
            Time from = slot.getTuesdayFrom() ;
            Time to = slot.getTuesdayTo() ;

            if(from.getHour()==0)from.setHour(12); ;
            if(from.getHour()>12)from.setHour(from.getHour()-12);
            if(to.getHour()==0)to.setHour(12);
            if(to.getHour()>12)to.setHour(to.getHour()-12);


            timeSlot+= "Tue "  ;
            if(from.getHour()<10)timeSlot+="0" ;
            timeSlot+= from.getHour() + ":" ;
            if(from.getMinute()<10)timeSlot+="0";
            timeSlot+= from.getMinute() + "-" ;
            if(to.getHour()<10)timeSlot+="0" ;
            timeSlot+= to.getHour()+ ":" ;
            if(to.getMinute()<10)timeSlot+="0";
            timeSlot+= to.getMinute()+ " " ;
        }
        if(!Objects.equals(slot.getWednesdayFrom().getHour(), slot.getWednesdayTo().getHour())||
                (!Objects.equals(slot.getWednesdayFrom().getMinute(), slot.getWednesdayTo().getMinute()))){
            Time from = slot.getWednesdayFrom() ;
            Time to = slot.getWednesdayTo() ;

            if(from.getHour()==0)from.setHour(12); ;
            if(from.getHour()>12)from.setHour(from.getHour()-12);
            if(to.getHour()==0)to.setHour(12);
            if(to.getHour()>12)to.setHour(to.getHour()-12);

            timeSlot+= "Wed "  ;
            if(from.getHour()<10)timeSlot+="0" ;
            timeSlot+= from.getHour() + ":" ;
            if(from.getMinute()<10)timeSlot+="0";
            timeSlot+= from.getMinute() + "-" ;
            if(to.getHour()<10)timeSlot+="0" ;
            timeSlot+= to.getHour()+ ":" ;
            if(to.getMinute()<10)timeSlot+="0";
            timeSlot+= to.getMinute()+ " " ;
        }
        if(!Objects.equals(slot.getThursdayFrom().getHour(), slot.getThursdayTo().getHour())||
                (!Objects.equals(slot.getThursdayFrom().getMinute(), slot.getThursdayTo().getMinute()))){
            Time from = slot.getThursdayFrom() ;
            Time to = slot.getThursdayTo() ;

            if(from.getHour()==0)from.setHour(12); ;
            if(from.getHour()>12)from.setHour(from.getHour()-12);
            if(to.getHour()==0)to.setHour(12);
            if(to.getHour()>12)to.setHour(to.getHour()-12);

            timeSlot+= "Thur " ;
            if(from.getHour()<10)timeSlot+="0" ;
            timeSlot+= from.getHour() + ":" ;
            if(from.getMinute()<10)timeSlot+="0";
            timeSlot+= from.getMinute() + "-" ;
            if(to.getHour()<10)timeSlot+="0" ;
            timeSlot+= to.getHour()+ ":" ;
            if(to.getMinute()<10)timeSlot+="0";
            timeSlot+= to.getMinute()+ " " ;
        }
        if(!Objects.equals(slot.getFridayFrom().getHour(), slot.getFridayTo().getHour())||
                (!Objects.equals(slot.getFridayFrom().getMinute(), slot.getFridayTo().getMinute()))){
            Time from = slot.getFridayFrom() ;
            Time to = slot.getFridayTo() ;

            if(from.getHour()==0)from.setHour(12); ;
            if(from.getHour()>12)from.setHour(from.getHour()-12);
            if(to.getHour()==0)to.setHour(12);
            if(to.getHour()>12)to.setHour(to.getHour()-12);

            timeSlot+= "Fri " ;
            if(from.getHour()<10)timeSlot+="0" ;
            timeSlot+= from.getHour() + ":" ;
            if(from.getMinute()<10)timeSlot+="0";
            timeSlot+= from.getMinute() + "-" ;
            if(to.getHour()<10)timeSlot+="0" ;
            timeSlot+= to.getHour()+ ":" ;
            if(to.getMinute()<10)timeSlot+="0";
            timeSlot+= to.getMinute()+ " " ;
        }
        if(!Objects.equals(slot.getSaturdayFrom().getHour(), slot.getSaturdayTo().getHour())||
                (!Objects.equals(slot.getSaturdayFrom().getMinute(), slot.getSaturdayTo().getMinute()))){
            Time from = slot.getSaturdayFrom() ;
            Time to = slot.getSaturdayTo() ;

            if(from.getHour()==0)from.setHour(12); ;
            if(from.getHour()>12)from.setHour(from.getHour()-12);
            if(to.getHour()==0)to.setHour(12);
            if(to.getHour()>12)to.setHour(to.getHour()-12);

            timeSlot+= "Sat " ;
            if(from.getHour()<10)timeSlot+="0" ;
            timeSlot+= from.getHour() + ":" ;
            if(from.getMinute()<10)timeSlot+="0";
            timeSlot+= from.getMinute() + "-" ;
            if(to.getHour()<10)timeSlot+="0" ;
            timeSlot+= to.getHour()+ ":" ;
            if(to.getMinute()<10)timeSlot+="0";
            timeSlot+= to.getMinute()+ " " ;
        }
        if(!Objects.equals(slot.getSundayFrom().getHour(), slot.getSundayTo().getHour())||
                (!Objects.equals(slot.getSundayFrom().getMinute(), slot.getSundayTo().getMinute()))){
            Time from = slot.getSundayFrom() ;
            Time to = slot.getSundayTo() ;

            if(from.getHour()==0)from.setHour(12); ;
            if(from.getHour()>12)from.setHour(from.getHour()-12);
            if(to.getHour()==0)to.setHour(12);
            if(to.getHour()>12)to.setHour(to.getHour()-12);

            timeSlot+= "Sun "  ;
            if(from.getHour()<10)timeSlot+="0" ;
            timeSlot+= from.getHour() + ":" ;
            if(from.getMinute()<10)timeSlot+="0";
            timeSlot+= from.getMinute() + "-" ;
            if(to.getHour()<10)timeSlot+="0" ;
            timeSlot+= to.getHour()+ ":" ;
            if(to.getMinute()<10)timeSlot+="0";
            timeSlot+= to.getMinute()+ " " ;
        }

        setSemester(slot.getSemester());
        setSlotType(slot.getSlotType());
        setTimeTable(timeSlot);

    }

}
