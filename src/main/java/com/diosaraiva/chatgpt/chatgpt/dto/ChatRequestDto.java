package com.diosaraiva.chatgpt.chatgpt.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatRequestDto {

    private String model;
    private List<MessageDto> messages;
    private int n;
    private double temperature;

    public ChatRequestDto(String model, String prompt) {
        this.model = model;
        
        this.messages = new ArrayList<>();
        this.messages.add(new MessageDto("user", prompt));
    }
}
