package com.example.bookmanagementproject.controller;

import com.example.bookmanagementproject.model.Author;
import com.example.bookmanagementproject.model.Book;
import com.example.bookmanagementproject.model.Member;
import com.example.bookmanagementproject.model.request.AuthorCreationRequest;
import com.example.bookmanagementproject.model.request.BookCreationRequest;
import com.example.bookmanagementproject.model.request.BookLendRequest;
import com.example.bookmanagementproject.model.request.MemberCreationRequest;
import com.example.bookmanagementproject.model.response.BookResponse;
import com.example.bookmanagementproject.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/book")
    public ResponseEntity readBooks(@RequestParam(required = false) String isbn){
        if(isbn == null){
            return ResponseEntity.ok(libraryService.readBooks());
        }
        return ResponseEntity.ok(libraryService.readBook(isbn));
    }

    @GetMapping("/book/{bookId}")
    public BookResponse readBook (@PathVariable Long bookId) {

        return libraryService.readBook(bookId);
    }

    @PostMapping("/book")
    public BookResponse createBook(@RequestBody BookCreationRequest request){
        return libraryService.createBook(request);
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long bookId){
        libraryService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/member")
    public ResponseEntity<Member> createMember (@RequestBody MemberCreationRequest request){
        return ResponseEntity.ok(libraryService.createMember(request));
    }

    @PatchMapping("/member/{memberId}")
    public ResponseEntity<Member> updateMember (@RequestBody MemberCreationRequest request, @PathVariable Long memberId){
        return ResponseEntity.ok(libraryService.updateMember(memberId, request));
    }

    @PostMapping("/book/lend")
    public ResponseEntity<List<String>> lendABook(@RequestBody BookLendRequest bookLendRequests) {
        return ResponseEntity.ok(libraryService.lendABook(bookLendRequests));
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor (@RequestBody AuthorCreationRequest request) {
        return ResponseEntity.ok(libraryService.createAuthor(request));
    }
//    @GetMapping("/author/{authorId}")
//    public ResponseEntity<Author> readAuthor (@PathVariable Long authorId) {
//        return ResponseEntity.ok(libraryService.readAuthor(authorId));
//    }

}//class end