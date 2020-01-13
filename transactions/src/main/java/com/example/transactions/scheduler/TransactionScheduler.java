package com.example.transactions.scheduler;

import com.example.transactions.client.TransactionRestClient;
import com.example.transactions.service.TransactionQueueSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionScheduler {

    @Autowired
    private RetryTemplate retryTemplate;

    @Autowired
    private TransactionRestClient transactionRestClient;


    @Autowired
    private TransactionQueueSenderService transactionQueueSenderService;

    @Scheduled(cron = "10 0/2 * ? * *")
    public void fetchTransactions() {
        retryTemplate.execute(context -> {

            transactionRestClient.fetchAll().get().stream().forEach(file -> transactionQueueSenderService.send(file));

            return null;

        });

    }

}
