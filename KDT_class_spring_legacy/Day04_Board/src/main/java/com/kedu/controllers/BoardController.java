package com.kedu.controllers;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.Gson;
import com.kedu.dto.BoardDTO;
import com.kedu.dto.FilesDTO;
import com.kedu.services.BoardService;
import com.kedu.services.FilesService;
import com.kedu.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final String bucketName = "kiwii_study";
    private final String keyFile = "focus-shape-472605-e7-01d62f8a3302.json";

    private Storage storage;

    @Autowired
    private BoardService boardService;
    @Autowired
    private FilesService filesService;
    @Autowired
    private ReplyService replyService;

    @Autowired
    private Gson gson;

    public BoardController() {
        try {
            InputStream keyStream = this.getClass().getClassLoader().getResourceAsStream(keyFile);
            GoogleCredentials credential = GoogleCredentials.fromStream(keyStream);
            storage = StorageOptions.newBuilder().setCredentials(credential).build().getService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(required = false, defaultValue = "") String searchOpt,
                       @RequestParam(required = false) String search,
                       @RequestParam(required = false, defaultValue = "1") int page) {
        List<BoardDTO> postsList = boardService.getList(search, searchOpt, page);
        int maxPage = boardService.getMaxPage(search, searchOpt);

        model.addAttribute("curPage", page);
        model.addAttribute("list", gson.toJson(postsList));
        model.addAttribute("naviPerPage", 5);
        model.addAttribute("itemPerPage", 10);
        model.addAttribute("maxPage", maxPage);

        return "board/boardList";
    }

    @RequestMapping("/postingform")
    public String postingForm() {
        return "board/posting";
    }

    @RequestMapping("/posting")
    public String posting(HttpSession session, String title, String contents, MultipartFile[] files) throws Exception {
        int boardId = boardService.posting(BoardDTO.builder()
                .writer((String) session.getAttribute("loginId"))
                .title(title)
                .contents(contents)
                .build());

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String oriname = file.getOriginalFilename();
                String sysname = UUID.randomUUID() + "_" + oriname;

                BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, sysname))
                        .setContentType(file.getContentType()).build();

                try (InputStream is = file.getInputStream()) {
                    storage.createFrom(blobInfo, is);
                }

                filesService.upload(FilesDTO.builder()
                        .writer((String) session.getAttribute("loginId"))
                        .oriName(oriname)
                        .sysName(sysname)
                        .parentSeq(boardId)
                        .build());
            }
        }
        return "redirect:/board/list";
    }

    @RequestMapping("/item")
    public String item(int id, Model model) {
        boardService.updatePostsViewCntById(id);
        model.addAttribute("post", boardService.getPostById(id));
        model.addAttribute("reply", gson.toJson(replyService.selectReplyListByParentSeq(id)));
        return "board/boardItem";
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session, int id, String writer) {
        if (session.getAttribute("loginId").equals(writer)) {
            boardService.deletePostById(id);
        }
        return "redirect:/board/list";
    }

    @RequestMapping("/update")
    public String update(HttpSession session, int id, String title, String contents) {
        if (boardService.getPostById(id).getWriter().equals(session.getAttribute("loginId"))) {
            boardService.updatePostById(BoardDTO.builder()
                    .id(id)
                    .title(title)
                    .contents(contents)
                    .build());
        }
        return "redirect:/board/item?id=" + id;
    }
}
