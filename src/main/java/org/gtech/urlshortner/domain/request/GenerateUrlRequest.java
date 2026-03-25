package org.gtech.urlshortner.domain.request;

import lombok.Data;

@Data
public class GenerateUrlRequest {
    private String longUrl;
}
