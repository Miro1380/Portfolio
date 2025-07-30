package com.Quotes.Quotes_API.controller;

import com.Quotes.Quotes_API.DTO.Category.CategoryDescriptionResponse;
import com.Quotes.Quotes_API.DTO.Category.CategoryNameResponse;

import com.Quotes.Quotes_API.exceptions.ResourceNotFoundException;
import com.Quotes.Quotes_API.model.Category;
import com.Quotes.Quotes_API.service.category.ICategoryService;
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

@RequestMapping("/api/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //GET Category By ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.getCategory(id);
        return category
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Get Category Name By ID
    @GetMapping("/{id}/name")
    public ResponseEntity<CategoryNameResponse> getCategoryNameById(@PathVariable Long id){
        Optional<String> categoryName = categoryService.getCategoryName(id);
        return categoryName
                .map( (value) -> new ResponseEntity<>( new CategoryNameResponse(value), HttpStatus.OK))
                .orElseGet( () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Get Category Description By ID. Uses POJO categoryDescriptionResponse
    @GetMapping("/{id}/description")
    public ResponseEntity<CategoryDescriptionResponse> getDescription(@PathVariable Long id){
        Optional<String> categoryDescription = categoryService.getCategoryDescription(id);
        return categoryDescription
                .map( (value) -> new ResponseEntity<>(new CategoryDescriptionResponse(value), HttpStatus.OK))
                .orElseGet( () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name){
        Optional<Category> categoryName = categoryService.getCategoryByName(name);
        return categoryName
                .map((value)-> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet( () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //TODO
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
