package com.example.block11uploaddownloadfiles.repository;

import com.example.block11uploaddownloadfiles.domain.FileNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends JpaRepository<FileNew, Integer> {
    FileNew getByName(String name);
}
