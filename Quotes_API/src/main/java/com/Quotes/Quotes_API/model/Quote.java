package com.Quotes.Quotes_API.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name="quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT", unique = true)
    private String quote;

    private String author;
    private String source;
    private String language;

    @Column(name = "usage_count")
    private Integer usageCount= 0;

    @Column(name = "date_added")
    private LocalDateTime dateAdded = LocalDateTime.now();

    @Column(name = "last_update")
    private LocalDateTime lastUpdated = LocalDateTime.now();

    private String status; //consider using enum ??

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})

    @JoinTable(
            name = "quote_tags",
            joinColumns = {@JoinColumn(name="quote_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote)) return false;
        Quote quote = (Quote) o;
        return id != null && id.equals(quote.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode(); // Or just 31
    }
}
