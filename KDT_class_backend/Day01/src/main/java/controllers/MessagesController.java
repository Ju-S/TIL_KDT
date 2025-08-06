package controllers;

import dao.MessagesDAO;
import dto.MessagesDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("*.messages")
public class MessagesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getRequestURI();

        MessagesDAO dao = MessagesDAO.getInstance();

        System.out.println(cmd);
        System.out.println("안녕");

        try {
            if (cmd.equals("/inputfrm.messages")) {
                response.sendRedirect("/messages/inputFrm.html");
            } else if (cmd.equals("/input.messages")) {
                String writer = request.getParameter("writer");
                String contact = request.getParameter("contact");
                dao.insMsg(writer, contact);
                response.sendRedirect("/");
            } else if (cmd.equals("/output.messages")) {
                List<MessagesDTO> list = dao.getMsg();
                request.setAttribute("list", list);
                request.getRequestDispatcher("/messages/output.jsp").forward(request, response);
            } else if (cmd.equals("/delete.messages")) {
                int target = Integer.parseInt(request.getParameter("target"));
                dao.deleteMsg(target);
                response.sendRedirect("/output.messages");
            } else if (cmd.equals("/update.messages")) {
                int target = Integer.parseInt(request.getParameter("target"));
                String writer = request.getParameter("writer");
                String contact = request.getParameter("contact");
                dao.updateMsg(target, writer, contact);
                response.sendRedirect("/output.messages");
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}