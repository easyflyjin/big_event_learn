package com.example.bigevent.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.bigevent.pojo.Result;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Value("${file.upload}")
    private String fileUpload;
    @PostMapping("")
    public Result<String> uploadFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        file.transferTo(new File("C:\\Users\\11031\\Desktop\\big_event\\bigevent\\src\\main\\java\\com\\example\\bigevent\\server\\"+filename));
        return Result.success("http://localhost:8080"+"/file/"+filename);
    }
    
}
