package edu.jwt.biponline.service;

import edu.jwt.biponline.entity.Author;
import edu.jwt.biponline.repository.AuthorRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<Author> findAll() {
        return authorRepo.findAll();
    }

    public Optional<Author> findById(long id) {
        return authorRepo.findById(id);
    }

    public Author save(Author data) {
        return authorRepo.save(data);
    }

    public void update (Author data) {
        authorRepo.save(data);
    }

    public void delete(long id) {
        authorRepo.deleteById(id);
    }
}
