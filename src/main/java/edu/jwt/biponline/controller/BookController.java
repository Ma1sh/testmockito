package edu.jwt.biponline.controller;


import edu.jwt.biponline.entity.Author;
import edu.jwt.biponline.entity.Book;
import edu.jwt.biponline.response.BaseResponse;
import edu.jwt.biponline.response.DataResponse;
import edu.jwt.biponline.response.ListResponse;
import edu.jwt.biponline.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping("/allbook")
    public ResponseEntity<ListResponse<Book>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<Book>(true, "Список акторов", bookService.findAll()));
    }

     @GetMapping
    public ResponseEntity<DataResponse<Book>> by_id(@RequestParam Long id){
        return ResponseEntity.ok(
                new DataResponse<Book>(true, "Найдена следующая книга", bookService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Book>> save(@RequestBody Book book){
        return ResponseEntity.ok(
                new DataResponse<Book>(true,"Книга  сохранена", bookService.save(book)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Book book) {
        bookService.update(book);
        return ResponseEntity.ok(
                new BaseResponse(true, "Книга обновлена"));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        try{
            bookService.delete(id);
            return ResponseEntity.ok(
                    new BaseResponse(true,"Книга удален"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));
        }
    }
}
