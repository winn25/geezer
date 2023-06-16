package com.khoders.geez.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.khoders.geez.config.AwsProps;
import com.khoders.geez.dto.FileDirectoryDto;
import com.khoders.geez.entities.FileDirectory;
import com.khoders.geez.mapper.FileMapper;
import com.khoders.resource.utilities.SystemUtils;
import com.khoders.springapi.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class AwsS3Service extends AwsProps {
    private static final Logger log = LoggerFactory.getLogger(AwsS3Service.class);
    @Autowired private AmazonS3 s3;

    public String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        return s3.generatePresignedUrl(getS3BucketName(), fileName, calendar.getTime(), httpMethod).toString();
    }
    public String findByName(String fileName) {
        if (!s3.doesObjectExist(getS3BucketName(), fileName))
            return "File does not exist";
        log.info("Generating signed URL for file name {}", fileName);
        return generateUrl(fileName, HttpMethod.GET);
    }
    public String signedUrl(String extension) {
        String fileName = SystemUtils.generateCode() + extension;
        log.info("filename: {} ",fileName);
        return generateUrl(fileName, HttpMethod.PUT);
    }
}
