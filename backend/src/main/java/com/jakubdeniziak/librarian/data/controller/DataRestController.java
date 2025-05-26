package com.jakubdeniziak.librarian.data.controller;

import com.jakubdeniziak.librarian.data.dto.DataRequest;
import com.jakubdeniziak.librarian.data.dto.DataResponse;
import com.jakubdeniziak.librarian.data.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/data")
@AllArgsConstructor
public class DataRestController implements DataController {

    private final DataService service;

    @Override
    @PutMapping("/all")
    public void importAll(@RequestBody DataRequest request) {
        service.saveAll(request);
    }

    @Override
    @GetMapping("/all")
    public DataResponse exportAll() {
        return service.findAll();
    }

}
