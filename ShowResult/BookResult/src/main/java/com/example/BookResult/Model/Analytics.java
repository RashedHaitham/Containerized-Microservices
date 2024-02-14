package com.example.BookResult.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Analytics")
public class Analytics {

    private Double maxPrice;

    private Double minPrice;

    private Double averagePrice;

    private Integer numberOfBooks;

    private Date lastUpdated;
}
