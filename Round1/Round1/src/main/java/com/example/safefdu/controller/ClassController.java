package com.example.safefdu.controller;

import com.example.safefdu.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("class")
public class ClassController {
    @Autowired
    private ClassService classService;
}
