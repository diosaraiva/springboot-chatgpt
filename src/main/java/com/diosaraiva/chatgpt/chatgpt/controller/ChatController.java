package com.diosaraiva.chatgpt.chatgpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.diosaraiva.chatgpt.chatgpt.dto.ChatRequestDto;
import com.diosaraiva.chatgpt.chatgpt.dto.ChatResponseDto;
import com.diosaraiva.chatgpt.chatgpt.service.OpenAIService;
import com.diosaraiva.chatgpt.utils.YamlPropertySourceFactory;

@RestController
@RequestMapping("/chatgpt")
@PropertySource(value = "openai/openai.yaml", factory = YamlPropertySourceFactory.class)
public class ChatController {
    
    @Autowired
    private OpenAIService openAIService;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;
    
    @GetMapping("/chat")
    public String chat(String openaiApiKey, @RequestParam String prompt) {
        // create a request
        ChatRequestDto request = new ChatRequestDto(model, prompt);
        
        RestTemplate restTemplate = openAIService.openaiRestTemplate(openaiApiKey);
        ChatResponseDto response = restTemplate.postForObject(apiUrl, request, ChatResponseDto.class);
        
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        
        // return the first response - TODO
        return response.getChoices().get(0).getMessageDto().getContent();
    }
}