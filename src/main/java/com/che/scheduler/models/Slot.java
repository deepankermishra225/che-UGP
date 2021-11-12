package com.che.scheduler.models;

public class Slot {
    private String semester ;

    private String slotType ;

    private Time mondayFrom ;
    private Time mondayTo ;

    private Time tuesdayFrom ;
    private Time tuesdayTo ;

    private Time wednesdayFrom ;
    private Time wednesdayTo ;

    private Time thursdayFrom ;
    private Time thursdayTo ;

    private Time fridayFrom ;
    private Time fridayTo ;

    private Time saturdayFrom ;
    private Time saturdayTo ;

    private Time sundayFrom ;
    private Time sundayTo ;

    public String getSemester() {
        return this.semester;
    }

    public String getSlotType() {
        return this.slotType;
    }

    public Time getMondayFrom() {
        return this.mondayFrom;
    }

    public Time getMondayTo() {
        return this.mondayTo;
    }

    public Time getTuesdayFrom() {
        return this.tuesdayFrom;
    }

    public Time getTuesdayTo() {
        return this.tuesdayTo;
    }

    public Time getWednesdayFrom() {
        return this.wednesdayFrom;
    }

    public Time getWednesdayTo() {
        return this.wednesdayTo;
    }

    public Time getThursdayFrom() {
        return this.thursdayFrom;
    }

    public Time getThursdayTo() {
        return this.thursdayTo;
    }

    public Time getFridayFrom() {
        return this.fridayFrom;
    }

    public Time getFridayTo() {
        return this.fridayTo;
    }

    public Time getSaturdayFrom() {
        return this.saturdayFrom;
    }

    public Time getSaturdayTo() {
        return this.saturdayTo;
    }

    public Time getSundayFrom() {
        return this.sundayFrom;
    }

    public Time getSundayTo() {
        return this.sundayTo;
    }
}
