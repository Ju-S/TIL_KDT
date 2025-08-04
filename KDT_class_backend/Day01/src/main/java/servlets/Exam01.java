package servlets;

import dao.MessagesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Exam01")
public class Exam01 extends HttpServlet {
    MessagesDAO dao = new MessagesDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        String writer = request.getParameter("writer");
        String contact = request.getParameter("contact");

        try {
            dao.insMsg(writer, contact);
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.html");
        }

        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
