package br.com.jaime.camara.testejaimecamarabackend.exception;

public class BadRequestException extends BusinessException {
    public BadRequestException(String message) {
        super(message, 400);
    }
}
