package com.denysenko.messageprocessor.configs;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OpenAIConfig {

    @Value("${openai.key}")
    private String apiKey;
    @Value("${openai.timeout}")
    private long timeout;

    @Bean
    public OpenAiService initGptService(){
        var openAiService = new OpenAiService(apiKey, Duration.ofSeconds(timeout));
        return openAiService;
    }
}
