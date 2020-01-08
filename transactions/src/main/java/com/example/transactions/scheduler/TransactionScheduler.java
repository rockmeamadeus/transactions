package com.example.transactions.scheduler;

import com.example.transactions.client.TransactionRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

@Service
public class TransactionScheduler {

    @Autowired
    private RetryTemplate retryTemplate;

    private static int number = 0;

    @Autowired
    private TransactionRestClient transactionRestClient;


    @Scheduled(cron = "0/1 1/10 * ? * *")
    public void fetchTransactions() {
        retryTemplate.execute(context -> {

            System.out.println("-----------------" + LocalDateTime.now() + "RETRY: " + ++number + "--------------");

            System.out.println(transactionRestClient.fetchAll());

            number = 0;

            return null;

        });

    }

}
