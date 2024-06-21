package com.example.bookapiver2.controller;

import com.example.bookapiver2.service.BatchProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2.2")
public class BatchProcessingController {
    @Autowired
    private BatchProcessingService batchProcessingService;

    @GetMapping("/batch-insert")
    public String batchInsert(){
        batchProcessingService.batchInsertBooks();
        return "Batch insert successfully";
    }
}
