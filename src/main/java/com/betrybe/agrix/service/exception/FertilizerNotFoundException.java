package com.betrybe.agrix.service.exception;

/**
 * FertilizerNotFoundException class.
 */

public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
