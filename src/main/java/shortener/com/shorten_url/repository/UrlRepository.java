package shortener.com.shorten_url.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shortener.com.shorten_url.model.UrlModel;

@Repository
public interface UrlRepository extends MongoRepository<UrlModel, String> {
}
