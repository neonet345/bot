package controller;

import lombok.extern.slf4j.Slf4j;
import model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.entity.City;
import service.ICityService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/** The controller is designed to handle city-related requests */
@Slf4j
@RestController
public class CityController {

  private final ICityService cityService;

  @Autowired
  public CityController(final ICityService cityService) {
    this.cityService = cityService;
  }

  /**
   * This method is used to get a description of the passed city
   *
   * @param city city name
   * @return city description
   */
  @GetMapping(value = "city/{city}/getDescription", produces = "application/json")
  public @ResponseBody ResponseMessage getCityDescription(final @PathVariable String city) {
    log.info("request get city description");
    return cityService.getDescriptionCity(city);
  }

  /**
   * This method is used to get all cities
   *
   * @return city list
   */
  @GetMapping(value = "/getAllCities", produces = "application/json")
  public @ResponseBody List<City> getAllCity() {
    log.info("request for get all city");
    return cityService.getAllCities();
  }

  /**
   * This method is used to get all cities
   *
   * @return city list
   */
  @GetMapping(value = "/getAllNameCities", produces = "application/json")
  public @ResponseBody List<String> getAllNameCity() {
    log.info("request for get all city");
    return cityService.getAllNameCities();
  }

  /**
   * This method is used to store information about a city
   *
   * @param city information about the city
   * @throws IOException throws an exception if an error occurs while saving
   */
  @PostMapping(path = "/addCity", consumes = "application/json", produces = "application/json")
  public @ResponseBody ResponseMessage addCity(final @Valid @RequestBody City city)
      throws IOException {
    log.info("city save request");
    cityService.saveCity(city);
    return new ResponseMessage("City added successfully!");
  }

  /**
   * This method updates city information
   *
   * @param city information about the city
   * @throws IOException throws an exception if an error occurs while update
   */
  @PostMapping(path = "/updateCity", consumes = "application/json", produces = "application/json")
  public @ResponseBody ResponseMessage updateCity(final @Valid @RequestBody City city)
      throws IOException {
    log.info("city update request");
    cityService.updateCity(city);
    return new ResponseMessage("City update successfully!");
  }

  /**
   * This method is used to remove information about a city
   *
   * @param city information about the city
   */
  @DeleteMapping(value = "/deleteCity/{city}", produces = "application/json")
  public @ResponseBody ResponseMessage deleteCity(final @Valid @PathVariable String city) {
    log.info("city delete request");
    cityService.deleteCity(city);
    return new ResponseMessage("City delete successfully!");
  }
}
