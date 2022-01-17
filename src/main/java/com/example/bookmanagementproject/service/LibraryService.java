package com.example.bookmanagementproject.service;


import com.example.bookmanagementproject.model.*;
import com.example.bookmanagementproject.model.request.AuthorCreationRequest;
import com.example.bookmanagementproject.model.request.BookCreationRequest;
import com.example.bookmanagementproject.model.request.BookLendRequest;
import com.example.bookmanagementproject.model.request.MemberCreationRequest;
import com.example.bookmanagementproject.repository.AuthorRepository;
import com.example.bookmanagementproject.repository.BookRepository;
import com.example.bookmanagementproject.repository.LendRepository;
import com.example.bookmanagementproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryService{
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final LendRepository lendRepository;
    private final BookRepository bookRepository;

    public Book readBook(Long id) {

        Optional<Book> book = bookRepository.findById(id);
//        if (book.isPresent()){
//            return book.get();
//        }
        throw new EntityNotFoundException("Cant find any book under given ID");
    }

    public List<Book> readBooks() {
        return bookRepository.findAll();
    }

    public Book readBook(String isbn){
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(book.isPresent()){
            return book.get();
        }
        throw new EntityNotFoundException("Cant find any book under given ISBN");
    }

    public Book createBook(BookCreationRequest book) {
        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        if(!author.isPresent()){
            throw new EntityNotFoundException("Author Not Found");
        }
        Book bookToCreate = new Book();
        BeanUtils.copyProperties(book, bookToCreate);
        bookToCreate.setAuthor(author.get());
        return bookRepository.save(bookToCreate);

    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);

        return memberRepository.save(member);
    }

    public Member updateMember(Long id, MemberCreationRequest request) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(!optionalMember.isPresent()){
            throw new EntityNotFoundException("Member not present in the database");
        }
        Member member = optionalMember.get();
        member.setLastName(request.getLastName());
        member.setFirstName(request.getFirstName());
        return memberRepository.save(member);
    }

    public List<String> lendABook(BookLendRequest bookLendrequests) {
        List<String> booksApporvedToBurrow = new ArrayList<>();
        bookLendrequests.getBookIds().forEach(bookId ->{
            Optional<Book> bookForId = bookRepository.findById(bookId);
            if (!bookForId.isPresent()){
                throw new EntityNotFoundException("Cant find any book under given ID");
            }
            Optional<Member> memberForId = memberRepository.findById(bookLendrequests.getMemberId());
            if (!memberForId.isPresent()){
                throw new EntityNotFoundException("Member not present in the database");
            }
            Member member = memberForId.get();
            if (member.getStatus() != MemberStatus.ACTIVE){
                throw new RuntimeException("User is not active to proceed a lending.");
            }
            Optional<Lend> burrowedBook = lendRepository.findByBookAndStatus(bookForId.get(), LendStatus.BURROWED);

            if(!burrowedBook.isPresent()){
                booksApporvedToBurrow.add(bookForId.get().getName());
                Lend lend = new Lend();
                lend.setMember(memberForId.get());
                lend.setBook(bookForId.get());
                lend.setStatus(LendStatus.BURROWED);
                lend.setStartOn(Instant.now());
                lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
                lendRepository.save(lend);
            }
        });
        return booksApporvedToBurrow;
    }

    public Author createAuthor (AuthorCreationRequest request) {
        Author author = new Author();
        BeanUtils.copyProperties(request, author);
        return authorRepository.save(author);
    }

//    public Author readAuthor(Long id) {
//        Optional<Author> author = authorRepository.findById(id);
//        throw new EntityNotFoundException("Cant find any author under given ID");
//    }
}//class
