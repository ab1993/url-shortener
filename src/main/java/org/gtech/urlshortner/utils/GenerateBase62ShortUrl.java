package org.gtech.urlshortner.utils;

import org.gtech.urlshortner.domain.success.UrlGenerationSuccessModel;

public class GenerateBase62ShortUrl {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final SnowflakeGenerator generator = new SnowflakeGenerator(1);

    public UrlGenerationSuccessModel encodeUrlIntoBase62(UrlGenerationSuccessModel urlGenerationSuccessModel,String longUrl){
        if(verifyUrlIsValidOrNot(longUrl)){
            long id = generator.nextId();
            String shortId = encode(id);
            urlGenerationSuccessModel.setShortId(shortId);
            urlGenerationSuccessModel.setLongUrl(longUrl);
            urlGenerationSuccessModel.setSuccess(true);
        }else {
            urlGenerationSuccessModel.setError("Invalid URL");
            urlGenerationSuccessModel.setSuccess(false);
        }
        return urlGenerationSuccessModel;
    }

    boolean verifyUrlIsValidOrNot(String url){
        return url.startsWith("http:") || url.startsWith("https:");
    }

    public static String encode(long value) {
        if (value == 0) return "a";

        StringBuilder sb = new StringBuilder();

        while (value > 0) {
            int remainder = (int) (value % 62);
            sb.append(BASE62.charAt(remainder));
            value /= 62;
        }

        return sb.reverse().toString();
    }

}
