package com.khoders.geez.services;

import com.khoders.geez.dto.FileMetadataDto;
import com.khoders.geez.dto.Sql;
import com.khoders.geez.entities.FileMetadata;
import com.khoders.geez.entities.FileDirectory;
import com.khoders.geez.exception.BadDataException;
import com.khoders.geez.mapper.FileMapper;
import com.khoders.resource.utilities.Msg;
import com.khoders.springapi.AppService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FileService {
    private static final Logger log = LoggerFactory.getLogger(FileService.class);
    @Autowired private AppService appService;
    @Autowired private FileMapper mapper;
    @Autowired private NamedParameterJdbcTemplate jdbc;

    public FileMetadataDto save(FileMetadataDto dto) throws Exception {
        FileMetadata metadata = mapper.toEntity(dto);
        FileMetadata fileDataMetadata = (FileMetadata) appService.save(metadata);
        if (fileDataMetadata == null) {
            throw new BadDataException(Msg.UNKNOWN_ERROR);
        }
        log.info("Done saving & converting to dto");
        return mapper.toDto(fileDataMetadata);
    }
    public FileMetadataDto findById(String fileMetaDataId) throws Exception {
        if(fileMetaDataId == null){
            throw new BadDataException(Msg.UNKNOWN_ERROR);
        }
        FileMetadata fileDataMetadata = appService.findById(FileMetadata.class, fileMetaDataId);
        return mapper.toDto(fileDataMetadata);
    }
    public List<FileMetadataDto> findAllFiles(){
        List<FileMetadataDto> dtoList = new LinkedList<>();
        List<FileMetadata> fileList =  appService.findAll(FileMetadata.class);
        fileList.forEach(file ->{
            try {
                dtoList.add(mapper.toDto(file));
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });
        return dtoList;
    }
    public List<FileMetadataDto> allFilesInDirectory(String directoryId){
        List<FileMetadataDto> dtoList = new LinkedList<>();
        SqlParameterSource param = new MapSqlParameterSource(FileMetadata._fileDirectoryId, directoryId);
        List<FileMetadata> fileList = jdbc.query(Sql.ALL_FILES_IN_DIR, param, BeanPropertyRowMapper.newInstance(FileMetadata.class));
        fileList.forEach(file ->{
            try {
                dtoList.add(mapper.toDto(file));
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });
        return dtoList;
    }
    public boolean delete(String directoryId) {
        return appService.deleteById(FileDirectory.class, directoryId);
    }
}
