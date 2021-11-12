package com.che.scheduler.models;

import javax.persistence.*;

@Entity
@Table(name="SEM")
public class Sem {

    @Id
    @Column(name="SEMESTER")
    private String semester ;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
