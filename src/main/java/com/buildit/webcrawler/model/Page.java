package com.buildit.webcrawler.model;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private String uri;
    private List<String> internalLinks;
    private List<String> externalLinks;
    private List<String> images;

    public Page() {
        this.uri = "";
        this.internalLinks = new ArrayList<>();
        this.externalLinks = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getInternalLinks() {
        return internalLinks;
    }

    public void setInternalLinks(List<String> internalLinks) {
        this.internalLinks = internalLinks;
    }

    public List<String> getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(List<String> externalLinks) {
        this.externalLinks = externalLinks;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
