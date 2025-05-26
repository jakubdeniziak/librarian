package com.jakubdeniziak.librarian.data.controller;

import com.jakubdeniziak.librarian.data.dto.DataRequest;
import com.jakubdeniziak.librarian.data.dto.DataResponse;

public interface DataController {

    void importAll(DataRequest request);
    DataResponse exportAll();

}
