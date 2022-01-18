package com.example.bookmanagementproject.model.response;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private List<BookResponse> bookResponses;
}
