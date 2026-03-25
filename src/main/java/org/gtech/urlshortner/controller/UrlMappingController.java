package org.gtech.urlshortner.controller;

import org.gtech.urlshortner.domain.request.GenerateUrlRequest;
import org.gtech.urlshortner.domain.response.UrlMappingResponse;
import org.gtech.urlshortner.domain.success.UrlGenerationSuccessModel;
import org.gtech.urlshortner.services.UrlMappingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/gtech/v1")
public class UrlMappingController {

    private final UrlMappingServiceImpl urlMappingService;

    public UrlMappingController(UrlMappingServiceImpl urlMappingService) {
        this.urlMappingService = urlMappingService;
    }

    @PostMapping("/shorten")
    ResponseEntity<UrlGenerationSuccessModel> generateShortenUrl(@RequestBody GenerateUrlRequest request){
        UrlGenerationSuccessModel urlGenerationSuccessModel = urlMappingService.generateShortUrl(request.getLongUrl());
        if(!urlGenerationSuccessModel.isSuccess()){
            return new ResponseEntity<>(urlGenerationSuccessModel,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(urlGenerationSuccessModel,HttpStatus.CREATED);
    }

    @GetMapping("/{shortId}")
    ResponseEntity<UrlMappingResponse> getLongUrl(@PathVariable String shortId){
         UrlMappingResponse urlMappingResponse = urlMappingService.getLongUrl(shortId);
         if(Boolean.FALSE.equals(urlMappingResponse.getSuccess())){
             return new ResponseEntity<>(urlMappingResponse,HttpStatus.NOT_FOUND);
         }

        return new ResponseEntity<>(urlMappingResponse,HttpStatus.FOUND);
                /*ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlMappingResponse.getLongUrl()))
                .build();*/
    }
}
