package edu.jwt.biponline.controller;

import edu.jwt.biponline.entity.Book;
import edu.jwt.biponline.entity.Publisher;
import edu.jwt.biponline.response.BaseResponse;
import edu.jwt.biponline.response.DataResponse;
import edu.jwt.biponline.response.ListResponse;
import edu.jwt.biponline.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/publisher")
@AllArgsConstructor
public class PublisherController {
    private PublisherService publisherService;

    @GetMapping("/allpublisher")
    public ResponseEntity<ListResponse<Publisher>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<Publisher>(true, "Список акторов", publisherService.findAll()));
    }
    @GetMapping
    public ResponseEntity<DataResponse<Publisher>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<Publisher>(true,"Найдены следующии издатели",publisherService.findById(id).orElseThrow() )
        );
    }
    @PostMapping
    public ResponseEntity<DataResponse<Publisher>> save(@RequestParam Publisher publisher) {
        return ResponseEntity.ok(
                new DataResponse<Publisher>(true,"Издания сохранены",publisherService.save(publisher) )
        );
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestParam Publisher publisher) {
    publisherService.update(publisher);
    return ResponseEntity.ok(
            new BaseResponse(true,"Издания  обновлены"));
    }
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        try{
            publisherService.delete(id);
            return ResponseEntity.ok(
                    new BaseResponse(true,"Издание удалено"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));
        }
    }

}
