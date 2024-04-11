package com.betrybe.agrix.controller.advice;

import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import com.betrybe.agrix.service.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalControllerAdvice class.
 */

@ControllerAdvice
public class GlobalControllerAdvice {

  /**
   * Handle farm not found.
   */

  @ExceptionHandler({FarmNotFoundException.class})
  public ResponseEntity<String> handleFarmNotFound(FarmNotFoundException e) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
  }

  /**
   * Handle crop not found.
   */

  @ExceptionHandler({CropNotFoundException.class})
  public ResponseEntity<String> handleCropNotFound(CropNotFoundException e) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
  }

  /**
   * Handle fertilizer not found.
   */

  @ExceptionHandler({FertilizerNotFoundException.class})
  public ResponseEntity<String> handleFertilizerNotFound(FertilizerNotFoundException e) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
  }

  /**
   * Handle person not found.
   */

  public ResponseEntity<String> handlePersonNotFound(PersonNotFoundException e) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
  }
}
