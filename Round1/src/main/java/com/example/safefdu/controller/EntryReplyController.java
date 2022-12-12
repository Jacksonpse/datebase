package com.example.safefdu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.safefdu.annoation.StuIdCheck;
import com.example.safefdu.common.Constants;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.entity.EntryReply;
import com.example.safefdu.entity.ExitApply;
import com.example.safefdu.service.EntryReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.scanner.Constant;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("entryreply")
public class EntryReplyController {
    @Autowired
    private EntryReplyService entryReplyService;
    @StuIdCheck
    @GetMapping("/get/{id}")
    public ResultBody getAll(@PathVariable String id){
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntryReply::getStuId,id);
        List<EntryReply> list = entryReplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @GetMapping("/get/{id}/{count}")
    public ResultBody getAllCount(@PathVariable String id , @PathVariable int count){
        long beginTime = System.currentTimeMillis() - 86400000L * count;
        List<EntryReply> list = entryReplyService.getEntryReply(true,null,beginTime,Constants.ALL);
        return ResultBody.success(list);

    }

    @StuIdCheck
    @GetMapping("/getaccept/{id}")
    public ResultBody getAccept(@PathVariable String id){
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntryReply::getStuId,id);
        queryWrapper.eq(EntryReply::getCheckStage, Constants.TEACHERACCEPT);
        List<EntryReply> list = entryReplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @StuIdCheck
    @GetMapping("/getrefuse/{id}")
    public ResultBody getRefuse(@PathVariable String id){
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntryReply::getStuId,id);
        queryWrapper.eq(EntryReply::getCheckStage, Constants.CLASSREFUSE)
                .or().eq(EntryReply::getCheckStage,Constants.TEACHERREFUSE);
        List<EntryReply> list = entryReplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @StuIdCheck
    @GetMapping("/getundisposed/{id}")
    public ResultBody getUndisposed(@PathVariable String id){
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntryReply::getStuId,id);
        queryWrapper.eq(EntryReply::getCheckStage, Constants.UNDISPOSED);
        List<EntryReply> list = entryReplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @PostMapping("/insert")
    public ResultBody insert(@RequestBody EntryReply entryReply){
        entryReply.setApplyTime(new Timestamp(System.currentTimeMillis()));
        entryReplyService.save(entryReply);
        return ResultBody.success();
    }

    @GetMapping("undisposed/{id}/{n}")
    public ResultBody getUndisposed(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(false,id,beginTime,Constants.UNDISPOSED);
        return ResultBody.success(list);
    }

    @GetMapping("/undisposed/{n}")
    public ResultBody getUndisposedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(true,null,beginTime,Constants.UNDISPOSED);
        return ResultBody.success(list);
    }

    @GetMapping("/accepted/{n}")
    public ResultBody getAcceptedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(true,null,beginTime,Constants.TEACHERACCEPT);
        return ResultBody.success(list);
    }

    @GetMapping("accepted/{id}/{n}")
    public ResultBody getAccepted(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(false,id,beginTime,Constants.TEACHERACCEPT);
        return ResultBody.success(list);
    }

    @GetMapping("/refused/{n}")
    public ResultBody getRefusedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(true,null,beginTime,Constants.REFUSE);
        return ResultBody.success(list);
    }

    @GetMapping("refused/{id}/{n}")
    public ResultBody getRefused(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(false,id,beginTime,Constants.REFUSE);
        return ResultBody.success(list);
    }

}
