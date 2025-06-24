package com.mcontreras.msvc.usuario.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter @Setter
public class ErrorDTO {
    private Integer status;
    private Date date;

    private Map<String, String> errors;


    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", date=" + date +
                ", errors=" + errors +
                '}';
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
