package com.jakubdeniziak.librarian.data.controller;

import com.jakubdeniziak.librarian.data.dto.DataRequest;
import com.jakubdeniziak.librarian.data.dto.DataResponse;
import com.jakubdeniziak.librarian.data.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/data")
@AllArgsConstructor
public class DataRestController implements DataController {

    private final DataService service;

    @Override
    @PutMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public void importAll(@RequestBody DataRequest request) {
        service.saveAll(request);
    }

    @Override
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse exportAll() {
        return service.findAll();
    }

}
