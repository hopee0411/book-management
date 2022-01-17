package com.example.bookmanagementproject.repository;

import com.example.bookmanagementproject.model.Book;
import com.example.bookmanagementproject.model.Lend;
import com.example.bookmanagementproject.model.LendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus lendStatus);
}
