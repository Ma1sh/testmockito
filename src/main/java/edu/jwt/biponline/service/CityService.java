package edu.jwt.biponline.service;


import edu.jwt.biponline.entity.City;
import edu.jwt.biponline.repository.CityRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepo cityRepo;

    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public List<City> findAll() {
        return cityRepo.findAll();
    }

    public Optional<City> findById(Long id) {
        return cityRepo.findById(id);
    }
    public City save(City data) {
        return cityRepo.save(data);
    }
    public void update(City data) {
        cityRepo.save(data);
    }
    public void delete(long id ) {
        cityRepo.deleteById(id);
    }
}
