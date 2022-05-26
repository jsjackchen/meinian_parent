package com.sgcc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sgcc.constant.MessageConstant;
import com.sgcc.constant.QiniuUtils;
import com.sgcc.entity.Result;
import com.sgcc.service.impl.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        String originalFilename = imgFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String fileName = UUID.randomUUID().toString()+suffix;
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
        } catch (IOException e) {
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
    }
}
