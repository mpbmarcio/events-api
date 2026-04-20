package br.com.mpb.events.controller;

import br.com.mpb.events.dto.response.ErrorDTO;
import br.com.mpb.events.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException nfe) {
        return ResponseEntity.status(404).body(new ErrorDTO(nfe.getMessage()));
    }
}
