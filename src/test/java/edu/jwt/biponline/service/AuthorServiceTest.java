package edu.jwt.biponline.service;

import edu.jwt.biponline.entity.Author;
import edu.jwt.biponline.repository.AuthorRepo;
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
 * Тестирование бизнес-логики работы с авторами в системе
 */

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepo authorRepo;

    @InjectMocks
    private AuthorService authorService;

    /**
     * Метод для тестирования findById :)
     * Параметры:
     * @param id - идентификатор пользователя
     * @param name - имя пользователя
     * @param surname - фамилия пользователя
     * @param lastname - отчество пользователя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Иванович, Иван, Иванов",
            "2, Петрович, Иван, Иванов",
            "3, Владленович, Иван, Иванов",
            "4, Головач, Лена, Иванов"
    })
    void findById(Long id, String name, String lastname, String surname ) {

        Author author = new Author(id, lastname, name, surname);

        when(authorRepo.findById(id)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.findById(id);
        System.out.printf("Результат: %s %s %s", result.get().getName(), result.get().getSurname(), result.get().getLastname());

        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
        verify(authorRepo, times(1)).findById(id);
    }


    /**
     * Метод для тестирования сохранения пользователя в базу ;)
     * @param id - идентификатор пользователя
     * @param name - имя пользователя
     * @param surname - фамилия пользователя
     * @param lastname - отчество пользователя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Иванович, Иван, Иванов",
            "2, Петрович, Иван, Иванов",
            "3, Владленович, Иван, Иванов",
            "4, Головач, Лена, Иванов"
    })
    void save(Long id, String name, String surname, String lastname) {
        Author author = new Author(id, lastname, name, surname);

        when(authorRepo.save(author)).thenReturn(author);

        Author result = authorService.save(author);

        assertEquals(result, author);
    }

    /**
     * Метод для тестирования findAll :)
     * Параметры:
     * @param id - идентификатор пользователя
     * @param name - имя пользователя
     * @param surname - фамилия пользователя
     * @param lastname - отчество пользователя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Иванович, Иван, Иванов",
            "2, Петрович, Иван, Иванов",
            "3, Владленович, Иван, Иванов",
            "4, Головач, Лена, Иванов"
    })
    void findAll(Long id, String name, String surname, String lastname){
        List<Author> authors = Arrays.asList(new Author(id, lastname, name, surname));

        when(authorRepo.findAll()).thenReturn(authors);

        List<Author> result = authorService.findAll();
        System.out.printf("Результат: %s %s %s \n", result.get(0).getName(), result.get(0).getSurname(), result.get(0).getLastname());

        assertEquals(name, result.get(0).getName());
        assertEquals(surname, result.get(0).getSurname());
        assertEquals(lastname, result.get(0).getLastname());
        verify(authorRepo, times(1)).findAll();

    }

    /**
     * Метод для тестирования update :>)
     * Параметры:
     * @param id - идентификатор пользователя
     * @param name - имя пользователя
     * @param surname - фамилия пользователя
     * @param lastname - отчество пользователя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Иванович, Иван, Иванов",
            "2, Петрович, Иван, Иванов",
            "3, Владленович, Иван, Иванов",
            "4, Головач, Лена, Иванов"
    })
    void update(Long id, String name, String surname, String lastname){
        Author author = new Author(id, surname,name, lastname);
        authorService.update(author);
        verify(authorRepo, times(1)).save(author);
    }

    /**
     * Метод для тестирования delete :>)
     * Параметры:
     * @param id - идентификатор пользователя
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Иванович, Иван, Иванов",
            "2, Петрович, Иван, Иванов",
            "3, Владленович, Иван, Иванов",
            "4, Головач, Лена, Иванов"
    })
    void delete(Long id){
        authorService.delete(id);
        verify(authorRepo, times(1)).deleteById(id);
    }

}
