package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.FilesDAO;
import dto.FilesDTO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("*.files")
public class FilesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            FilesDAO dao = FilesDAO.getInstance();
            String cmd = request.getRequestURI();

            switch (cmd) {
                case "/list.files": {
                    request.setAttribute("filesList", new ObjectMapper().writeValueAsString(dao.getFilesList()));
                    break;
                }
                case "/download.files": {
                    String realPath = request.getServletContext().getRealPath("upload");
                    String sysName = request.getParameter("sysName");
                    String oriName = request.getParameter("oriName");
                    File target = new File(realPath + "/" + sysName);

                    System.out.println(sysName);

                    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(oriName, StandardCharsets.UTF_8) + "\"");

                    try (FileInputStream fis = new FileInputStream(target);
                         DataInputStream dis = new DataInputStream(fis);
                         ServletOutputStream sos = response.getOutputStream();) {
                        byte[] fileContents = new byte[(int)target.length()];

                        dis.readFully(fileContents);

                        sos.write(fileContents);
                        sos.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}