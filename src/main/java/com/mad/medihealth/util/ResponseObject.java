package com.mad.medihealth.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ResponseObject {
    private int code;
    private String message;
    private Object data;

    public ResponseObject() {

    }

    public ResponseObject(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
