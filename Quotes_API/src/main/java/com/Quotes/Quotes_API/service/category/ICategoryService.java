package com.Quotes.Quotes_API.service.category;

import com.Quotes.Quotes_API.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService  {
    //Consider Optional return type for retrieving or asking db.

    List<Category> getAllCategories();
    Optional<Category> getCategory(Long id);
    Optional<String> getCategoryDescription(Long id);
    Optional<String> getCategoryName(Long id);
    Category createCategory(Category category);
    Category updateCategory (Long id, Category category);
    void deleteCategory(Long id);
    Optional<Category> getCategoryByName(String name);
}
