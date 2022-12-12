package com.example.safefdu.controller;

import com.example.safefdu.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("campus")
public class CampusController{
    @Autowired
    private CampusService campusService;
}
