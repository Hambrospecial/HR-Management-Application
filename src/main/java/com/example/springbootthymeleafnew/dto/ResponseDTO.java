package com.example.springbootthymeleafnew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private int id;
    private String message;
}
