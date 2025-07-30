package com.Quotes.Quotes_API.repository;

import com.Quotes.Quotes_API.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName(String name);
}
