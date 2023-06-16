package com.khoders.geez.controller;

import com.khoders.geez.dto.FileDirectoryDto;
import com.khoders.geez.mapper.ApiEndpoint;
import com.khoders.geez.services.DirectoryService;
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
@Tag(name = "Directory - Endpoint")
@RequestMapping(ApiEndpoint.DIR_ENDPOINT)
public class DirectoryController {
    private static final Logger log = LoggerFactory.getLogger(DirectoryController.class);
    @Autowired private DirectoryService service;

    @PostMapping
    public ResponseEntity<FileDirectoryDto> createDir(@RequestBody FileDirectoryDto dto)throws Exception{
        return ApiResponse.created(Msg.CREATED,service.save(dto));
    }
    @PutMapping
    public ResponseEntity<?>updateDir(@RequestBody FileDirectoryDto dto) throws Exception {
        return ApiResponse.ok(Msg.UPDATED, service.save(dto));
    }
    @GetMapping("/{directoryId}")
    public ResponseEntity<?>delete(@PathVariable("directoryId") String directoryId) throws Exception {
        return ApiResponse.ok(Msg.RECORD_FOUND, service.findById(directoryId));
    }
    @GetMapping("/list")
    public ResponseEntity<List<FileDirectoryDto>> findAll(){
        return ApiResponse.ok(Msg.RECORD_FOUND, service.findAllDirectories());
    }
    @DeleteMapping("/{directoryId}")
    public ResponseEntity<?> deleteDirectory(@PathVariable("directoryId") String directoryId) {
        try {
            if (service.delete(directoryId)) return ApiResponse.ok(Msg.DELETED, true);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ApiResponse.error("Could Not Delete account", false);
    }
}
