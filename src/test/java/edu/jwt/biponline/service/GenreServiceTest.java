package edu.jwt.biponline.service;

import edu.jwt.biponline.entity.Genre;
import edu.jwt.biponline.repository.GenreRepo;
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
 * Тестирование бизнес-логики работы с жанрами в системе
 */

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreRepo genreRepo;

    @InjectMocks
    private GenreService genreService;

    /**
     * Метод для тестирования findById
     * Параметры:
     * @param id - идентификатор жанра
     * @param title - Название жанра
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Фантастика",
            "2, Классика",
            "3, Фэнтези",
            "4, Ужасы",
            "5, Детектив"
    })
    void findById(Long id, String title ) {

        Genre genres = new Genre(id, title);

        when(genreRepo.findById(id)).thenReturn(Optional.of(genres));

        Optional<Genre> result = genreRepo.findById(id);
        System.out.printf("Результат: %s \n", result.get().getTitle());

        assertTrue(result.isPresent());
        assertEquals(title, result.get().getTitle());
        verify(genreRepo, times(1)).findById(id);
    }


    /**
     * Метод для тестирования сохранения жанра в базу
     * @param id - идентификатор жанра
     * @param title - название жанра
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Фантастика",
            "2, Классика",
            "3, Фэнтези",
            "4, Ужасы",
            "5, Детектив"
    })
    void save(Long id, String title) {
        Genre genres = new Genre(id, title);

        when(genreRepo.save(genres)).thenReturn(genres);

        Genre result = genreRepo.save(genres);

        assertEquals(result, genres);
    }

    /**
     * Метод для тестирования findAll
     * Параметры:
     * @param id - идентификатор жанра
     * @param title - название жанра
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Фантастика",
            "2, Классика",
            "3, Фэнтези",
            "4, Ужасы",
            "5, Детектив"
    })
    void findAll(Long id, String title){
        List<Genre> genres = Arrays.asList(new Genre(id, title));

        when(genreService.findAll()).thenReturn(genres);

        List<Genre> result = genreService.findAll();
        System.out.printf("Результат: %s \n", result.get(0).getTitle());

        assertEquals(title, result.get(0).getTitle());
        verify(genreRepo, times(1)).findAll();

    }

    /**
     * Метод для тестирования update
     * Параметры:
     * @param id - идентификатор жанра
     * @param title - название жанра
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Фантастика",
            "2, Классика",
            "3, Фэнтези",
            "4, Ужасы",
            "5, Детектив"
    })
    void update(Long id, String title){
        Genre genres = new Genre(id, title);
        genreService.update(genres);
        verify(genreRepo, times(1)).save(genres);
    }

    /**
     * Метод для тестирования delete
     * Параметры:
     * @param id - идентификатор жанра
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Фантастика",
            "2, Классика",
            "3, Фэнтези",
            "4, Ужасы",
            "5, Детектив"
    })
    void delete(Long id){
        genreService.delete(id);
        verify(genreRepo, times(1)).deleteById(id);
    }

}
