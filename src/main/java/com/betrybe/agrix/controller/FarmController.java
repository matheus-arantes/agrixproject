package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FarmController class.
 */

@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    Farm created = farmService.createFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.toDto(created));
  }

  /**
   * List all farms.
   */

  @GetMapping
  @PreAuthorize(
          "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
  public ResponseEntity<List<FarmDto>> getAll() {
    List<Farm> farms = farmService.getAll();
    List<FarmDto> farmsDto = farms.stream()
            .map(FarmDto::toDto)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(farmsDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getById(@PathVariable Long id) {
    FarmDto farmDto = FarmDto.toDto(farmService.getById(id));
    return ResponseEntity.status(HttpStatus.OK).body(farmDto);
  }

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> addCrop(@PathVariable Long farmId,
                                         @RequestBody CropDto cropDto) {
    Crop appendedCrop = farmService.addCrop(farmId, cropDto.toCrop());
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.toDto(appendedCrop));
  }

  /**
   * Returns the crops from a certain farm.
   */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> findByFarm(@PathVariable Long farmId) {
    List<CropDto> cropDtos = farmService.findByFarm(farmId).stream()
            .map(CropDto::toDto)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropDtos);
  }
}
