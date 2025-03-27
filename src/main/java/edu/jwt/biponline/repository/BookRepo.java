package edu.jwt.biponline.repository;

import edu.jwt.biponline.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
