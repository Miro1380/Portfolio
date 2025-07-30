package com.Quotes.Quotes_API.repository;

import com.Quotes.Quotes_API.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote,Long> {
    //Add Custom queries.
    Page<Quote> findByCategoryId(Long categoryId, Pageable pageable);

    //Gets tag names for a quote from other tables.
    @Query("SELECT q FROM Quote q JOIN q.tags t WHERE t.name = :tagName")
    Page<Quote> findByTags_Name(@Param("tagName") String tagName, Pageable pageable);

    //Gets category_id from a quote by checking other table
    @Query("SELECT q.category.id FROM Quote q WHERE q.id = :quoteId")
    Optional<Long> findCategoryIdByQuoteId(@Param("quoteId") Long quoteId);

    //Find ONE RANDOM quote from db
    @Query(value = "SELECT * FROM quotes ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Quote> findRandomQuote();

    @Query(value = "SELECT * FROM quotes WHERE category_id = :categoryId ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Quote> findRandomQuoteByCategoryId(@Param("categoryId") Long categoryId);

    //Five and Ten Random
    @Query(value = "SELECT * FROM quotes ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<Quote> findFiveRandom();

    @Query(value = "SELECT * FROM quotes ORDER BY RANDOM() LIMIT 10", nativeQuery = true)
    List<Quote> findTenRandom();

    //Five and Ten By Category ID

    @Query(value = "SELECT * FROM quotes WHERE category_id = :categoryId ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<Quote> findFiveRandomByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT * FROM quotes WHERE category_id = :categoryId ORDER BY RANDOM() LIMIT 10", nativeQuery = true)
    List<Quote> findTenRandomByCategoryId(@Param("categoryId") Long categoryId);

    //Increment usage
    @Modifying
    @Transactional
    @Query("UPDATE Quote q SET q.usageCount = q.usageCount + 1 WHERE q.id = :id")
    void incrementUsageCount(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Quote q SET q.usageCount = q.usageCount + 1 WHERE q.id IN :ids")
    void incrementUsageCountBatch(@Param("ids") List<Long> ids);

}
