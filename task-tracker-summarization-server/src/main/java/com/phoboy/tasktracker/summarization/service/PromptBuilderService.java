package com.phoboy.tasktracker.summarization.service;

import com.phoboy.tasktracker.shared.dto.SummarizationRequestDto;
import com.phoboy.tasktracker.summarization.constants.PromptConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PromptBuilderService {


    public String generatePrompt(SummarizationRequestDto requestDto) {

        String promptBody = requestDto.toString();
        return PromptConstants.PROMPT_HEADER + promptBody;

    }
}
