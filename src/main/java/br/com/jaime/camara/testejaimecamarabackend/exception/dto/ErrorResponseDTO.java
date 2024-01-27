package br.com.jaime.camara.testejaimecamarabackend.exception.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {
    private String message;

    private int status;
}
