package it.mialeshka.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(LoadBookException.class)
    public ResponseEntity<?> handleException(LoadBookException e) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("ErrInfo", e.getMessage());
        return ResponseEntity.ok(errMap);
    }
}
