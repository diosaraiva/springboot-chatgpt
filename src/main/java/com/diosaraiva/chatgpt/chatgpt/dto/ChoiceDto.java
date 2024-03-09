package com.diosaraiva.chatgpt.chatgpt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChoiceDto {

	private int index;
	private MessageDto messageDto;
}
