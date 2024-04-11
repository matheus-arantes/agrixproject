package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FarmService class.
 */

@Service
public class FarmService {
  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAll() {
    return farmRepository.findAll();
  }

  public Farm getById(Long id) {
    return farmRepository.findById(id)
            .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Adds a new crop .
   */

  public Crop addCrop(Long farmId, Crop crop) {
    Farm findedFarm = farmRepository.findById(farmId)
            .orElseThrow(FarmNotFoundException::new);
    crop.setFarm(findedFarm);
    return cropRepository.save(crop);
  }

  /**
   * Finds crops by farmId .
   */

  public List<Crop> findByFarm(Long farmId) {
    Farm findedFarm = farmRepository.findById(farmId)
            .orElseThrow(FarmNotFoundException::new);
    return findedFarm.getCrops();
  }

}
