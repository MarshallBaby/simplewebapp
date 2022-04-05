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

@RestControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("by.marshallbaby.controller-advice-logger");

    @ExceptionHandler({ResourceNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(Exception e, WebRequest request) {

        logger.error("Not Found", e);

        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                "Not found",
                request.getDescription(false)
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage httpMessageNotReadableException(Exception e, WebRequest request) {

        logger.error("Got bad request.", e);

        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Invalid Request Body.",
                request.getDescription(false)
        );
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            IdParameterMismatchException.class,
            ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage validationException(Exception e, WebRequest request) {

        logger.error("Got Invalid Request Body Data.", e);

        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Invalid Request Body Data.",
                request.getDescription(false)
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(Exception e, WebRequest request) {

        logger.error("Internal server error", e);

        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Internal server error",
                request.getDescription(false)
        );
    }
}
