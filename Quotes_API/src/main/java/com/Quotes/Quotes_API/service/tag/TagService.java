package com.Quotes.Quotes_API.service.tag;

import com.Quotes.Quotes_API.DTO.Tag.TagNameResponse;
import com.Quotes.Quotes_API.exceptions.DuplicateResourceException;
import com.Quotes.Quotes_API.exceptions.ResourceNotFoundException;
import com.Quotes.Quotes_API.model.Tag;
import com.Quotes.Quotes_API.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagService implements ITagService{
    private final TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> getTag(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Optional<String> getTagName(Long id) {
        return tagRepository.findById(id).map(Tag :: getName);
    }

    @Override
    public Optional<String> getSlug(Long id) {
        return tagRepository.findById(id).map( Tag :: getSlug);
    }

    @Override
    public Optional<String> getDescription(Long id) {
        return tagRepository.findById(id).map( Tag :: getDescription);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.findById(id)
                .ifPresentOrElse(tagRepository :: delete , () -> { throw new ResourceNotFoundException("Not found");
                });
    }

    //Creates a new tag from the input tag and saves it to db after null check
    @Override
    public Tag createTag(Tag tag) {
        if(tag == null || tag.getName() == null || tag.getName().trim().isEmpty()){
            throw  new IllegalArgumentException("Tag name cannot be null or empty");
        }

        if(tagRepository.findByName(tag.getName()).isPresent()){
            throw new DuplicateResourceException("Tag with name '"+ tag.getName() + "' already exists");
        }

        Tag savedTag = tagRepository.save(tag);
        log.info("Created Tag with name: {}", savedTag.getName());

        return savedTag;
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        if(id == null || tag.getName() == null || tag.getName().trim().isEmpty()){
            throw new ResourceNotFoundException("Id tag and name cannot be null");
        }

        Tag updatedTag = tagRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Tag cannot be located"));
        updatedTag.setName(tag.getName());
        updatedTag.setDescription(tag.getDescription());
        updatedTag.setSlug(tag.getSlug());
        return tagRepository.save(updatedTag);

    }
}
