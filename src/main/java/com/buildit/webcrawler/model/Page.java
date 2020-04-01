package com.buildit.webcrawler.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Page {

    private String uri;
    private Set<String> internalLinks;
    private Set<String> externalLinks;
    private Set<String> images;

    public Page(String url) {
        this.uri = url;
        this.internalLinks = new HashSet<>();
        this.externalLinks = new HashSet<>();
        this.images = new HashSet<>();
    }

    public String getUri() {
        return uri;
    }

    public List<String> getInternalLinks() {
        return new ArrayList<>(internalLinks);
    }

    public List<String> getExternalLinks() {
        return new ArrayList<>(externalLinks);
    }

    public List<String> getImages() {
        return new ArrayList<>(images);
    }

    public void addInternalLink(String link) {
        internalLinks.add(link);
    }

    public void addExternalLink(String link) {
        externalLinks.add(link);
    }

    public void addImage(String link) {
        images.add(link);
    }
}