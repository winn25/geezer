package com.khoders.geez.mapper;

import com.khoders.geez.dto.FileMetadataDto;
import com.khoders.geez.entities.FileDirectory;
import com.khoders.geez.entities.FileExt;
import com.khoders.geez.entities.FileMetadata;
import com.khoders.geez.exception.DataNotFoundException;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import com.khoders.springapi.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class FileMapper {
    private static final Logger log = LoggerFactory.getLogger(FileMapper.class);
    @Autowired
    private AppService appService;
    public FileMetadata toEntity(FileMetadataDto dto) throws DataNotFoundException {
        FileMetadata metadata = new FileMetadata();
        if(dto.getId() != null){
            metadata.setId(dto.getId());
        }
        metadata.setFileName(dto.getFileName());
        metadata.setFilePath("");
       if(dto.getDirectoryId() == null){
           log.error("Directory not found");
           throw new DataNotFoundException("directoryId cannot be null");
       }
       if(dto.getFileExtId() == null){
           log.error("File ext not found");
           throw new DataNotFoundException("FileExt Id cannot be null");
       }
        metadata.setFileExt(appService.findById(FileExt.class, dto.getFileExtId()));
        metadata.setFileDirectory(appService.findById(FileDirectory.class, dto.getDirectoryId()));
       return metadata;
    }

    public FileMetadataDto toDto(FileMetadata metadata) throws Exception {
        FileMetadataDto dto = new FileMetadataDto();
        dto.setDirectory(metadata.getFileDirectory() != null ? metadata.getFileDirectory().getDirectoryName() : null);
        dto.setDirectoryId(metadata.getFileDirectory() != null ? metadata.getFileDirectory().getId() : null);
        dto.setFileName(metadata.getFileName());
        dto.setValueDate(DateUtil.parseLocalDateString(metadata.getValueDate(), Pattern.ddMMyyyy));
        dto.setFilePath(metadata.getFilePath());
        if(metadata.getFileExt() != null){
            dto.setFileExt(metadata.getFileExt().getExtName());
            dto.setFileExtId(metadata.getFileExt().getId());
        }
        return dto;
    }
}
