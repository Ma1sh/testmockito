package edu.jwt.biponline.service;


import edu.jwt.biponline.entity.Publisher;
import edu.jwt.biponline.repository.PublisherRepo;
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
 * Тестирование бизнес-логики работы с издателями в системе
 */

@ExtendWith(MockitoExtension.class)
class PublisherServiceTest {

    @Mock
    private PublisherRepo publisherRepo;

    @InjectMocks
    private PublisherService publisherService;

    /**
     * Метод для тестирования findById
     * Параметры:
     * @param id - идентификатор издателя
     * @param title - наименование издателя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Литрес",
            "2, Эксмо",
            "3, АСТ",
            "4, Лабиринт",
            "5, Алтапресс"
    })
    void findById(Long id, String title ) {

        Publisher publishers = new Publisher(id, title);

        when(publisherRepo.findById(id)).thenReturn(Optional.of(publishers));

        Optional<Publisher> result = publisherRepo.findById(id);
        System.out.printf("Результат: %s \n", result.get().getTitle());

        assertTrue(result.isPresent());
        assertEquals(title, result.get().getTitle());
        verify(publisherRepo, times(1)).findById(id);
    }


    /**
     * Метод для тестирования сохранения издателя в базу
     * @param id - идентификатор издателя
     * @param title - наименование издателя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Литрес",
            "2, Эксмо",
            "3, АСТ",
            "4, Лабиринт",
            "5, Алтапресс"
    })
    void save(Long id, String title) {
        Publisher publishers = new Publisher(id, title);

        when(publisherRepo.save(publishers)).thenReturn(publishers);

        Publisher result = publisherRepo.save(publishers);

        assertEquals(result, publishers);
    }

    /**
     * Метод для тестирования findAll
     * Параметры:
     * @param id - идентификатор издателя
     * @param title - наименование издателя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Литрес",
            "2, Эксмо",
            "3, АСТ",
            "4, Лабиринт",
            "5, Алтапресс"
    })
    void findAll(Long id, String title){
        List<Publisher> publishers = Arrays.asList(new Publisher(id, title));

        when(publisherRepo.findAll()).thenReturn(publishers);

        List<Publisher> result = publisherService.findAll();
        System.out.printf("Результат: %s \n", result.get(0).getTitle());

        assertEquals(title, result.get(0).getTitle());
        verify(publisherRepo, times(1)).findAll();

    }

    /**
     * Метод для тестирования update
     * Параметры:
     * @param id - идентификатор издателя
     * @param title - наименование издателя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Литрес",
            "2, Эксмо",
            "3, АСТ",
            "4, Лабиринт",
            "5, Алтапресс"
    })
    void update(Long id, String title){
        Publisher publishers = new Publisher(id, title);
        publisherService.update(publishers);
        verify(publisherRepo, times(1)).save(publishers);
    }

    /**
     * Метод для тестирования delete
     * Параметры:
     * @param id - идентификатор издателя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Литрес",
            "2, Эксмо",
            "3, АСТ",
            "4, Лабиринт",
            "5, Алтапресс"
    })
    void delete(Long id){
        publisherService.delete(id);
        verify(publisherRepo, times(1)).deleteById(id);
    }

}
