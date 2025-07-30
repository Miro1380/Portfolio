package com.Quotes.Quotes_API.service.tag;

import com.Quotes.Quotes_API.model.Tag;

import java.util.List;
import java.util.Optional;

public interface ITagService {
    List<Tag> getAllTags();
    Optional<Tag> getTag(Long id);
    Optional<String> getTagName(Long id);
    Optional<String> getSlug(Long id);
    Optional<String> getDescription(Long id);
    void deleteTag(Long id);
    Tag createTag(Tag tag);
    Tag updateTag(Long id, Tag tag);
}
