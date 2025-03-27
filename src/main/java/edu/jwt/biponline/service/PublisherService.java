package edu.jwt.biponline.service;

import edu.jwt.biponline.entity.Publisher;
import edu.jwt.biponline.repository.PublisherRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherService(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }
    public List<Publisher> findAll() {
        return publisherRepo.findAll();
    }
    public Optional<Publisher> findById(Long id) {
        return publisherRepo.findById(id);
    }
    public Publisher save(Publisher data) {
        return publisherRepo.save(data);
    }
    public void update(Publisher data) {
        publisherRepo.save(data);
    }
    public void delete(long id ) {
        publisherRepo.deleteById(id);
    }
}
