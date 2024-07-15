package com.example.Vaccination.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecordDto {
    private String p_seno;
    private String p_no;
    private String p_name;
    private String i_code;
    private String i_name;
    private String p_date;
}
