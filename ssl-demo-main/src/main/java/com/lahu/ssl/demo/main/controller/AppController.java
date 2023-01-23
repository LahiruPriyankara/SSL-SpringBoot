package com.lahu.ssl.demo.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("ssl-main")
public class AppController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("get/one")
    public ResponseEntity<String> testMethodOne() {
        return ResponseEntity.ok("[ssl-main/get/one called.]");
    }

    @GetMapping("get/two")
    public ResponseEntity<String> testMethodTwo(@RequestParam(name = "id", defaultValue = "") String id) {

        System.out.println("Main Server.ID "+id);
        String url = "https://localhost:444/ssl-sub/get/one?id={id}";

        String responseString = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity requestEntity = new HttpEntity<>(headers);
            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("id", id);

            responseString =
                    restTemplate
                            .exchange(
                                    url,
                                    HttpMethod.GET,
                                    requestEntity,
                                    String.class,
                                    uriVariables)
                            .getBody();

            if (responseString == null) {
                System.out.println("Null response fetch.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when calling."+e);
        }

        System.out.println("Response : " + responseString);
        return ResponseEntity.ok("[ssl-main/get/two called.] Response from second service :" + responseString);
    }


}
