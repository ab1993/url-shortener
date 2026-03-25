package org.gtech.urlshortner.domain.success;

import lombok.Data;

@Data
public class UrlGenerationSuccessModel {
    private String shortId;
    private String shortUrl;
    private String longUrl;
    private String error;
    private boolean success;
}
