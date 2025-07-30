package com.Quotes.Quotes_API.service.quote;

import com.Quotes.Quotes_API.DTO.Quote.QuoteAuthorSourceResponse;
import com.Quotes.Quotes_API.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IQuoteService {
    //CREATE
    Quote createQuote(Quote quote);

    //READ (GET)
    //List<Quote> getAllQuotes();
    Page<Quote> getAllQuotes(Pageable pageable);

    Optional<Quote> getQuote(Long id);

    //Random Focused
    Optional<Quote> getRandomQuote();
    Optional<Quote> getRandomQuoteByCategory(Long categoryId);


    Optional<List<Quote>> getFiveRandomQuotes();
    Optional<List<Quote>> getTenRandomQuotes();

    //Criteria instead of Quote ID
    Page<Quote> getQuotesByCategory(Long categoryId, Pageable pageable);
    Page<Quote> getQuotesByTagName(String tagName, Pageable pageable);

    Optional<List<Quote>> getFiveRandomByCategoryId(Long categoryId);
    Optional<List<Quote>> getTenRandomByCategoryId(Long categoryId);

    //Single Quote Focused
    Optional<String> getQuoteAuthor(Long id);
    Optional<String> getQuoteLanguage(Long id);
    Optional<String> getQuoteSource(Long id);
    Optional<Integer> getQuoteUsage(Long id);
    Optional<Long> getQuoteCategoryId(Long id);
    Optional<LocalDateTime> getQuoteLastUpdate(Long id);
    Optional<LocalDateTime> getQuoteDateAdded(Long id);

    Optional<QuoteAuthorSourceResponse> getQuoteAuthorAndSource(Long id);


    //UPDATE
    Quote updateQuote(Long id, Quote quote);

    //DELETE
    void deleteQuote(Long id);
}
