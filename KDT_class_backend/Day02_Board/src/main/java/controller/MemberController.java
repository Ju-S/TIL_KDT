package controller;

import dao.MemberDAO;
import dto.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MemberDAO dao = MemberDAO.getInstance();

            String cmd = request.getRequestURI();

            if (cmd.equals("/registerFrm.member")) {
                response.sendRedirect("/members/register.jsp");
            } else if (cmd.equals("/idDuplCheck.member")) {
                String id = request.getParameter("id");

                request.setAttribute("idDupl", dao.idDuplcheck(id));
                request.getRequestDispatcher("/members/idDuplCheck.jsp").forward(request, response);
            } else if (cmd.equals("/register.member")) {
                String id = request.getParameter("id");
                String pw = request.getParameter("pw");
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String zipCode = request.getParameter("zipCode");
                String address1 = request.getParameter("address1");
                String address2 = request.getParameter("address2");

                dao.insertMember(new MemberDTO(id, pw, name, phone, email, zipCode, address1, address2));

                response.sendRedirect("/");
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}