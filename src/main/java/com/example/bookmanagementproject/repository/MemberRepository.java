package com.example.bookmanagementproject.repository;

import com.example.bookmanagementproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
