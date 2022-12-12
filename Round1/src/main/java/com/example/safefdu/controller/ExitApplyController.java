package com.example.safefdu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.safefdu.annoation.StuIdCheck;
import com.example.safefdu.common.Constants;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.entity.EntryReply;
import com.example.safefdu.entity.ExitApply;
import com.example.safefdu.service.ExitApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/exitapply")
public class ExitApplyController {
    @Autowired
    private ExitApplyService exitApplyService;

    @StuIdCheck
    @GetMapping("/get/{id}")
    public ResultBody getAll(@PathVariable String id){
        LambdaQueryWrapper<ExitApply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExitApply::getStuId,id);
        List<ExitApply> list = exitApplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @StuIdCheck
    @GetMapping("/getaccept/{id}")
    public ResultBody getAccept(@PathVariable String id){
        LambdaQueryWrapper<ExitApply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExitApply::getStuId,id);
        queryWrapper.eq(ExitApply::getCheckStage, Constants.TEACHERACCEPT);
        List<ExitApply> list = exitApplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @StuIdCheck
    @GetMapping("/getrefuse/{id}")
    public ResultBody getRefuse(@PathVariable String id){
        LambdaQueryWrapper<ExitApply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExitApply::getStuId,id);
        queryWrapper.eq(ExitApply::getCheckStage, Constants.CLASSREFUSE)
                .or().eq(ExitApply::getCheckStage,Constants.TEACHERREFUSE);
        List<ExitApply> list = exitApplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @StuIdCheck
    @GetMapping("/getundisposed/{id}")
    public ResultBody getUndisposed(@PathVariable String id){
        LambdaQueryWrapper<ExitApply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExitApply::getStuId,id);
        queryWrapper.eq(ExitApply::getCheckStage, Constants.UNDISPOSED);
        List<ExitApply> list = exitApplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @PostMapping("/insert")
    public ResultBody insert(@RequestBody ExitApply exitApply){
        if(exitApply.getLeaveDate().getTime() > exitApply.getReturnDate().getTime())
            return ResultBody.error(Constants.CODE_600,"离开日期必须不晚于返回日期");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        exitApply.setApplyTime(timestamp);
        exitApplyService.save(exitApply);
        return ResultBody.success();
    }

    @GetMapping("undisposed/{id}/{n}")
    public ResultBody getUndisposed(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<ExitApply> list = exitApplyService.getExitApply(false,id,beginTime,Constants.UNDISPOSED);
        return ResultBody.success(list);
    }

    @GetMapping("/undisposed/{n}")
    public ResultBody getUndisposedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<ExitApply> list = exitApplyService.getExitApply(true,null,beginTime,Constants.UNDISPOSED);
        return ResultBody.success(list);
    }

    @GetMapping("accepted/{id}/{n}")
    public ResultBody getAccepted(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<ExitApply> list = exitApplyService.getExitApply(false,id,beginTime,Constants.TEACHERACCEPT);
        return ResultBody.success(list);
    }

    @GetMapping("/accepted/{n}")
    public ResultBody getAcceptedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<ExitApply> list = exitApplyService.getExitApply(true,null,beginTime,Constants.TEACHERACCEPT);
        return ResultBody.success(list);
    }

    @GetMapping("refused/{id}/{n}")
    public ResultBody getRefused(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<ExitApply> list = exitApplyService.getExitApply(false,id,beginTime,Constants.REFUSE);
        return ResultBody.success(list);
    }

    @GetMapping("/refused/{n}")
    public ResultBody getRefusedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<ExitApply> list = exitApplyService.getExitApply(true,null,beginTime,Constants.REFUSE);
        return ResultBody.success(list);
    }
}
