package com.example.demo.form;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminForm implements Serializable{
	
    @NotBlank
    @Size(max = 255, min = 1)
    private String lastName;

    @NotBlank
    @Size(max = 255, min = 1)
    private String firstName;

    @NotBlank
    @Email
    @Size(max = 255, min = 1)
    private String email;
    
    @NotBlank
    @Size(max = 255, min = 1)
    private String password;

    // クライアント側には表示しない値
	private Long id;
	private LocalDateTime currentSignInAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
