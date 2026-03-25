package org.gtech.urlshortner.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("urlMapping")
@Data
public class UrlMapping {
    @Id
    private String id;
    private String longUrl;
    LocalDateTime createDate;
}
