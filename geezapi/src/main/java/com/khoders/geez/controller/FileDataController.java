package com.khoders.geez.controller;

import com.khoders.geez.dto.FileDirectoryDto;
import com.khoders.geez.dto.FileMetadataDto;
import com.khoders.geez.mapper.ApiEndpoint;
import com.khoders.geez.services.FileService;
import com.khoders.resource.utilities.Msg;
import com.khoders.springapi.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "File - Endpoint")
@RequestMapping(ApiEndpoint.FILE_ENDPOINT)
public class FileDataController {
    private static final Logger log = LoggerFactory.getLogger(FileDataController.class);
    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<FileMetadataDto> save(@RequestBody FileMetadataDto dto) throws Exception {
        return ApiResponse.created(Msg.CREATED, fileService.save(dto));
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody FileMetadataDto dto) throws Exception {
        return ApiResponse.ok(Msg.UPDATED,fileService.save(dto));
    }
    @GetMapping("/{directoryId}/files")
    public ResponseEntity<List<FileMetadataDto>>findAllFilesInDir(@PathVariable("directoryId") String directoryId) throws Exception {
        return ApiResponse.ok(Msg.RECORD_FOUND, fileService.allFilesInDirectory(directoryId));
    }
    @GetMapping("/list")
    public ResponseEntity<List<FileDirectoryDto>> findAll(){
        return ApiResponse.ok(Msg.RECORD_FOUND, fileService.findAllFiles());
    }
    @DeleteMapping("/{fileId}")
    public ResponseEntity<?> deleteDirectory(@PathVariable("fileId") String fileId) {
        try {
            if (fileService.delete(fileId)) return ApiResponse.ok(Msg.DELETED, true);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ApiResponse.error("Could Not Delete account", false);
    }
}
