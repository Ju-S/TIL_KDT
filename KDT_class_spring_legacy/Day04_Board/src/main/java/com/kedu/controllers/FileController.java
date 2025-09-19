package com.kedu.controllers;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.kedu.dao.FilesDAO;
import com.kedu.dto.FilesDTO;
import com.kedu.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    private final String bucketName = "kiwii_study";
    private final String keyFile = "focus-shape-472605-e7-01d62f8a3302.json";

    private Storage storage;

    @Autowired
    private FilesService filesService;

    public FileController() {
        try {
            InputStream keyStream = this.getClass().getClassLoader().getResourceAsStream(keyFile);
            GoogleCredentials credential = GoogleCredentials.fromStream(keyStream);
            storage = StorageOptions.newBuilder().setCredentials(credential).build().getService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<FilesDTO> getList(int parentSeq) {
        return filesService.list(parentSeq);
    }

    @ResponseBody
    @RequestMapping("/listgcs")
    public List<String> list() {
        List<String> fileNames = new ArrayList<>();

        Page<Blob> blobs = storage.list(bucketName);
        for(Blob b : blobs.iterateAll()) {
            fileNames.add(b.getName());
            System.out.println(b.getName());
        }
        return fileNames;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String oriName, String sysName, HttpSession session, HttpServletResponse response) throws Exception {
        Blob blob = storage.get(bucketName, sysName);

        byte[] content = blob.getContent();

        String encodedFilename = URLEncoder.encode(oriName, "UTF-8").replace("+", "%20");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + "download.png" + "\"; filename*=UTF-8''" + encodedFilename);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
//        String realPath = session.getServletContext().getRealPath("upload");
//        File target = new File(realPath + "/" + sysName);
//
//        oriName = new String(oriName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
//        response.setHeader("content-disposition", "attachment;filename=\"" + oriName + "\"");
//
//        try (DataInputStream dis = new DataInputStream(new FileInputStream(target));
//             DataOutputStream dos = new DataOutputStream(response.getOutputStream())) {
//            byte[] fileContents = dis.readAllBytes();
//            dos.write(fileContents);
//            dos.flush();
//        }
    }
}
