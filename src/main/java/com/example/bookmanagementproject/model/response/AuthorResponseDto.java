package com.example.bookmanagementproject.model.response;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<BookResponseDto> bookResponses;
}
