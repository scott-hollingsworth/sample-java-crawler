package com.buildit.webcrawler.controller;

import com.buildit.webcrawler.model.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Crawler {

    @GetMapping("/crawl")
    public Page crawl(@RequestParam(value = "url") String url) {
        return new Page();
    }

}
