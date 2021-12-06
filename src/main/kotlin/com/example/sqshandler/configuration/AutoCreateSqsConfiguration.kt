package com.example.sqshandler.configuration

import com.example.sqshandler.model.QueueContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest


@Configuration
class AutoCreateSqsConfiguration {

    @Bean
    fun urlQueueName(@Value("\${sqs.queue}") queue: List<String>, sqsClient: SqsClient): QueueContext {

        val queues = queue.associateBy ({ it },{
            val createQueueRequest = CreateQueueRequest.builder()
                .queueName(it)
                .build()

            val url = sqsClient.createQueue(createQueueRequest).queueUrl()

            url
        })

        return QueueContext(queues)
    }
}