package com.Quotes.Quotes_API.DTO.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDescriptionResponse {
    private String description;

    public CategoryDescriptionResponse(String description){
        this.description = description;
    }

}
