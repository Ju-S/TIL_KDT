package com.kedu.dto;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilesDTO {
    private int seq;
    private String writer;
    private String oriName;
    private String sysName;
    private int parentSeq;
    private Timestamp uploadTime;
}
