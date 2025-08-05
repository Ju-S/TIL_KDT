package servlets;

import dao.MessagesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Update")
public class Update extends HttpServlet {
    MessagesDAO dao = MessagesDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int target = Integer.parseInt(request.getParameter("target"));
            String writer = request.getParameter("writer");
            String contact = request.getParameter("contact");
            dao.updateMsg(target, writer, contact);
            response.sendRedirect("/Output");
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}