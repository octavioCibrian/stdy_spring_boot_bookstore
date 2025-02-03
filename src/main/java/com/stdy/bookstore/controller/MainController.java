package com.stdy.bookstore.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.ok("ok");
    }
}
