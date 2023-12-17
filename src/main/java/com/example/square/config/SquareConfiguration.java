package com.example.square.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SquareConfiguration {

    @Value("${SQUARE_ACCESS_TOKEN}")
    private String accessToken;
}
