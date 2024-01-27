package br.com.jaime.camara.testejaimecamarabackend.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private int status;

    public BusinessException(String message, int status) {
        super(message);

        this.status = status;
    }
}
