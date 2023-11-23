package com.rdjaramillo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @GetMapping("/locale/{loc}")
    public ResponseEntity<Void>changeLocale(@PathVariable ("loc") String loc){
        Locale res = switch (loc){
            case "en" -> Locale.ENGLISH;
            case "fr" -> Locale.FRANCE;
            default -> Locale.ROOT;
        };
        localeResolver.setLocale(request, response, res);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

}
