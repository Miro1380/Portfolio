package com.Quotes.Quotes_API.controller;

import com.Quotes.Quotes_API.DTO.Tag.TagDescriptionResponse;
import com.Quotes.Quotes_API.DTO.Tag.TagNameResponse;
import com.Quotes.Quotes_API.DTO.Tag.TagSlugResponse;
import com.Quotes.Quotes_API.exceptions.ResourceNotFoundException;
import com.Quotes.Quotes_API.model.Tag;
import com.Quotes.Quotes_API.service.tag.ITagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor

@RequestMapping("/api/tags")
public class TagController {
    private final ITagService tagService;

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(){
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable Long id){
        Optional<Tag> tag = tagService.getTag(id);
        return tag
                .map( (value) -> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet( () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<TagNameResponse> getTagName(@PathVariable Long id){
        Optional<String> tagName = tagService.getTagName(id);
        return tagName.map( (value) -> new ResponseEntity<>( new TagNameResponse(value),HttpStatus.OK))
                .orElseGet( () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/slug")
    public ResponseEntity<TagSlugResponse> getTagSlug(@PathVariable Long id){
        Optional<String> tagOptional = tagService.getSlug(id);
        return tagOptional
                .map((value) -> new ResponseEntity<>(new TagSlugResponse(value), HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/description")
    public ResponseEntity<TagDescriptionResponse> getTagDescription(@PathVariable Long id){
        Optional<String> tagDescription = tagService.getDescription(id);
        return tagDescription
                .map( (value) -> new ResponseEntity<>(new TagDescriptionResponse(value), HttpStatus.OK))
                .orElseGet( () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
