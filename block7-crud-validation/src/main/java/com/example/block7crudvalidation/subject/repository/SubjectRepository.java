package com.example.block7crudvalidation.subject.repository;

import com.example.block7crudvalidation.subject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
