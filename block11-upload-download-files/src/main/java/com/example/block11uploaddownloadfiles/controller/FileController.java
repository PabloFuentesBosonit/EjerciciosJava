package com.example.block11uploaddownloadfiles.controller;

import com.example.block11uploaddownloadfiles.application.FileService;
import com.example.block11uploaddownloadfiles.domain.FileNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController
@RequestMapping("/fileNew")
public class FileController {

    @Autowired
    FileService fileService;

    //añadir archivo
    @PostMapping
    public ResponseEntity addFile (@RequestParam("fileNew") MultipartFile multipartFile) throws Exception {

        FileNew fileNew = new FileNew();
        Date date =new Date();

        fileNew.setName(multipartFile.getOriginalFilename());
        fileNew.setUploadDate(date);
        fileNew.setContent(multipartFile.getBytes());

        return new ResponseEntity<>(fileService.addFile(fileNew), HttpStatus.CREATED);
    }

    //obtener archivo con id
    @GetMapping("/id/{id}")
    public ResponseEntity <?> getFileById (@PathVariable Integer id) throws Exception {
        FileNew fileNew = fileService.getFileById(id);
        if (fileNew !=null) {
            byte [] file = fileNew.getContent();
            Path patch = Paths.get(fileNew.getName());
            Files.write(patch,file);
            return  ResponseEntity.status( HttpStatus.OK)
                    .body("Archivo encontrado con exito " + fileNew.getName());
        } else { throw new FileNotFoundException("FileNew not found");
        }
    }

    //obtener archivo con nombre
    @GetMapping("/name/{name}")
    public ResponseEntity <?> getFileByName (@PathVariable String name) throws Exception {
        FileNew fileNew = fileService.getFileByName(name);
        if (fileNew!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(fileNew);
        } else { throw new FileNotFoundException("FileNew not found");
        }
    }

}
