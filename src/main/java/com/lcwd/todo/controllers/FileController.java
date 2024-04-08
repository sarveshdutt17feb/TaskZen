package com.lcwd.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);
    @PostMapping("/single")
    public String uploadSingle(@RequestParam("image")MultipartFile file){
        logger.info("name {} ",file.getName());
        logger.info("content type {} ",file.getContentType());
        logger.info("original file name {} ",file.getOriginalFilename());
        logger.info("file size {} ",file.getSize());
//       InputStream inputStream= file.getInputStream();
//        FileOutputStream fileOutputStream = new FileOutputStream("data.pdf");
//        byte data[] =new  byte[inputStream.available()];
//        fileOutputStream.write();
        return "file test";
    }
}
