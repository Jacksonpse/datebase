package com.example.safefdu.controller;

import com.example.safefdu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
}
