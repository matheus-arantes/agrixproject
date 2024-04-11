package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * Crop Dto.
 */

public record CropDto(Long id, String name, Double plantedArea,
                      LocalDate plantedDate, LocalDate harvestDate, Long farmId) {
  public Crop toCrop() {
    return new Crop(id, name, plantedArea, plantedDate, harvestDate, null, null);
  }

  /**
   * toDto method.
   *
   * @param crop crop
   * @return crop dto
   */

  public static CropDto toDto(Crop crop) {
    return new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId());
  }
}
