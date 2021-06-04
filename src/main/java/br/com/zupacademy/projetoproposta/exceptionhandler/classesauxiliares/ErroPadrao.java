package br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares;

import java.time.LocalTime;

public class ErroPadrao {

    private Integer statusCode;
    private String message;
    private Long timeStamp;

    public ErroPadrao(Integer statusCode, String message, Long timeStamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public Integer getStatusCode() {

        return statusCode;
    }

    public String getMessage() {

        return message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
