package com.Quotes.Quotes_API.DTO.Tag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagNameResponse {
    private final String name;

    public TagNameResponse(String name){
        this.name = name;
    }
}
