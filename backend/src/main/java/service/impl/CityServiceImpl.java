package service.impl;

import lombok.extern.slf4j.Slf4j;
import model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CityRepository;
import repository.entity.City;
import service.ICityService;
import service.IValidateService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CityServiceImpl implements ICityService {

  private final IValidateService validateService;

  private final CityRepository cityRepository;

  @Autowired
  public CityServiceImpl(
      final IValidateService validateService, final CityRepository cityRepository) {
    this.validateService = validateService;
    this.cityRepository = cityRepository;
  }

  @Override
  public ResponseMessage getDescriptionCity(final String cityName) {
    log.info("Retrieving a city description by name {}", cityName);
    return Optional.ofNullable(cityRepository.findCity(cityName))
        .map(c -> new ResponseMessage(c.getDescription()))
        .orElse(new ResponseMessage(""));
  }

  @Override
  public List<City> getAllCities() {
    log.info("Getting all cities from the base");
    return new ArrayList<>(cityRepository.findAll());
  }

  @Override
  public void saveCity(final City city) throws IOException {
    log.info("saving the city  to the database, {}", city);
    validateService.isCityBeforeSaving(city);
    cityRepository.save(city);
  }

  @Override
  public void updateCity(final City city) throws IOException {
    log.info("update the city to the database, {}", city);
    validateService.isCityBeforeUpdate(city);
    cityRepository.save(city);
  }

  @Override
  public void deleteCity(final String city) {
    log.info("delete the city to the database, {}", city);
    cityRepository.deleteByCity(city);
  }
}
