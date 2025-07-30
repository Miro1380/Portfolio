package com.Quotes.Quotes_API.DTO.Quote;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class QuoteLastUpdateResponse {
    private final LocalDateTime lastUpdated;
}
