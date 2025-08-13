package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import commons.BoardConfig;
import dao.BoardDAO;
import dao.ReplyDAO;
import dto.BoardDTO;
import dto.ReplyDTO;
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
            BoardDAO boardDAO = BoardDAO.getInstance();
            ReplyDAO replyDAO = ReplyDAO.getInstance();

            switch (cmd) {
                case "/list.board": {
                    List<BoardDTO> postsList;
                    int maxPage = boardDAO.getMaxPage(BoardConfig.ITEM_PER_PAGE);
                    int curPage;

                    String searchOpt = request.getParameter("searchOpt");
                    String search = request.getParameter("search");

                    try {
                        curPage = Integer.parseInt(request.getParameter("page"));
                    } catch (Exception e) {
                        curPage = 1;
                    }

                    if (search != null) {
                        if (!search.isEmpty()) {
                            postsList = boardDAO.getPostsPage(searchOpt, search, curPage, BoardConfig.ITEM_PER_PAGE);
                            maxPage = boardDAO.getMaxPage(searchOpt, search, BoardConfig.ITEM_PER_PAGE);
                        } else {
                            postsList = boardDAO.getPostsPage(curPage, BoardConfig.ITEM_PER_PAGE);
                        }
                    } else {
                        postsList = boardDAO.getPostsPage(curPage, BoardConfig.ITEM_PER_PAGE);
                    }

                    request.setAttribute("curPage", curPage);
                    request.setAttribute("list", new ObjectMapper().writeValueAsString(postsList));
                    request.setAttribute("naviPerPage", BoardConfig.NAVI_PER_PAGE);
                    request.setAttribute("itemPerPage", BoardConfig.ITEM_PER_PAGE);
                    request.setAttribute("maxPage", maxPage);
                    request.getRequestDispatcher("/boards/boardList.jsp").forward(request, response);
                    break;
                }
                case "/postingFrm.board": {
                    request.getRequestDispatcher("/boards/posting.jsp").forward(request, response);
                    break;
                }
                case "/posting.board": {
                    String title = request.getParameter("title");
                    String contents = request.getParameter("contents");
                    String writer = (String) request.getSession().getAttribute("loginId");

                    boardDAO.posting(new BoardDTO(writer, title, contents));
                    response.sendRedirect("/list.board");
                    break;
                }
                case "/item.board": {
                    int seq = Integer.parseInt(request.getParameter("seq"));
                    List<ReplyDTO> replyList = replyDAO.selectReplyListByParentSeq(seq);

                    boardDAO.updatePostsViewCntBySeq(seq);

                    request.setAttribute("post", boardDAO.getPostBySeq(seq));
                    request.setAttribute("reply", new ObjectMapper().writeValueAsString(replyList));

                    request.getRequestDispatcher("/boards/boardItem.jsp").forward(request, response);
                    break;
                }
                case "/delete.board": {
                    int seq = Integer.parseInt(request.getParameter("seq"));
                    String writer = request.getParameter("writer");
                    if (request.getSession().getAttribute("loginId").equals(writer)) {
                        boardDAO.deletePostBySeq(seq);
                    }
                    response.sendRedirect("/list.board");
                    break;
                }
                case "/update.board": {
                    int seq = Integer.parseInt(request.getParameter("seq"));
                    String title = request.getParameter("title");
                    String contents = request.getParameter("contents");
                    String writer = request.getParameter("writer");

                    if(request.getSession().getAttribute("loginId").equals(writer)) {
                        boardDAO.updatePostBySeq(new BoardDTO(seq, title, contents));
                    }
                    response.sendRedirect("/item.board?seq=" + seq);
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