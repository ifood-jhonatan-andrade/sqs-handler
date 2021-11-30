package com.example.sqshandler.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

@Configuration
class SqsClientConfiguration {
    @Profile("local")
    @Bean
    fun sqsQueue(): SqsClient? {
        return SqsClient.builder()
            .endpointOverride(URI.create("http://localhost:4566"))
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(object: AwsCredentials {
                override fun accessKeyId(): String {
                    return "FAKE";
                }

                override fun secretAccessKey(): String {
                    return "FAKE";
                }
            }))
            .build()
    }


}