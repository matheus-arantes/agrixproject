package com.betrybe.agrix.service.exception;

/**
 * FarmNotFoundException class.
 */

public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}