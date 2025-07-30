package com.Quotes.Quotes_API.DTO.Quote;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuoteAuthorSourceResponse {
    private final String author;
    private final String source;
}
