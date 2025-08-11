package controller;

import dao.BoardDAO;
import dto.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("*.board")
public class BoardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cmd = request.getRequestURI();
            BoardDAO dao = BoardDAO.getInstance();

            switch (cmd) {
                case "/list.board": {
                    List<BoardDTO> postsList;
                    int itemPerPage = 10;
                    List<List<BoardDTO>> tempList = dao.getPostsPagable(itemPerPage);
                    int maxPage = tempList.size();
                    try {
                        int page = Integer.parseInt(request.getParameter("page"));
                        request.setAttribute("curPage", page);
                        postsList = tempList.get(page - 1);
                    } catch (Exception e) {
                        request.setAttribute("curPage", 1);
                        postsList = tempList.get(0);
                    }
                    request.setAttribute("list", postsList);
                    request.setAttribute("maxPage", maxPage);
                    request.getRequestDispatcher("/boards/boardList.jsp").forward(request, response);
                    break;
                }
                case "/search.board": {
                    String searchOpt = request.getParameter("searchOpt");
                    String search = request.getParameter("search");
                    if (search.isEmpty()) {
                        response.sendRedirect("/list.board");
                        break;
                    }
                    List<BoardDTO> resultList = dao.getPostsByOpt(searchOpt, search);
                    request.setAttribute("list", resultList);
                    request.getRequestDispatcher("/boards/boardList.jsp").forward(request, response);
                    break;
                }
                case "/postingFrm.board": {
                    response.sendRedirect("/boards/posting.jsp");
                    break;
                }
                case "/posting.board": {
                    String title = request.getParameter("title");
                    String contents = request.getParameter("contents");
                    String writer = (String) request.getSession().getAttribute("loginId");

                    dao.posting(new BoardDTO(writer, title, contents));
                    response.sendRedirect("/list.board");
                    break;
                }
                case "/item.board": {
                    int seq = Integer.parseInt(request.getParameter("seq"));
                    request.setAttribute("post", dao.getPostBySeq(seq));
                    request.getRequestDispatcher("/boards/boardItem.jsp").forward(request, response);
                    break;
                }
                case "/delete.board": {
                    int seq = Integer.parseInt(request.getParameter("seq"));
                    dao.deletePostBySeq(seq);
                    response.sendRedirect("/list.board");
                    break;
                }
                case "/update.board": {
                    int seq = Integer.parseInt(request.getParameter("seq"));
                    String title = request.getParameter("title");
                    String contents = request.getParameter("contents");

                    dao.updatePostBySeq(new BoardDTO(seq, title, contents));
                    request.getRequestDispatcher("/item.board").forward(request, response);
                    break;
                }
                default:
                    break;
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