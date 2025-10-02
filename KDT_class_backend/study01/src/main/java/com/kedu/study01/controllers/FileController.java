package com.kedu.study01.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    @PostMapping
    public ResponseEntity<Void> files(@RequestParam List<MultipartFile> files) {
        for(MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }
        return ResponseEntity.ok().build();
    }
}
