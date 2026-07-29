package shortener.com.shorten_url.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shortener.com.shorten_url.controller.dto.ShortenUrlRequest;
import shortener.com.shorten_url.controller.dto.ShortenUrlResponse;
import shortener.com.shorten_url.model.UrlModel;
import shortener.com.shorten_url.repository.UrlRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class UrlController {

    @Autowired
    UrlRepository urlRepository;

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest){

        String id;
        do {
            id = RandomStringUtils.secure().nextAlphanumeric(5, 11);
        }while(urlRepository.existsById(id));
        urlRepository.save(new UrlModel(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);
        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id){
        var url = urlRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}