package com.securebank.securenank.DTO;

import jakarta.websocket.Decoder;

public class audit_logDTO {

    public int id_log;

    public int user_id;

    public String action;

    public String entity;

    public int entity_id;

    public Decoder.Text details;
}
