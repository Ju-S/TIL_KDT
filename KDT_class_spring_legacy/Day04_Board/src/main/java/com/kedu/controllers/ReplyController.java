package com.kedu.controllers;

import com.kedu.dao.ReplyDAO;
import com.kedu.dto.ReplyDTO;
import com.kedu.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @RequestMapping("/insert")
    public String insert(String comment,
                         int parentSeq,
                         HttpSession session) {
        replyService.insertReply(ReplyDTO.builder()
                .contents(comment)
                .parentSeq(parentSeq)
                .writer((String) session.getAttribute("loginId"))
                .build());
        return "redirect:/board/item?id=" + parentSeq;
    }

    @RequestMapping("/delete")
    public String delete(int seq,
                         int parentSeq,
                         String writer,
                         HttpSession session) {
        if (session.getAttribute("loginId").equals(writer)) {
            replyService.deleteBySeq(seq);
        }
        return "redirect:/board/item?id=" + parentSeq;
    }

    @RequestMapping("/update")
    public String update(int seq,
                         int parentSeq,
                         String contents,
                         String writer,
                         HttpSession session) {
        if (session.getAttribute("loginId").equals(writer)) {
            replyService.updateBySeq(ReplyDTO.builder()
                    .seq(seq)
                    .contents(contents)
                    .build());
        }
        return "redirect:/board/item?id=" + parentSeq;
    }
}