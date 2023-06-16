package com.khoders.geez.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.khoders.geez.config.AwsProps;
import com.khoders.geez.dto.FileDirectoryDto;
import com.khoders.geez.entities.FileDirectory;
import com.khoders.geez.mapper.FileMapper;
import com.khoders.springapi.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class BackupService extends AwsProps {
    private static final Logger log = LoggerFactory.getLogger(BackupService.class);
    @Autowired private AmazonS3 s3;
    @Autowired private FileMapper fm;
    @Autowired private AppService appService;
//    public FileDirectoryDto createBucket(FileDirectoryDto dto){
//        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(getS3region()).build();
//        Bucket b = getBucket(dto.getDirectoryName().toLowerCase());
//        if (b != null && b.getName().equals(dto.getDirectoryName().toLowerCase())) {
//            log.debug("Bucket %s already exists: {}", dto.getDirectoryName());
//        } else {
//            try {
//                b = s3.createBucket(dto.getDirectoryName());
//            } catch (AmazonS3Exception e) {
//                log.error(e.getMessage());
//            }
//        }
//        return b != null ? fm.toDto(b) : null;
//    }

    private Bucket getBucket(String directoryName){
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(getS3region()).build();
        Bucket bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(directoryName)) {
                bucket = b;
            }
        }
        return bucket;
    }


    public void createFolder(String directoryId, String folderName){

    }
    public boolean deleteDirectory(String directoryId){
        try {
            FileDirectory directory = appService.findById(FileDirectory.class, directoryId);
            if (s3.doesBucketExistV2(directory.getDirectoryName())) {
                s3.deleteBucket(directory.getDirectoryName());
            }
        } catch (AmazonServiceException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
    public void deleteFolder(String directoryId, String folderPath){
        FileDirectory directory = appService.findById(FileDirectory.class, directoryId);
        for (S3ObjectSummary file : s3.listObjects(directory.getDirectoryName(), folderPath).getObjectSummaries()){
            s3.deleteObject(directory.getDirectoryName(), file.getKey());
        }
    }
    public void deleteFile(String directoryId, String fileName){
        try {
            FileDirectory directory = appService.findById(FileDirectory.class, directoryId);
            s3.deleteObject(directory.getDirectoryName(), fileName);
        } catch (AmazonServiceException e) {
            log.error(e.getErrorMessage());
        }
    }
}
