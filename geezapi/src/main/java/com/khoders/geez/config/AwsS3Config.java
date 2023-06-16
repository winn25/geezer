package com.khoders.geez.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config extends AwsProps {
    @Bean
    public AmazonS3 s3Client(){
        final BasicAWSCredentials awsCredentials = new BasicAWSCredentials(getAccessKey(),getSecreteKey());
        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(getS3region()).build();
    }
}
