package br.com.jaime.camara.testejaimecamarabackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.jaime.camara.testejaimecamarabackend.exception.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        var error = new ErrorResponseDTO();
        error.setMessage(ex.getMessage());
        error.setStatus(ex.getStatus());

        return this.handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatusCode.valueOf(ex.getStatus()), request);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        var error = new ErrorResponseDTO();
        error.setMessage(ex.getMessage());
        error.setStatus(401);

        return this.handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
