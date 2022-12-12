package com.example.safefdu.controller;

import com.example.safefdu.service.ExitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exitrecord")
public class ExitRecordController {
    @Autowired
    private ExitRecordService exitRecordService;
}
