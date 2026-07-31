package shortener.com.shorten_url.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import shortener.com.shorten_url.model.UrlModel;
import shortener.com.shorten_url.repository.UrlRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String createShortUrl(String originalUrl) {
        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "https://" + originalUrl;
        }

        String id;
        do {
            id = RandomStringUtils.secure().nextAlphanumeric(5, 11);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlModel(id, originalUrl, LocalDateTime.now().plusMinutes(1)));
        return id;
    }

    public Optional<UrlModel> findById(String id) {

        return urlRepository.findById(id);
    }

    @Cacheable(value = "getUrls")
    public List<UrlModel> getAllUrl(){
        return urlRepository.findAll();
    }
}