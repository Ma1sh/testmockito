package edu.jwt.biponline.repository;

import edu.jwt.biponline.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {
}
