package com.kedu.controllers;

import com.kedu.dao.FilesDAO;
import com.kedu.dto.FilesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FilesDAO dao;

    @RequestMapping("/upload")
    public String upload(String text, MultipartFile[] files, HttpSession session) throws Exception {
        String realPath = session.getServletContext().getRealPath("upload");
        System.out.println(realPath);

        File realPathFile = new File(realPath);

        if (!realPathFile.exists()) {
            realPathFile.mkdir();
        }

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String oriname = file.getOriginalFilename();
                String sysname = UUID.randomUUID() + "_" + oriname;

                file.transferTo(new File(realPathFile + "/" + sysname));

                dao.insert(FilesDTO.builder()
                        .writer("tester")
                        .oriName(oriname)
                        .sysName(sysname)
                        .parentSeq(0)
                        .build());
            }
        }
        return "redirect:/";
    }
}
