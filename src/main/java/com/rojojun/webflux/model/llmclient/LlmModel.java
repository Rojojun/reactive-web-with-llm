package com.rojojun.webflux.model.llmclient;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LlmModel {
    GPT_4O("gpt-4o", LlmType.GPT),
    GEMINI_3_0_FLASH("gemini-3.0-flash", LlmType.GEMINI);

    private final String code;
    private final LlmType llmType;

    LlmModel(String code, LlmType llmType) {
        this.code = code;
        this.llmType = llmType;
    }

    @JsonValue
    @Override
    public String toString() {
        return code;
    }
}
