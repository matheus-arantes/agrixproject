package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CropController class.
 */

@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Get all crops.
   */

  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
  public ResponseEntity<List<CropDto>> getAll() {
    List<Crop> crops = cropService.getAll();
    List<CropDto> cropsDto = crops.stream()
            .map(CropDto::toDto)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getById(@PathVariable Long id) {
    CropDto cropDto = CropDto.toDto(cropService.getById(id));
    return ResponseEntity.status(HttpStatus.OK).body(cropDto);
  }

  /**
   * findByHarvest date.
   * */

  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getByDate(@RequestParam LocalDate start,
                                                 @RequestParam LocalDate end) {
    List<Crop> crops = cropService.findByHarvestDateBetween(start, end);
    List<CropDto> cropsDto = crops.stream()
            .map(CropDto::toDto)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }

  /**
   * Associates a fertilizer with a crop.
   * */

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> insertFertilizer(@PathVariable Long cropId,
                                                 @PathVariable Long fertilizerId) {
    cropService.insertFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Gets all the fertilizers associated with a crop.
   * */

  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCrop(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getFertilizersByCrop(cropId);
    List<FertilizerDto> fertilizersDto = fertilizers.stream()
            .map(FertilizerDto::toDto)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(fertilizersDto);
  }

}
