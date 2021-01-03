package service.impl;

import repository.CityRepository;

import java.io.IOException;
import java.util.Objects;

public class AbstractValidateService {
  private final CityRepository cityRepository;

  AbstractValidateService(final CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  protected boolean isNonExistsRecordCityByName(final String cityName) throws IOException {
    if (Objects.nonNull(cityRepository.findCity(cityName))) {
      throw new IOException("Entry already exists in the database");
    } else return Boolean.TRUE;
  }

  protected boolean isExistsRecordCityById(final Long id) throws IOException {
    if (cityRepository.existsById(id)) {
      return Boolean.TRUE;
    } else throw new IOException("The entry for the specified id does not exist");
  }
}
