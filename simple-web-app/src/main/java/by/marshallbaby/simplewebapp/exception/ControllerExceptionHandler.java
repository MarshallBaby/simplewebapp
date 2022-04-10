package by.marshallbaby.simplewebapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.UUID;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private final Logger controllerAdviceLogger = LoggerFactory.getLogger(
            "by.marshallbaby.controller-advice-logger"
    );

    private final Logger requestResponseLogger = LoggerFactory.getLogger(
            "by.marshallbaby.request-logger"
    );

    private ErrorMessage handleException(Exception e, WebRequest request, String errorMessage) {
        UUID errorId = UUID.randomUUID();

        requestResponseLogger.warn(
                "OUT ERROR: {}. Watch info in controller advice log. Error ID: {}",
                errorMessage,
                errorId
        );
        controllerAdviceLogger.error("{} Error ID: {}", errorMessage, errorId, e);

        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                String.format("%s Error Id: %s", errorMessage, errorId),
                request.getDescription(false)
        );
    }

    @ExceptionHandler({ResourceNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(Exception e, WebRequest request) {
        return handleException(e, request, "Not found.");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage httpMessageNotReadableException(Exception e, WebRequest request) {
        return handleException(e, request, "Got bad request.");
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            IdParameterMismatchException.class,
            ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage validationException(Exception e, WebRequest request) {
        return handleException(e, request, "Invalid Request Body Data.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(Exception e, WebRequest request) {
        return handleException(e, request, "Internal server error.");
    }
}
