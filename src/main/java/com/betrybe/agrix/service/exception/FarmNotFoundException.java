package com.betrybe.agrix.service.exception;

/**
 * FarmNotFoundException class.
 */

public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
