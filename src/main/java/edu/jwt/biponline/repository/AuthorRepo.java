package edu.jwt.biponline.repository;

import edu.jwt.biponline.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {

}
