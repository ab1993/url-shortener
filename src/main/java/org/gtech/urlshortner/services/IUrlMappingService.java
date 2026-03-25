package org.gtech.urlshortner.services;

import org.gtech.urlshortner.domain.response.UrlMappingResponse;
import org.gtech.urlshortner.domain.success.UrlGenerationSuccessModel;

public interface IUrlMappingService {
    UrlGenerationSuccessModel generateShortUrl(String url);
    UrlMappingResponse getLongUrl(String shortId);
}
