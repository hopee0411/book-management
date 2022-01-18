package com.example.bookmanagementproject.model.response;

import com.example.bookmanagementproject.model.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String name;
    private String isbn;
    private Author author;

    public void BookResponse(Book book){
        this.id = book.getId();
        this.name = book.getName();
        this.isbn = book.getIsbn();
        this.author.setId(book.getAuthor().getId());
        this.author.setFirstName(book.getAuthor().getFirstName());
        this.author.setLastName(book.getAuthor().getLastName());
    }

    @Getter
    @Setter
    static class Author {
        private Long id;
        private String firstName;
        private String lastName;
    }
}
