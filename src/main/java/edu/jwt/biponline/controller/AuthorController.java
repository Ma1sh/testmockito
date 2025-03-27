package edu.jwt.biponline.controller;


import edu.jwt.biponline.entity.Author;
import edu.jwt.biponline.response.BaseResponse;
import edu.jwt.biponline.response.DataResponse;
import edu.jwt.biponline.response.ListResponse;
import edu.jwt.biponline.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("api/v1/author")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/allauthor")
    public ResponseEntity<ListResponse<Author>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<Author>(true, "Список акторов", authorService.findAll()));
    }

    @GetMapping
    public ResponseEntity<DataResponse<Author>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Author>(true, "Найден следующий автор", authorService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Author>> save(@RequestBody Author author) {
        return ResponseEntity.ok(
                new DataResponse<Author>(true, "Автор сохранен", authorService.save(author)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Author author) {
        authorService.update(author);
        return ResponseEntity.ok(
                new BaseResponse(true, "Автор обновлен"));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        try{
            authorService.delete(id);
            return ResponseEntity.ok(
                    new BaseResponse(true,"Автор удален"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));
        }
    }



}
