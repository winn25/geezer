package com.khoders.geez.services;

import com.khoders.geez.config.JndiConfig;
import com.khoders.geez.dto.FileDirectoryDto;
import com.khoders.geez.entities.FileDirectory;
import com.khoders.geez.exception.BadDataException;
import com.khoders.geez.mapper.DirectoryMapper;
import com.khoders.resource.utilities.Msg;
import com.khoders.springapi.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {
    private static final Logger log = LoggerFactory.getLogger(DirectoryMapper.class);
    private final AppService appService;
    @Autowired private DirectoryMapper mapper;
    @Autowired private LocalSessionFactoryBean factoryBean;

    @Autowired
    public DirectoryService(LocalSessionFactoryBean factoryBean) {
        this.appService = new AppService(factoryBean.getObject());
    }

    public FileDirectoryDto save(FileDirectoryDto dto) throws Exception {
        FileDirectory directory = mapper.toEntity(dto);
        String fileDirectory = (String)appService.save(directory);
        if (fileDirectory == null) {
            throw new BadDataException(Msg.UNKNOWN_ERROR);
        }
        log.info("Done saving & converting to dto: "+fileDirectory);
        return dto;
    }
    public FileDirectoryDto findById(String directoryId) throws Exception {
        if(directoryId == null){
            throw new BadDataException(Msg.UNKNOWN_ERROR);
        }
        FileDirectory fileDirectory = appService.findById(FileDirectory.class, directoryId);
        return mapper.toDto(fileDirectory);
    }
    public List<FileDirectoryDto> findAllDirectories() {
        return null;
    }
    public boolean delete(String directoryId) {
        return appService.deleteById(FileDirectory.class, directoryId);
    }
}
