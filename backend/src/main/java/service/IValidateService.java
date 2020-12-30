package service;

import repository.entity.City;

import java.io.IOException;

/** The service is designed to check incoming data before accessing the database. */
public interface IValidateService {
  /**
   * This method will check if there is such a city in the database. Throws an exception if a record
   * is found
   *
   * @param city information
   * @return true if no entry was found
   * @throws IOException if a record is found
   */
  boolean isCityBeforeSaving(City city) throws IOException;

  /**
   * This method will check if there is such a city in the database. Throws an exception if no entry
   * is found
   *
   * @param city information
   * @return true if entry was found
   * @throws IOException if no entry was found
   */
  boolean isCityBeforeUpdate(City city) throws IOException;
}
