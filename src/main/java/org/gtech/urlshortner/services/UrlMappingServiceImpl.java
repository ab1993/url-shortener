package org.gtech.urlshortner.services;

import lombok.extern.slf4j.Slf4j;
import org.gtech.urlshortner.domain.UrlMapping;
import org.gtech.urlshortner.domain.response.UrlMappingResponse;
import org.gtech.urlshortner.domain.success.UrlGenerationSuccessModel;
import org.gtech.urlshortner.repositories.UrlMappingRepository;
import org.gtech.urlshortner.utils.GenerateBase62ShortUrl;
import org.gtech.urlshortner.utils.SnowflakeGenerator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UrlMappingServiceImpl implements IUrlMappingService{

    private final UrlMappingRepository urlMappingRepository;
    private GenerateBase62ShortUrl generateBase62ShortUrl = new GenerateBase62ShortUrl();
    private static final String CACHE_NAME = "shortId";

    public UrlMappingServiceImpl(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }


    @Override
    public UrlGenerationSuccessModel generateShortUrl(String url) {
        log.info("generateShortUrl() invoked:");
        log.debug("Started generating url - {}",url);
        UrlGenerationSuccessModel urlGenerationSuccessModel = new UrlGenerationSuccessModel();
        UrlMapping urlMapping = new UrlMapping();
        if(null == url){
            urlGenerationSuccessModel.setError("Invalid URL");
            return urlGenerationSuccessModel;
        }
        UrlGenerationSuccessModel result = generateBase62ShortUrl.encodeUrlIntoBase62(urlGenerationSuccessModel,url);
        if(result!=null && result.isSuccess()){
            urlMapping.setLongUrl(url);
            urlMapping.setId(result.getShortId());
            urlMapping.setCreateDate(LocalDateTime.now());
           urlMappingRepository.save(urlMapping);
        }
        return result;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#shortId")
    public UrlMappingResponse getLongUrl(String shortId) {
        log.info("getLongUrl() invoked:");
        log.debug("Started getting long url for id- {}",shortId);
        UrlMappingResponse urlMappingResponse = new UrlMappingResponse();
        if(null == shortId){
            urlMappingResponse.setSuccess(false);
            urlMappingResponse.setError("Short URL not found");
        }

        // find the url based on id
        UrlMapping longUrl = urlMappingRepository.findUrlMappingById(shortId);

        if(null == longUrl){
            urlMappingResponse.setSuccess(false);
            urlMappingResponse.setError("Short URL not found");
            return urlMappingResponse;
        }

        if(longUrl.getLongUrl()!=null){
            urlMappingResponse.setLongUrl(longUrl.getLongUrl());
            urlMappingResponse.setSuccess(true);
        }

        return urlMappingResponse;
    }
}
