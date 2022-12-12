package com.example.safefdu.controller;

import com.example.safefdu.service.EntryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("entryrecord")
public class EntryRecordController {
    @Autowired
    private EntryRecordService entryRecordService;
}
