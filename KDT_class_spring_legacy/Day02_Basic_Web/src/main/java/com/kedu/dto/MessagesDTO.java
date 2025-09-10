package com.kedu.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessagesDTO {
    private int seq;
    private String writer;
    private String contents;
}
