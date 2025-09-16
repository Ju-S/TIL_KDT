package com.kedu.controllers;

import com.kedu.dao.FilesDAO;
import com.kedu.dto.FilesDTO;
import com.kedu.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FilesService filesService;

    @ResponseBody
    @RequestMapping("/list")
    public List<FilesDTO> getList(int parentSeq) {
        return filesService.list(parentSeq);
    }

    @RequestMapping("/download")
    public void download(String oriName, String sysName, HttpSession session, HttpServletResponse response) throws Exception {
        String realPath = session.getServletContext().getRealPath("upload");
        File target = new File(realPath + "/" + sysName);

        oriName = new String(oriName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.setHeader("content-disposition", "attachment;filename=\"" + oriName + "\"");
        System.out.println(oriName);

        try (DataInputStream dis = new DataInputStream(new FileInputStream(target));
             DataOutputStream dos = new DataOutputStream(response.getOutputStream())) {
            byte[] fileContents = dis.readAllBytes();
            dos.write(fileContents);
            dos.flush();
        }
    }
}
