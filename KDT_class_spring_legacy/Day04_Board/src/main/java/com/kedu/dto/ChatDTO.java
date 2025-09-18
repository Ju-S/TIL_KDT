package com.kedu.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDTO {
    private int seq;
    private String sender;
    private String message;
    private Timestamp sendDate;
}
