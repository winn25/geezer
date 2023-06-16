package com.khoders.geez.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
@PropertySource("classpath:aws-cloud.properties")
public class AwsProps {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secreteKey;
    @Value("${cloud.aws.region.static}")
    private String s3region;

    @Value("${application.bucket.name}")
    private String s3BucketName;

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecreteKey() {
        return secreteKey;
    }

    public String getS3region() {
        return s3region;
    }

    public String getS3BucketName() {
        return s3BucketName;
    }
}
