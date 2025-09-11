package com.kedu.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactsDTO {
    private int id;
    private String name;
    private String contact;
}
