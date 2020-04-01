package com.buildit.webcrawler.model;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private String uri;
    private List<String> internalLinks;
    private List<String> externalLinks;
    private List<String> images;

    public Page(String url) {
        this.uri = url;
        this.internalLinks = new ArrayList<>();
        this.externalLinks = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public String getUri() {
        return uri;
    }

    public List<String> getInternalLinks() {
        return internalLinks;
    }

    public List<String> getExternalLinks() {
        return externalLinks;
    }

    public List<String> getImages() {
        return images;
    }
}
