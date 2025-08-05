package servlets;

import dao.MessagesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
    MessagesDAO dao = MessagesDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            dao.deleteMsg(Integer.parseInt(request.getParameter("target")));
            response.sendRedirect("/Output");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}