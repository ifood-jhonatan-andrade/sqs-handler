package com.example.sqshandler.controller

import com.example.sqshandler.services.SqsService
import com.google.gson.Gson
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sqs")
class SqsController (
    private val sqsService: SqsService
        ) {


    @GetMapping
    fun index(
        @RequestParam prefix: String
    ): List<String> {
        return sqsService.getQueue(prefix)
    }

    @PostMapping
    fun store(
        @RequestBody body: Map<String, Any>, @RequestParam queue: String
    ) {

        sqsService.sendMessage(body, queue)
    }
}