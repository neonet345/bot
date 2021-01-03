package service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CityRepository;
import repository.entity.City;
import service.IValidateService;

import java.io.IOException;

@Slf4j
@Service
public class ValidateServiceImpl extends AbstractValidateService implements IValidateService {

  @Autowired
  public ValidateServiceImpl(final CityRepository cityRepository) {
    super(cityRepository);
  }

  @Override
  public boolean isCityBeforeSaving(final City city) throws IOException {
    log.info("check the city before saving , {}", city);
    return isNonExistsRecordCityByName(city.getCity());
  }

  @Override
  public boolean isCityBeforeUpdate(final City city) throws IOException {
    log.info("check the city before update , {}", city);
    return isExistsRecordCityById(city.getId());
  }
}
