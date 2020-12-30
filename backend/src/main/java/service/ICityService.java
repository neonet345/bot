package service;

import model.ResponseMessage;
import repository.entity.City;

import java.io.IOException;
import java.util.List;

/** This service is designed to process information by city */
public interface ICityService {

  /**
   * This method is used to get a description of the passed city
   *
   * @param city city city name
   * @return city description
   */
  ResponseMessage getDescriptionCity(final String city);

  /**
   * This method is used to get all cities
   *
   * @return city list
   */
  List<City> getAllCities();

  /**
   * This method is used to store information about a city
   *
   * @param city city information about the city
   * @throws IOException IOException throws an exception if an error occurs while saving
   */
  void saveCity(final City city) throws IOException;

  /**
   * This method updates city information
   *
   * @param city city information about the city
   * @throws IOException IOException throws an exception if an error occurs while update
   */
  void updateCity(final City city) throws IOException;

  /**
   * This method is used to remove information about a city
   *
   * @param city information about the city
   */
  void deleteCity(final String city);
}
