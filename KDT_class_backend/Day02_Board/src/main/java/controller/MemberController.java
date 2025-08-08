package controller;

import common.CustomEncrypt;
import dao.MemberDAO;
import dto.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import oracle.jdbc.driver.JsonWebToken;

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

                dao.insertMember(new MemberDTO(id, CustomEncrypt.encrypt(pw), name, phone, email, zipCode, address1, address2));

                response.sendRedirect("/");
            } else if (cmd.equals("/login.member")) {
                String id = request.getParameter("id");
                String pw = request.getParameter("pw");

                boolean loginState = dao.login(id, CustomEncrypt.encrypt(pw));

                if (loginState) {
                    //response.addCookie(new Cookie("loginId", id));
                    request.getSession().setAttribute("loginId", id);
                }
                response.sendRedirect("/");
            } else if (cmd.equals("/logout.member")) {
                request.getSession().invalidate(); // 현재 접속한 사용자의 세션 값을 전부 무효화 하는 명령
                response.sendRedirect("/");
            } else if (cmd.equals("/withdraw.member")) {
                String id = (String) request.getSession().getAttribute("loginId");
                dao.withdraw(id);
                request.getSession().invalidate();
                response.sendRedirect("/");
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