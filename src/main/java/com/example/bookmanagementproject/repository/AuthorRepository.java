package com.example.bookmanagementproject.repository;

import com.example.bookmanagementproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
