package shortener.com.shorten_url.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shortener.com.shorten_url.controller.dto.ShortenUrlRequest;
import shortener.com.shorten_url.controller.dto.ShortenUrlResponse;
import shortener.com.shorten_url.model.UrlModel;
import shortener.com.shorten_url.service.UrlService;

import java.net.URI;
import java.util.List;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping(value = "/")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest) {

        String id = urlService.createShortUrl(request.url());

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);
        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        var url = urlService.findById(id);
        if (url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<UrlModel>> get(){
        return ResponseEntity.ok(urlService.getAllUrl());
    }
}