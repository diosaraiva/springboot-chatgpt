package com.diosaraiva.chatgpt.chatgpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.diosaraiva.chatgpt.chatgpt.dto.ChatRequestDto;
import com.diosaraiva.chatgpt.chatgpt.dto.ChatResponseDto;
import com.diosaraiva.chatgpt.chatgpt.service.OpenAIService;

@RestController
@RequestMapping("/chatgpt")
public class ChatController {

	@Autowired
	private OpenAIService openAIService;

	@Value("${openai.model}")
	private String model;

	@Value("${openai.api.url}")
	private String apiUrl;

	@GetMapping("/get")
	public ResponseEntity<?> getChatGPTResponse(String openaiApiKey, @RequestParam String prompt) {

		try {
			ChatRequestDto request = new ChatRequestDto(model, prompt);
			RestTemplate restTemplate = openAIService.openaiRestTemplate(openaiApiKey);
			ChatResponseDto response = restTemplate.postForObject(apiUrl, request, ChatResponseDto.class);

			if (response != null) {
				//return response.getChoices().get(0).getMessageDto().getContent();
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}		
	}
}
