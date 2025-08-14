package controller;

import utils.CustomEncrypt;
import dao.MemberDAO;
import dto.MemberDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MemberDAO dao = MemberDAO.getInstance();

            String cmd = request.getRequestURI();

            switch (cmd) {
                case "/registerFrm.member": {
                    response.sendRedirect("/members/register.jsp");
                    break;
                }
                case "/idDuplCheck.member": {
                    String id = request.getParameter("id");

                    request.setAttribute("idDupl", dao.idDuplcheck(id));
                    request.getRequestDispatcher("/members/idDuplCheck.jsp").forward(request, response);
                    break;
                }
                case "/register.member": {
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
                    break;
                }
                case "/login.member": {
                    String id = request.getParameter("id");
                    String pw = request.getParameter("pw");

                    boolean loginState = dao.login(id, CustomEncrypt.encrypt(pw));

                    if (loginState) {
                        request.getSession().setAttribute("loginId", id);
                    }
                    response.sendRedirect("/");
                    break;
                }
                case "/logout.member": {
                    request.getSession().invalidate(); // 현재 접속한 사용자의 세션 값을 전부 무효화 하는 명령
                    response.sendRedirect("/");
                    break;
                }
                case "/withdraw.member": {
                    String id = (String) request.getSession().getAttribute("loginId");
                    dao.withdraw(id);
                    request.getSession().invalidate();
                    response.sendRedirect("/");
                    break;
                }
                case "/mypage.member": {
                    String id = (String) request.getSession().getAttribute("loginId");
                    request.setAttribute("myInfo", dao.getMemberInfoById(id));
                    request.getRequestDispatcher("/members/mypage.jsp").forward(request, response);
                    break;
                }
                case "/update.member": {
                    String id = (String) request.getSession().getAttribute("loginId");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String zipcode = request.getParameter("zipCode");
                    String address1 = request.getParameter("address1");
                    String address2 = request.getParameter("address2");

                    dao.updateMemberInfo(new MemberDTO(id, phone, email, zipcode, address1, address2));

                    response.sendRedirect("/mypage.member");
                    break;
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