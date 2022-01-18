package com.example.bookmanagementproject.model.response;

import com.example.bookmanagementproject.model.Author;
import com.example.bookmanagementproject.model.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookResponseDto {
    private Long id;
    private String name;
    private String isbn;
    private AuthorResponseDto author=new AuthorResponseDto();

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.isbn = book.getIsbn();
        this.author.setId(book.getAuthor().getId());
        this.author.setFirstName(book.getAuthor().getFirstName());
        this.author.setLastName(book.getAuthor().getLastName());
    }
//
//    public void BookResponse(Book book){
//
//    }


}
