package com.diosaraiva.chatgpt.chatgpt.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatResponseDto {

    private List<ChoiceDto> choices;
}
