package com.securebank.securenank.Model;

import jakarta.persistence.*;
import org.w3c.dom.Text;

@Entity
public class audit_log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_log;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private app_user user_id;

    @Column(name = "action")
    private String action;

    @Column(name = "entity")
    private String entity;

    @Column(name = "entity_id")
    private int entity_id;

    @Lob
    @Column(name = "details")
    private String details;

    public audit_log(int id_log, app_user user_id, String action, String entity,
                     int entity_id, String details) {
        this.id_log = id_log;
        this.user_id = user_id;
        this.action = action;
        this.entity = entity;
        this.entity_id = entity_id;
        this.details = details;
    }

    public audit_log() {
    }

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public app_user getUser_id() {
        return user_id;
    }

    public void setUser_id(app_user user_id) {
        this.user_id = user_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
