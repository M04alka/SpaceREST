package BackEnd.Controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalErrorsController {
  @ExceptionHandler({ HttpServerErrorException.class })
  public ResponseEntity<ErrorResponse> errorResponseResponseEntity(Exception e) {
    return ResponseEntity
        .status(BAD_REQUEST)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(new ErrorResponse(e.getMessage()));
  }

  @Data
  @AllArgsConstructor
  class ErrorResponse {
    String errorMessage;
  }
  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<ErrorResponse> ExceptionNotFound(Exception e) {
    return ResponseEntity
            .status(NOT_FOUND)
            .contentType(MediaType.APPLICATION_PROBLEM_JSON)
            .body(new ErrorResponse(e.getMessage()));
  }
  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorResponse> OtherProblems(Exception e) {
    return ResponseEntity
            .status(BAD_REQUEST)
            .contentType(MediaType.APPLICATION_PROBLEM_JSON)
            .body(new ErrorResponse(e.getMessage()));
  }
}
