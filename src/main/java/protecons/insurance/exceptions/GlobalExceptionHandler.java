package protecons.insurance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            DuplicateEmailException.class
    )
    public ResponseEntity<Map<String, String>>
    handleDuplicateEmail(
            DuplicateEmailException exception) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        Map.of(
                                "error",
                                "EMAIL_ALREADY_EXISTS",

                                "message",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(
            InvalidCredentialsException.class
    )
    public ResponseEntity<Map<String, String>>
    handleInvalidCredentials(
            InvalidCredentialsException exception) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        Map.of(
                                "error",
                                "INVALID_CREDENTIALS",

                                "message",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(
            IllegalArgumentException.class
    )
    public ResponseEntity<Map<String, String>>
    handleIllegalArgument(
            IllegalArgumentException exception) {

        return ResponseEntity
                .badRequest()
                .body(
                        Map.of(
                                "error",
                                "BAD_REQUEST",

                                "message",
                                exception.getMessage()
                        )
                );
    }
}
