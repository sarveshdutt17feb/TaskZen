package com.lcwd.todo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

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
    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file->{
            logger.info("file original name {} ",file.getOriginalFilename());
            logger.info("file type {} ",file.getContentType());
            //call service to upload files : and pass file object
        });
        return "handling multiple files";

    }

    //serving image file in response
    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response){
        //image
        try{
        InputStream fileInputStream = new FileInputStream("images/download.png");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
