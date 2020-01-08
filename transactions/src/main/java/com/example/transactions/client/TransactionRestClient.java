package com.example.transactions.client;


import com.example.transactions.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public Optional<List<File>> fetchAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer 1234567890qwertyuiopasdfghjklzxcvbnm");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> result = restTemplate.exchange("https://increase-transactions.herokuapp.com/file.txt", HttpMethod.GET, entity, String.class);

        return proccessResponse(result.getBody());

    }

    private Optional<List<File>> proccessResponse(String body) {

        Pattern pattern = Pattern.compile("((1[a-z0-9]{32}[\\s]{3}[a-z0-9]{42}[\\n])(2[a-z0-9]{45}[\\s]{5}[\\d][\\n])+(3[a-z0-9]{45}[\\s]{3}[\\d][\\n])+(4[\\s]{15}[a-z0-9]{40}))+");

        List<File> files = Stream.of(body).
                map(pattern::matcher)
                .filter(Matcher::find)
                .map(matcher -> new File(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)))
                .collect(Collectors.toList());

        return Optional.of(files);
    }

}
