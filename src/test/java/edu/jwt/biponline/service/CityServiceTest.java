package edu.jwt.biponline.service;

import edu.jwt.biponline.entity.City;
import edu.jwt.biponline.repository.CityRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Тестирование бизнес-логики работы с городами в системе
 */

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepo cityRepo;

    @InjectMocks
    private CityService cityService;

    /**
     * Метод для тестирования findById
     * Параметры:
     * @param id - идентификатор города
     * @param title - название города
     */
    @ParameterizedTest
    @CsvSource(value = {
        "1, Москва",
        "2, Санкт-Петербург",
        "3, Омск",
        "4, Тула",
        "5, Владивосток"
    })
    void findById(Long id, String title ) {

        City citys = new City(id, title);

        when(cityRepo.findById(id)).thenReturn(Optional.of(citys));

        Optional<City> result = cityRepo.findById(id);
        System.out.printf("Результат: %s \n", result.get().getTitle());

        assertTrue(result.isPresent());
        assertEquals(title, result.get().getTitle());
        verify(cityRepo, times(1)).findById(id);
    }


    /**
     * Метод для тестирования сохранения города в базу
     * @param id - идентификатор города
     * @param title - название города
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Москва",
            "2, Санкт-Петербург",
            "3, Омск",
            "4, Тула",
            "5, Владивосток"
    })
    void save(Long id, String title) {
        City citys = new City(id, title);

        when(cityRepo.save(citys)).thenReturn(citys);

        City result = cityService.save(citys);

        assertEquals(result, citys);
    }

    /**
     * Метод для тестирования findAll
     * Параметры:
     * @param id - идентификатор города
     * @param title - название города
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Москва",
            "2, Санкт-Петербург",
            "3, Омск",
            "4, Тула",
            "5, Владивосток"
    })
    void findAll(Long id, String title){
        List<City> citys = Arrays.asList(new City(id, title));

        when(cityService.findAll()).thenReturn(citys);

        List<City> result = cityService.findAll();
        System.out.printf("Результат: %s \n", result.get(0).getTitle());

        assertEquals(title, result.get(0).getTitle());
        verify(cityRepo, times(1)).findAll();

    }

    /**
     * Метод для тестирования update
     * Параметры:
     * @param id - идентификатор города
     * @param title - название города
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Москва",
            "2, Санкт-Петербург",
            "3, Омск",
            "4, Тула",
            "5, Владивосток"
    })
    void update(Long id, String title){
        City citys = new City(id, title);
        cityService.update(citys);
        verify(cityRepo, times(1)).save(citys);
    }

    /**
     * Метод для тестирования delete
     * Параметры:
     * @param id - идентификатор города
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Москва",
            "2, Санкт-Петербург",
            "3, Омск",
            "4, Тула",
            "5, Владивосток"
    })
    void delete(Long id){
        cityService.delete(id);
        verify(cityRepo, times(1)).deleteById(id);
    }

}
