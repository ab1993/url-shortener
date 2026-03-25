package org.gtech.urlshortner.repositories;

import org.gtech.urlshortner.domain.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlMappingRepository extends MongoRepository<UrlMapping,Long> {
    UrlMapping findUrlMappingById(String id);
}
