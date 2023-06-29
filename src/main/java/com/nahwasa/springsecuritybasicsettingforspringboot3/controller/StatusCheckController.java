package com.nahwasa.springsecuritybasicsettingforspringboot3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusCheckController {
/**
 * 상태 확인용 API
 * 그냥 정상적인 상태에선 OK 를 던져주는 역할
 * */
    @GetMapping
    public ResponseEntity<String> serverStatusCheck() {
        return ResponseEntity.ok("ok");
    }
}
