package com.lahu.ssl.demo.sub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ssl-sub")
public class AppController {

    @GetMapping("get/one")
    public ResponseEntity<String> testMethodOne(@RequestParam(name = "id", defaultValue = "") String id) {
        System.out.println("Sub Server.ID "+id);
        return ResponseEntity.ok("[ssl-sub/get/one called.] Received Id : " + id);
    }

}