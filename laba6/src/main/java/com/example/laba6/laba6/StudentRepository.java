package com.example.laba6.laba6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByFullName(String fullName);
    void deleteByFullName(String fullName);
}
