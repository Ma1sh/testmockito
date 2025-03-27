package edu.jwt.biponline.service;


import edu.jwt.biponline.entity.Book;
import edu.jwt.biponline.repository.BookRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService bookService;

    /**
     * Метод для тестирования findById
     * Параметры:
     *
     * @param id   - идентификатор книги
     * @param year - год книги
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, 1455",
            "2, 1555",
            "3, 1885",
            "4, 1744",
            "5, 1738"
    })
    void findById(Long id, String year) {

        Book books = new Book(id, year);

        when(bookRepo.findById(id)).thenReturn(Optional.of(books));

        Optional<Book> result = bookRepo.findById(id);
        System.out.printf("Результат: %s \n", result.get().getYear());

        assertTrue(result.isPresent());
        assertEquals(year, result.get().getYear());
        verify(bookRepo, times(1)).findById(id);
    }


    /**
     * Метод для тестирования сохранения кинги в базу
     *
     * @param id   - идентификатор книги
     * @param year - год книги
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, 1455",
            "2, 1555",
            "3, 1885",
            "4, 1744",
            "5, 1738"
    })
    void save(Long id, String year) {
        Book books = new Book(id, year);

        when(bookRepo.save(books)).thenReturn(books);

        Book result = bookService.save(books);

        assertEquals(result, books);
    }

    /**
     * Метод для тестирования findAll
     * Параметры:
     *
     * @param id   - идентификатор книги
     * @param year - год книги
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, 1455",
            "2, 1555",
            "3, 1885",
            "4, 1744",
            "5, 1738"
    })
    void findAll(Long id, String year) {
        List<Book> books = Arrays.asList(new Book(id, year));

        when(bookService.findAll()).thenReturn(books);

        List<Book> result = bookService.findAll();
        System.out.printf("Результат: %s \n", result.get(0).getYear());

        assertEquals(year, result.get(0).getYear());
        verify(bookRepo, times(1)).findAll();

    }

    /**
     * Метод для тестирования update
     * Параметры:
     *
     * @param id   - идентификатор книги
     * @param year - год книги
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, 1455",
            "2, 1555",
            "3, 1885",
            "4, 1744",
            "5, 1738"
    })
    void update(Long id, String year) {
        Book books = new Book(id, year);
        bookService.update(books);
        verify(bookRepo, times(1)).save(books);
    }

    /**
     * Метод для тестирования delete
     * Параметры:
     * @param id - идентификатор книги
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, 1455",
            "2, 1555",
            "3, 1885",
            "4, 1744",
            "5, 1738"
    })
    void delete(Long id) {
        bookService.delete(id);
        verify(bookRepo, times(1)).deleteById(id);
    }
}