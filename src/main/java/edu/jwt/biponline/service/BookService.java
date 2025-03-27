package edu.jwt.biponline.service;


import edu.jwt.biponline.entity.Author;
import edu.jwt.biponline.entity.Book;
import edu.jwt.biponline.repository.BookRepo;
import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Optional<Book> findById(long id) {
        return bookRepo.findById(id);
    }

    public Book save(Book data) {
        return bookRepo.save(data);
    }

    public void update(Book data) {
        bookRepo.save(data);
    }
   public void delete(long id) {
        bookRepo.deleteById(id);
   }
}
