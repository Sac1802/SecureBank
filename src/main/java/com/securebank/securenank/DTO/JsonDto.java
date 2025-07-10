package com.securebank.securenank.DTO;

import com.securebank.securenank.Utils.StatusMessage;

public class JsonDto {
    public StatusMessage status;
    public String message;

    public JsonDto() {}

    public JsonDto(StatusMessage status, String message) {
        this.status = status;
        this.message = message;
    }
}
