package com.poc.dynamo.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class DynamoDBConfig {

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AwsClientBuilder.EndpointConfiguration config){
        return AmazonDynamoDBAsyncClientBuilder.standard()
                .withEndpointConfiguration(config)
                .withClientConfiguration(new ClientConfiguration()).build();
    }

    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfiguration(){
        return new AwsClientBuilder.EndpointConfiguration("http://172.28.5.10:4569","us-east-1");
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(final AmazonDynamoDB amazonDynamoDB){
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
