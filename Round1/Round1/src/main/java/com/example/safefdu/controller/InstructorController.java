package com.example.safefdu.controller;

import com.example.safefdu.Dto.UserDto;
import com.example.safefdu.common.Constants;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.common.RoleConstant;
import com.example.safefdu.entity.Instructor;
import com.example.safefdu.entity.Student;
import com.example.safefdu.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping("/login")
    public ResultBody Login(@RequestBody UserDto userDto){

        Instructor instructor = instructorService.getById(userDto.getId());
        if (instructor == null) {
            return ResultBody.error(Constants.CODE_600, "不存在该用户");
        } else {
            return ResultBody.success(instructor);
        }

    }




}
