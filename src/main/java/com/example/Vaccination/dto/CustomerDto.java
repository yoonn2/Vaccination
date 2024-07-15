package com.example.Vaccination.dto;

import lombok.*;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {
    private String p_no;
    private String p_name;
    private String p_bday;
    private String p_gender;
    private String p_tel1;
    private String p_tel2;
    private String p_tel3;
    private String p_city;


    private String FormattedBday;
    private int age;

}
