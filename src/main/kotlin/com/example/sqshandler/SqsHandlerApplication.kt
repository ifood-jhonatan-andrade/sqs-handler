package com.example.sqshandler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SqsHandlerApplication

fun main(args: Array<String>) {
    runApplication<SqsHandlerApplication>(*args)
}
