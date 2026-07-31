package shortener.com.shorten_url.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import shortener.com.shorten_url.model.UrlModel;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<UrlModel, String> {
    Optional<UrlModel> findById(String id);
}
