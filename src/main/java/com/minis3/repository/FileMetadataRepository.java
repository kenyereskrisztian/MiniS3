package com.minis3.repository;

import com.minis3.domain.FileMetadata;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMetadataRepository extends CrudRepository<FileMetadata, Long> {

    @Query("SELECT f FROM FileMetadata f WHERE f.account.id = :accountId")
    List<FileMetadata> findAllByAccountId(Long accountId);
}
