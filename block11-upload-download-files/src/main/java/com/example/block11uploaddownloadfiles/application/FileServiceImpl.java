package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.domain.FileNew;
import com.example.block11uploaddownloadfiles.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public FileNew addFile(FileNew fileNew) throws Exception {
        return fileRepository.save(fileNew);
    }

    @Override
    public FileNew getFileById(Integer id) throws Exception {
        return fileRepository.getReferenceById(id);
    }

    @Override
    public FileNew getFileByName(String name) throws Exception {
        return fileRepository.getByName(name);
    }
}