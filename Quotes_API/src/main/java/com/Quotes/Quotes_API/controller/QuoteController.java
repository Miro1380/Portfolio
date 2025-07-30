package com.Quotes.Quotes_API.controller;

import com.Quotes.Quotes_API.DTO.Quote.*;
import com.Quotes.Quotes_API.exceptions.ResourceNotFoundException;
import com.Quotes.Quotes_API.model.Quote;
import com.Quotes.Quotes_API.service.quote.IQuoteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
public class QuoteController {
    private final IQuoteService quoteService;

    @GetMapping("/quote/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id){
        Optional<Quote> quote = quoteService.getQuote(id);
        return quote
                .map((value) -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Random Single Quote
    @GetMapping("/random/one")
    public ResponseEntity<Quote> getRandomQuote(){
        Optional<Quote> randomQuote = quoteService.getRandomQuote();
        return randomQuote
                .map((value)-> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //GET Five Random Quotes
    @GetMapping("/random/five")
    public ResponseEntity<List<Quote>> getFiveRandomQuotes(){
        Optional<List<Quote>> fiveRandom = quoteService.getFiveRandomQuotes();
        return fiveRandom
                .map((value)-> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //GET Ten (10) Random Quotes
    @GetMapping("/random/ten")
    public ResponseEntity<List<Quote>> getTenRandomQuotes(){
        Optional<List<Quote>> tenRandom = quoteService.getTenRandomQuotes();
        return tenRandom
                .map((value)-> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Gets Quotes by Category ID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<Quote>> getQuotesByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        Page<Quote> quotePage = quoteService.getQuotesByCategory(categoryId, pageable);
        return ResponseEntity.ok(quotePage);
    }

    @GetMapping("/tag/{tagName}")
    public ResponseEntity<Page<Quote>> getQuotesByTagName(
            @PathVariable String tagName,@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction){

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortDirection,sort));
        Page<Quote> quotePage = quoteService.getQuotesByTagName(tagName,pageable);
        return ResponseEntity.ok(quotePage);
    }

    //GET FIVE (5) Random quotes based on Category
    @GetMapping("/category/{categoryId}/random/five")
    public ResponseEntity<List<Quote>> getFiveRandomByCategoryId(@PathVariable Long categoryId){
        Optional<List<Quote>> fiveRandomOptional = quoteService.getFiveRandomByCategoryId(categoryId);
        return fiveRandomOptional
                .map((value)-> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    //GET TEN (10) Random quotes based on Category
    @GetMapping("/category/{categoryId}/random/ten")
    public ResponseEntity<List<Quote>> getTenRandomByCategoryId(@PathVariable Long categoryId){
        Optional<List<Quote>> tenRandomOptional = quoteService.getTenRandomByCategoryId(categoryId);
        return tenRandomOptional
                .map((value)-> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/quote/{id}/author")
    public ResponseEntity<QuoteAuthorResponse> getQuoteAuthor(@PathVariable Long id){
        Optional<String> authorOptional = quoteService.getQuoteAuthor(id);
        return authorOptional
                .map((value) -> new ResponseEntity<>(new QuoteAuthorResponse(value),HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/quote/{id}/language")
    public ResponseEntity<QuoteLanguageResponse> getQuoteLanguage(@PathVariable Long id){
        Optional<String> quoteLangOptional = quoteService.getQuoteLanguage(id);
        return quoteLangOptional
                .map((value)-> new ResponseEntity<>(new QuoteLanguageResponse(value),HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));}


    @GetMapping("/quote/{id}/source")
    public ResponseEntity<QuoteSourceResponse> getQuoteSource(@PathVariable Long id){
        Optional<String> quoteSourceOptional = quoteService.getQuoteSource(id);
        return quoteSourceOptional
                .map((value) -> new ResponseEntity<>(new QuoteSourceResponse(value),HttpStatus.OK))
                .orElseGet( () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/quote/{id}/usage")
    public ResponseEntity<QuoteUsageResponse> getQuoteUsage(@PathVariable Long id){
        Optional<Integer> quoteUsageOptional = quoteService.getQuoteUsage(id);
        return quoteUsageOptional
                .map((value) -> new ResponseEntity<>(new QuoteUsageResponse(value),HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/quote/{id}/category")
    public ResponseEntity<QuoteCategoryResponse> getQuoteCategoryId(@PathVariable Long id){
        Optional<Long> quoteCategoryOptional = quoteService.getQuoteCategoryId(id);
        return quoteCategoryOptional
                .map((value) -> new ResponseEntity<>(new QuoteCategoryResponse(value),HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/quote/{id}/lastUpdate")
    public ResponseEntity<QuoteLastUpdateResponse> getLastUpdate(@PathVariable Long id){
        Optional<LocalDateTime> localTimeOptional = quoteService.getQuoteLastUpdate(id);
        return localTimeOptional
                .map((value)-> new ResponseEntity<>(new QuoteLastUpdateResponse(value),HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/quote/{id}/dateAdded")
    public ResponseEntity<QuoteDateAddedResponse> getDateAdded(@PathVariable Long id){
        Optional<LocalDateTime> dateAddedOptional = quoteService.getQuoteDateAdded(id);
        return dateAddedOptional
                .map((value) -> new ResponseEntity<>(new QuoteDateAddedResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("quote/{id}/author-source")
    public ResponseEntity<QuoteAuthorSourceResponse> getQuoteAuthorAndSource(@PathVariable Long id){
        Optional<QuoteAuthorSourceResponse> quoteAuthorSourceOptional = quoteService.getQuoteAuthorAndSource(id);
        return quoteAuthorSourceOptional
                .map((value)-> new ResponseEntity<>(new QuoteAuthorSourceResponse(value.getAuthor(), value.getSource()),HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/quote/{id}/update")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody Quote quote){
        Quote updateQuote = quoteService.updateQuote(id,quote);
        return new ResponseEntity<>(updateQuote,HttpStatus.OK);
    }

    @DeleteMapping("/quote/{id}")
    public ResponseEntity<HttpStatus> deleteQuote(@PathVariable Long id){
        quoteService.deleteQuote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpStatus> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Quote not found
    }
}
