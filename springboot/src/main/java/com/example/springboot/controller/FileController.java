package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.springboot.commen.Result;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    private static final String ROOT_PATH = "D:\\graduate\\images";
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException, InterruptedException {
        String originalFilename = file.getOriginalFilename();
        String extName = FileUtil.extName(originalFilename);
        String fileName = "message." + extName;
        String fileRealPath = "D:\\graduate\\images\\" + fileName;
        File saveFile = new File(fileRealPath);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = "http://localhost:8080/file/download/" + originalFilename;
        Process process = Runtime.getRuntime().exec("python D:\\graduate\\ultralytics-main\\ultralytics-main\\predict.py");
        process.waitFor();
        return Result.success(url);
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + fileName;
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    @GetMapping("/get")
    public String getUrl() throws IOException {
        String filePath = "D:/graduate/images/message.jpg";
        Path file = Paths.get(filePath);
        byte[] byteArray = Files.readAllBytes(file.toAbsolutePath());
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
