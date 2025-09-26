package com.kedu.study01.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDTO {
    private int id;
    private String title;
    private String singer;
}
