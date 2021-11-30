package com.example.sqshandler.services

import com.example.sqshandler.model.QueueContext
import com.google.gson.Gson
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.ListQueuesRequest
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*


@Component
class SqsService (
    private val sqsClient: SqsClient,
    private val queueContext: QueueContext
    ){

    fun parseMessage(payload: Map<String, Any>): String? {
        val message = mutableMapOf<String, Any>()

        message["createdAt"] = DateTimeFormatter.ISO_INSTANT.format(Instant.now()) as Any
        message["body"] = payload as Any
        message["id"] = UUID.randomUUID() as Any

        return Gson().toJson(message).toString()
    }

    fun sendMessage(jsonString: Map<String, Any>, queueName: String) {
        val request = SendMessageRequest.builder()
            .queueUrl(queueContext.queue[queueName])
            .messageBody(parseMessage(jsonString))
            .build()

        sqsClient.sendMessage(request)
    }

    fun getQueue(prefix: String): List<String> {
        val request = ListQueuesRequest.builder().queueNamePrefix(prefix).build()
        return sqsClient.listQueues(request).queueUrls()
    }
}