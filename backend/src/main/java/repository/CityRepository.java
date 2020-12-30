package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.entity.City;

/** Repository for working with the city table */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

  /**
   * This method is used to get a description of the city
   *
   * @param city city name
   * @return city description
   */
  @Query(value = "select * from city c where lower(c.city) = lower(:city)", nativeQuery = true)
  City findCity(@Param("city") String city);

  /**
   * This method is used to remove information about a city
   *
   * @param city city name
   */
  @Transactional
  @Modifying(clearAutomatically = true)
  @Query(value = "delete from city c where lower(c.city) = lower(:city)", nativeQuery = true)
  void deleteByCity(@Param("city") String city);
}
