package br.com.jaime.camara.testejaimecamarabackend.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message, 404);
    }
}
