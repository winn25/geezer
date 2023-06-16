package com.khoders.geez.mapper;

import com.amazonaws.services.s3.model.Bucket;
import com.khoders.geez.dto.FileDirectoryDto;
import com.khoders.geez.entities.FileDirectory;
import com.khoders.springapi.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectoryMapper {
    private static final Logger log = LoggerFactory.getLogger(DirectoryMapper.class);
    @Autowired private AppService appService;

    public FileDirectory toEntity(FileDirectoryDto dto){
        FileDirectory directory = new FileDirectory();
        if(dto.getId() != null){
            directory.setId(dto.getId());
        }
        directory.setDirectoryName(dto.directoryName);
        log.info("done conversion to entity");
        return directory;
    }

    public FileDirectoryDto toDto(FileDirectory fileDirectory){
        FileDirectoryDto dto = new FileDirectoryDto();
        FileDirectory directory = appService.findObj(FileDirectory.class, FileDirectory._directoryName,fileDirectory.getDirectoryName());
        if(directory != null){
            dto.setId(directory.getId());
            dto.setDirectoryName(directory.getDirectoryName());
            dto.setUserAccountId(directory.getUserAccount() != null ? directory.getUserAccount().getId() : null);
            dto.setChildDirectory(directory.isChildDirectory());
            dto.setDirectoryPath(dto.getDirectoryPath());
            dto.setParentId(directory.getParentId());
        }
        log.info("done conversion to dto");
        return dto;
    }
}
