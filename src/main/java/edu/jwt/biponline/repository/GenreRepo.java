package edu.jwt.biponline.repository;

import edu.jwt.biponline.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}
