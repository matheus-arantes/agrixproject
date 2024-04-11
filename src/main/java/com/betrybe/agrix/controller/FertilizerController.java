package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
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
 * FertilizerController class.
 */

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer created = fertilizerService.createFertilizer(fertilizerDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body(FertilizerDto.toDto(created));
  }

  /**
   * List all fertilizers.
   */

  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<List<FertilizerDto>> getAll() {
    List<Fertilizer> fertilizers = fertilizerService.getAll();
    List<FertilizerDto> fertilizerDtos = fertilizers.stream()
            .map(FertilizerDto::toDto)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> findById(@PathVariable Long id) {
    FertilizerDto fertilizerDto = FertilizerDto.toDto(fertilizerService.getById(id));
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerDto);
  }
}
