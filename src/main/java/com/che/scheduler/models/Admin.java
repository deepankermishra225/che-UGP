package com.che.scheduler.models;

import javax.persistence.*;

@Entity

@Table(name="ADMIN")
public class Admin {
    @Id
    @Column(name="Username")
    private String username ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
