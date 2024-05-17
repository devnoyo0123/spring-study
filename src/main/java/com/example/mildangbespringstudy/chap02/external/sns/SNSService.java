package com.example.mildangbespringstudy.chap02.external.sns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SNSService {

    public void send(String message) {
        log.info("Send message: {}", message);
    }
}
