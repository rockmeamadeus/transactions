package com.example.transactions.service;

import com.example.transactions.model.File;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TransactionQueueSenderService {

    private final RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(TransactionQueueSenderService.class);


    @Autowired
    public TransactionQueueSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object file) {
        logger.info("Entering to send with the message: " + file.toString());
        this.rabbitTemplate.convertAndSend("test-exchange", "mykey", file);
    }
}
