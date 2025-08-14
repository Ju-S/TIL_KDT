package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import commons.BoardConfig;
import dao.BoardDAO;
import dao.FilesDAO;
import dao.ReplyDAO;
import dto.BoardDTO;
import dto.FilesDTO;
import dto.ReplyDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("*.board")
public class BoardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cmd = request.getRequestURI();
            BoardDAO boardDAO = BoardDAO.getInstance();
            ReplyDAO replyDAO = ReplyDAO.getInstance();
            FilesDAO filesDAO = FilesDAO.getInstance();

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
                    int maxSize = 1024 * 1024 * 10;
                    String savePath = request.getServletContext().getRealPath("upload");
                    File filePath = new File(savePath);

                    if (!filePath.exists()) {
                        filePath.mkdir();
                    }

                    MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "utf8", new DefaultFileRenamePolicy());

                    String title = multi.getParameter("title");
                    String contents = multi.getParameter("contents");
                    String writer = (String) request.getSession().getAttribute("loginId");

                    String oriName = multi.getOriginalFileName("files");
                    String sysName = multi.getFilesystemName("files");

                    int seq = boardDAO.getBoardSeqNextVal();

                    boardDAO.posting(new BoardDTO(seq, writer, title, contents));
                    filesDAO.uploadFile(new FilesDTO(writer, oriName, sysName, seq));
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