package com.kedu.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private int seq;
    private String writer;
    private String contents;
    private Timestamp writeDate;
    private int parentSeq;
}
