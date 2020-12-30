package controller.exception;

import lombok.extern.slf4j.Slf4j;
import model.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** Exception handler component. */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
  /**
   * Handling exceptions related to incorrectly sent parameters
   *
   * @param exception an exception
   * @return an error message
   */
  @ResponseBody
  @ExceptionHandler(IOException.class)
  public ResponseEntity<ResponseMessage> handleMissingServletRequestPartException(
      final IOException exception) {
    log.warn(exception.getMessage());
    return ResponseEntity.badRequest().body(new ResponseMessage(exception.getMessage()));
  }

  /**
   * Handling Exceptions with Invalid Parameters
   *
   * @param exception an exception
   * @return an error message
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException exception) {
    log.warn(exception.getMessage());
    Map<String, String> errors = new HashMap<>();
    exception
        .getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    return ResponseEntity.badRequest().body(errors);
  }
}
