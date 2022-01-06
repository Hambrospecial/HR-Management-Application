package com.example.springbootthymeleafnew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {
    private String oldPassword;
    private String newPassword;
}
