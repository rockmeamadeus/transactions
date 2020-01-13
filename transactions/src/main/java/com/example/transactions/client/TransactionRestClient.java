package com.example.transactions.client;


import com.example.transactions.model.File;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class TransactionRestClient {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(TransactionRestClient.class);


    public Optional<List<File>> fetchAll() {
        logger.info("Entering to fetchAll");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer 1234567890qwertyuiopasdfghjklzxcvbnm");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> result = restTemplate.exchange("https://increase-transactions.herokuapp.com/file.txt", HttpMethod.GET, entity, String.class);

        return proccessfetchAllResponse(result.getBody());

    }

    private Optional<List<File>> proccessfetchAllResponse(String body) {

        Pattern pattern = Pattern.compile("(1[\\S]{32}[\\s]{3}[\\S]{42}[\\n])((2[\\S]{45}[\\s]{5}[\\S]{1}[\\n])+)((3[\\S]{45}[\\s]{3}[\\d][\\n])+)(4[\\s]{15}[\\S]{40})");


        List<File> files = Stream.of(body)
                .map(x -> new Scanner(x).findAll(pattern)
                        .map(m -> new File(m.group(1), m.group(2), m.group(5), m.group(6)))
                        .collect(Collectors.toList())
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return Optional.ofNullable(files);
    }

}
