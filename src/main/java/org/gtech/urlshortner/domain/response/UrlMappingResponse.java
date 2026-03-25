package org.gtech.urlshortner.domain.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UrlMappingResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    String longUrl;
    Boolean success;
    String error;
}
