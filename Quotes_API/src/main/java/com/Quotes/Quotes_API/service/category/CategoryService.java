package com.Quotes.Quotes_API.service.category;

import com.Quotes.Quotes_API.exceptions.ResourceNotFoundException;
import com.Quotes.Quotes_API.model.Category;
import com.Quotes.Quotes_API.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<String> getCategoryDescription(Long id) {
        return categoryRepository.findById(id).map(Category::getDescription);
    }

    @Override
    public Optional<String> getCategoryName(Long id) {
        return categoryRepository.findById(id).map(Category::getName);
    }

    @Override
    public Category createCategory(Category category) {
        //Add Validation & Check Input
        if(category == null || category.getName() == null || category.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        Category savedCategory = categoryRepository.save(category);
        log.info("Created Category with id {}", savedCategory.getId());

        return savedCategory;
    }

    //TODO
    @Override
    public Category updateCategory(Long id, Category category) {
        //Check for existing or throw custom error
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Category Not Found: "+ id));

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        //Retrieve category wrapped in optional
        categoryRepository
                .findById(id)
                .ifPresentOrElse(categoryRepository :: delete, () -> {throw new ResourceNotFoundException("Category not Found");});
        log.debug("Deleted: {}", id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
