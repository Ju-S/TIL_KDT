package controller;

import dao.ReplyDAO;
import dto.ReplyDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.reply")
public class ReplyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cmd = request.getRequestURI();
            ReplyDAO dao = ReplyDAO.getInstance();

            switch (cmd) {
                case "/insert.reply": {
                    String comment = request.getParameter("comment");
                    String writer = (String) request.getSession().getAttribute("loginId");
                    int parentSeq = Integer.parseInt(request.getParameter("parentSeq"));

                    dao.insertReply(new ReplyDTO(writer, comment, parentSeq));

                    response.sendRedirect("/item.board?seq=" + parentSeq);
                    break;
                }
                case "/delete.reply": {
                    int target = Integer.parseInt(request.getParameter("seq"));
                    String writer = request.getParameter("writer");
                    int parentSeq = Integer.parseInt(request.getParameter("parentSeq"));

                    if (request.getSession().getAttribute("loginId").equals(writer)) {
                        dao.deleteBySeq(target);
                    }

                    response.sendRedirect("/item.board?seq=" + parentSeq);
                    break;
                }
                case "/update.reply": {
                    int target = Integer.parseInt(request.getParameter("seq"));
                    String contents = request.getParameter("contents");
                    String writer = request.getParameter("writer");
                    int parentSeq = Integer.parseInt(request.getParameter("parentSeq"));

                    if(request.getSession().getAttribute("loginId").equals(writer)) {
                        dao.updateBySeq(new ReplyDTO(target, contents));
                    }

                    response.sendRedirect("/item.board?seq=" + parentSeq);
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