package com.Quotes.Quotes_API.service.quote;

import com.Quotes.Quotes_API.DTO.Quote.QuoteAuthorSourceResponse;
import com.Quotes.Quotes_API.exceptions.ResourceNotFoundException;
import com.Quotes.Quotes_API.model.Category;
import com.Quotes.Quotes_API.model.Quote;
import com.Quotes.Quotes_API.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional

public class QuoteService implements IQuoteService{
    private final QuoteRepository quoteRepository;

    //CREATE METHODS
    @Override
    public Quote createQuote(Quote quote) {

        Quote savedQuote = quoteRepository.save(quote);
        log.info("Created quote with ID: {}", savedQuote.getId());
        return savedQuote;
    }

    //READ METHODS (Gets)

    @Override
    @Transactional
    public Page<Quote> getAllQuotes(Pageable pageable) {
        Page<Quote> pageQuotes = quoteRepository.findAll(pageable);
        updateUsageForBatch(pageQuotes.getContent());
        return pageQuotes;
    }

    //Get a single quote if they have ID. For cases when they want to revisit quote
    @Override
    @Transactional
    public Optional<Quote> getQuote(Long id) {
        Optional<Quote> quoteOptional = quoteRepository.findById(id);
        quoteOptional.ifPresent((quote) -> quoteRepository.incrementUsageCount(quote.getId()));
        return quoteOptional;
    }

    //Get a SINGLE quote at RANDOM
    @Override
    @Transactional
    public Optional<Quote> getRandomQuote() {
        Optional<Quote> quoteOptional = quoteRepository.findRandomQuote();
        quoteOptional.ifPresent(quote -> quoteRepository.incrementUsageCount(quote.getId()));
        return quoteOptional;
    }

    //Get a SINGLE Quote by CATEGORY input
    @Override
    @Transactional
    public Optional<Quote> getRandomQuoteByCategory(Long categoryId) {
        Optional<Quote> quoteOptional = quoteRepository.findRandomQuoteByCategoryId(categoryId);
        quoteOptional.ifPresent(quote -> quoteRepository.incrementUsageCount(quote.getId()));
        return quoteOptional;
    }

    @Override
    @Transactional
    public Optional<List<Quote>> getFiveRandomQuotes() {
        List<Quote> fiveQuotes = quoteRepository.findFiveRandom();
        updateUsageForBatch(fiveQuotes);
        return Optional.ofNullable(fiveQuotes);
    }

    @Override
    @Transactional
    public Optional<List<Quote>> getTenRandomQuotes() {
        List<Quote> tenQuotes = quoteRepository.findTenRandom();
        updateUsageForBatch(tenQuotes);
        return Optional.ofNullable(tenQuotes);
    }

    //GET Quotes other than Quote ID

    @Override
    @Transactional
    public Page<Quote> getQuotesByCategory(Long categoryId, Pageable pageable) {
        Page<Quote> pageQuotes = quoteRepository.findByCategoryId(categoryId,pageable);
        updateUsageForBatch(pageQuotes.getContent());
        return pageQuotes;
    }



    @Override
    @Transactional
    public Page<Quote> getQuotesByTagName(String tagName, Pageable pageable) {
        Page<Quote> pageQuotes = quoteRepository.findByTags_Name(tagName,pageable);
        updateUsageForBatch(pageQuotes.getContent());
        return pageQuotes;
    }


    @Override
    @Transactional
    public Optional<List<Quote>> getFiveRandomByCategoryId(Long categoryId) {
        List<Quote> fiveRandomQuotes = quoteRepository.findFiveRandomByCategoryId(categoryId);
        updateUsageForBatch(fiveRandomQuotes);
        return Optional.ofNullable(fiveRandomQuotes);
    }

    @Override
    @Transactional
    public Optional<List<Quote>> getTenRandomByCategoryId(Long categoryId) {
        List<Quote> tenRandomQuotes = quoteRepository.findTenRandomByCategoryId(categoryId);
        updateUsageForBatch(tenRandomQuotes);
        return Optional.ofNullable(tenRandomQuotes);
    }

    @Override
    @Transactional
    public Optional<String> getQuoteAuthor(Long id) {
        return quoteRepository.findById(id).map(Quote::getAuthor);
    }

    @Override
    public Optional<String> getQuoteLanguage(Long id) {
        return quoteRepository.findById(id).map(Quote::getLanguage);
    }

    @Override
    public Optional<String> getQuoteSource(Long id) {
        return quoteRepository.findById(id).map(Quote::getSource);
    }

    @Override
    public Optional<Integer> getQuoteUsage(Long id) {
        return quoteRepository.findById(id).map(Quote::getUsageCount);
    }

    @Override
    public Optional<Long> getQuoteCategoryId(Long id) {
        return quoteRepository.findCategoryIdByQuoteId(id);
    }

    @Override
    public Optional<LocalDateTime> getQuoteLastUpdate(Long id) {
        return quoteRepository.findById(id).map(Quote ::getLastUpdated);
    }

    @Override
    public Optional<LocalDateTime> getQuoteDateAdded(Long id) {
        return quoteRepository.findById(id).map(Quote::getDateAdded);
    }

    @Override
    public Optional<QuoteAuthorSourceResponse> getQuoteAuthorAndSource(Long id) {
        Optional<Quote> qAuthorSourceResponse= quoteRepository.findById(id);

        return qAuthorSourceResponse.map((quote)-> {
            String author = quote.getAuthor();
            String source = quote.getSource();
            return new QuoteAuthorSourceResponse(author,source);
        });
    }

    //findById return is an optional type
    @Override
    @Transactional
    public Quote updateQuote(Long id, Quote quote) {
        Quote existingQuote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found with id:"+ id));

        //Update Properties of Quote
        existingQuote.setQuote(quote.getQuote());
        existingQuote.setAuthor(quote.getAuthor());
        existingQuote.setSource(quote.getSource());
        existingQuote.setLanguage(quote.getLanguage());
        existingQuote.setUsageCount(quote.getUsageCount());
        //existingQuote.setDateAdded(quote.getDateAdded());
        existingQuote.setLastUpdated(quote.getLastUpdated());
        existingQuote.setStatus(quote.getStatus());
        existingQuote.setCategory(quote.getCategory());
        existingQuote.setTags(quote.getTags());

        //Save to Quote repository
        Quote savedQuote = quoteRepository.save(existingQuote);
        log.info("Updated quote with id: {}", id);

        return savedQuote;
    }

    @Override
    public void deleteQuote(Long id) {
        if(!quoteRepository.existsById(id)){
            throw new ResourceNotFoundException("Quote not found with id: "+ id);
        }
        quoteRepository.deleteById(id);
        log.info("Delete quote with ID: {}", id);
    }

    //Helper function to update
    @Transactional
    private void updateUsageForBatch(List<Quote> quotes) {
        if (quotes != null && !quotes.isEmpty()) {
            List<Long> quoteIds = quotes.stream()
                    .filter(quote -> quote != null)
                    .map(Quote::getId)
                    .collect(Collectors.toList());
            if (!quoteIds.isEmpty()) {
                quoteRepository.incrementUsageCountBatch(quoteIds);
            }
        }
    }
}
