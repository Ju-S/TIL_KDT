package com.kedu.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private int id;
    private String title;
    private String writer;
    private String contents;
    private int viewCount;
    private Timestamp writeDate;
}
