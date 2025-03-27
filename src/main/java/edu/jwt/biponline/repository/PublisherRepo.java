package edu.jwt.biponline.repository;

import edu.jwt.biponline.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
}
