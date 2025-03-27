package edu.jwt.biponline.controller;


import edu.jwt.biponline.entity.Book;
import edu.jwt.biponline.entity.City;
import edu.jwt.biponline.response.BaseResponse;
import edu.jwt.biponline.response.DataResponse;
import edu.jwt.biponline.response.ListResponse;
import edu.jwt.biponline.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/city")
@AllArgsConstructor
public class CityController {
    private CityService cityService;

    @GetMapping("/allcity")
    public ResponseEntity<ListResponse<City>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<City>(true, "Список акторов", cityService.findAll()));
    }
    @GetMapping
    public ResponseEntity<DataResponse<City>> by_id(@RequestParam Long id){
        return ResponseEntity.ok(
                new DataResponse<City>(true, "Найден следующий город", cityService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<City>> save(@RequestBody City city){
        return ResponseEntity.ok(
                new DataResponse<City>(true,"Город сохранен", cityService.save(city)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody City city) {
        cityService.update(city);
        return ResponseEntity.ok(
                new BaseResponse(true, "Город обновлен"));
    }
    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        try{
            cityService.delete(id);
            return ResponseEntity.ok(
                    new BaseResponse(true,"Город удален"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));
        }
    }

}
