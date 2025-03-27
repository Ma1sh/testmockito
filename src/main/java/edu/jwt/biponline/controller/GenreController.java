package edu.jwt.biponline.controller;

import edu.jwt.biponline.entity.Book;
import edu.jwt.biponline.entity.Genre;
import edu.jwt.biponline.response.BaseResponse;
import edu.jwt.biponline.response.DataResponse;
import edu.jwt.biponline.response.ListResponse;
import edu.jwt.biponline.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/genre")
@AllArgsConstructor
public class GenreController {
    private GenreService genreService;

    @GetMapping("/allgenre")
    public ResponseEntity<ListResponse<Genre>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<Genre>(true, "Список акторов", genreService.findAll()));

    }

    @GetMapping
    public ResponseEntity<DataResponse<Genre>> by_id(@RequestParam Long id){
        return ResponseEntity.ok(
                new DataResponse<Genre>(true, "Найден следующий жанр", genreService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Genre>> save(@RequestBody Genre genre){
        return ResponseEntity.ok(
                new DataResponse<Genre>(true,"Жанр сохранен", genreService.save(genre)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Genre genre) {
        genreService.update(genre);
        return ResponseEntity.ok(
                new BaseResponse(true, "Жанр обновлен"));
    }
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        try{
            genreService.delete(id);
            return ResponseEntity.ok(
                    new BaseResponse(true,"Жанр удален"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));
        }
    }
}
