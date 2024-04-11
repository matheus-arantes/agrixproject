package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * CropService class.
 */

@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public List<Crop> getAll() {
    return cropRepository.findAll();
  }

  public Crop getById(Long id) {
    return cropRepository.findById(id)
            .orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * InsertFertilizer method.
   */

  public void insertFertilizer(Long cropId, Long fertilizerId) {
    Crop findedCrop = cropRepository.findById(cropId)
            .orElseThrow(CropNotFoundException::new);

    Fertilizer findedFertilizer = fertilizerRepository.findById(fertilizerId)
            .orElseThrow(FertilizerNotFoundException::new);

    findedCrop.getFertilizers().add(findedFertilizer);
    cropRepository.save(findedCrop);
  }

  /**
   * Gets all the fertilizers associated with a crop.
   * */

  public List<Fertilizer> getFertilizersByCrop(Long cropId) {
    Crop findedCrop = cropRepository.findById(cropId)
            .orElseThrow(CropNotFoundException::new);
    return findedCrop.getFertilizers();
  }

}

