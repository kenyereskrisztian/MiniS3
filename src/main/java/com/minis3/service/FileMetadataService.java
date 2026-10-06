package com.minis3.service;

import com.minis3.repository.FileMetadataRepository;
import com.minis3.domain.FileMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileMetadataService {

    private final FileMetadataRepository fileMetadataRepository;

    @Autowired
    public FileMetadataService(FileMetadataRepository fileMetadataRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }

public List<FileMetadata> getFilesMetadata(Long accountId) {
        return fileMetadataRepository.findAllByAccountId(accountId);
    }
}
