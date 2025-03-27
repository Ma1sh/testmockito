package edu.jwt.biponline.service;

import edu.jwt.biponline.entity.Genre;
import edu.jwt.biponline.repository.GenreRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepo genreRepo;

    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }
    public List<Genre> findAll() {
        return genreRepo.findAll();
    }
    public Optional<Genre> findById(Long id){
        return genreRepo.findById(id);
    }
    public Genre save(Genre data){
        return genreRepo.save(data);
    }
    public void update(Genre data){
        genreRepo.save(data);
    }
    public void delete(long id ) {
        genreRepo.deleteById(id);
    }
}
