package com.minis3.controller;

import com.minis3.domain.FileMetadata;
import com.minis3.service.FileMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/file-metadata")
public class FileMetadataController {

    private final FileMetadataService fileMetadataService;

    @Autowired
    public FileMetadataController(FileMetadataService fileMetadataService) {
        this.fileMetadataService = fileMetadataService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<FileMetadata>> getAccountFiles(@PathVariable Long accountId) {
        List<FileMetadata> fileMetadataList = fileMetadataService.getFilesMetadata(accountId);
        return ResponseEntity.ok().body(fileMetadataList);
    }
}
