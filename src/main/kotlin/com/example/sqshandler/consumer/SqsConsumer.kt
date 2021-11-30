package com.example.sqshandler.consumer

import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class SqsConsumer {
    @SqsListener(value = ["DEV_IFOOD_CATALOG_PRODUCT_VIOLATION"])
    fun consumer(message: String, @Header("SenderId") senderId: String) {
        println("$senderId - $message")
    }
}