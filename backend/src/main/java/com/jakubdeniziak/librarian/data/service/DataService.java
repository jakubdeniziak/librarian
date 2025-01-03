package com.jakubdeniziak.librarian.data.service;

import com.jakubdeniziak.librarian.data.dto.DataRequest;
import com.jakubdeniziak.librarian.data.dto.DataResponse;

public interface DataService {

    void saveAll(DataRequest request);
    DataResponse findAll();

}
