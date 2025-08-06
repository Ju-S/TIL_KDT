package controllers;

import dao.MessageDAO;
import dto.MessageDTO;
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
        try {
            String cmd = request.getRequestURI();

            MessageDAO dao = MessageDAO.getInstance();

            if (cmd.equals("/inputFrm.messages")) {
                response.sendRedirect("/messages/input.html");
            } else if (cmd.equals("/input.messages")) {
                String writer = request.getParameter("writer");
                String message = request.getParameter("message");

                dao.insert(writer, message);
                response.sendRedirect("/");
            } else if (cmd.equals("/output.messages")) {
                List<MessageDTO> list = dao.getMessages();
                request.setAttribute("list", list);
                request.getRequestDispatcher("/messages/output.jsp").forward(request, response);
            } else if (cmd.equals("/delete.messages")) {
                int target = Integer.parseInt(request.getParameter("target"));

                dao.deleteBySeq(target);
                response.sendRedirect("/output.messages");
            } else if (cmd.equals("/update.messages")) {
                int target = Integer.parseInt(request.getParameter("target"));
                String writer = request.getParameter("writer");
                String message = request.getParameter("message");

                dao.updateBySeq(target, writer, message);
                response.sendRedirect("/output.messages");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}