package com.Quotes.Quotes_API.DTO.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryNameResponse {
    private String name;

    public CategoryNameResponse(String name){
        this.name = name;
    }

}
