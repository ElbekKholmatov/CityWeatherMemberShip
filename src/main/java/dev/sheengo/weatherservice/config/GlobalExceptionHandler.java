package dev.sheengo.weatherservice.config;


import dev.sheengo.weatherservice.dto.response.AppErrorDTO;
import dev.sheengo.weatherservice.exceptions.NotFoundException;
import dev.sheengo.weatherservice.exceptions.UrlExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorMessage = "Input is not valid";
        Map<String, List<String>> errorBody = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorBody.compute(field, (s, values) -> {
                if (!Objects.isNull(values))
                    values.add(message);
                else
                    values = new ArrayList<>(Collections.singleton(message));
                return values;
            });
        }
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 400);
        return ResponseEntity.status(400).body(errorDTO);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleUrlNotFoundException(NotFoundException e, HttpServletRequest request) {
        String errorMessage = e.getMessage();
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, 404);
        return ResponseEntity.status(404).body(errorDTO);
    }

    @ExceptionHandler(UrlExpiredException.class)
    public ResponseEntity<AppErrorDTO> handleUrlExpiredException(UrlExpiredException e, HttpServletRequest request) {
        String errorMessage = e.getMessage();
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, 400);
        return ResponseEntity.status(400).body(errorDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppErrorDTO> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String errorMessage = e.getMessage();
        String errorBody = e.getMessage();
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 500);
        return ResponseEntity.status(500).body(errorDTO);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AppErrorDTO> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        String errorMessage = e.getMessage();
        String errorBody = e.getMessage();
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 500);
        return ResponseEntity.status(500).body(errorDTO);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<AppErrorDTO> handleIllegalStateException(IllegalStateException e, HttpServletRequest request) {
        String errorMessage = "Illegal state exception";
        String errorBody = e.getMessage();
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 500);
        return ResponseEntity.status(500).body(errorDTO);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AppErrorDTO> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String errorMessage = "Access denied";
        String errorBody = e.getMessage();
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 403);
        return ResponseEntity.status(403).body(errorDTO);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
        String errorMessage = "Username not found";
        String errorBody = e.getMessage();
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 401);
        return ResponseEntity.status(401).body(errorDTO);
    }
}

