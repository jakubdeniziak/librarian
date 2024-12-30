package com.jakubdeniziak.librarian.data.controller;

import com.jakubdeniziak.librarian.data.dto.DataResponse;
import com.jakubdeniziak.librarian.data.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
@AllArgsConstructor
public class DataRestController implements DataController {

    private final DataService service;

    @Override
    @GetMapping("/all")
    public DataResponse exportAll() {
        return service.findAll();
    }

}
