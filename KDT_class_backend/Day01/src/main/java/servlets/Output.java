package servlets;
import dao.MessagesDAO;
import dto.MessagesDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Output")
public class Output extends HttpServlet {
    MessagesDAO dao = new MessagesDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("JVM file.encoding = " + System.getProperty("file.encoding"));
            List<MessagesDTO> messageList = dao.getMsg();
            for (MessagesDTO message : messageList){
                response.getWriter().append(message.getSeq() + " : " + message.getWriter() + " : " + message.getMessage());
            }
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