package com.buildit.webcrawler.controller;

import com.buildit.webcrawler.dao.WebDao;
import com.buildit.webcrawler.model.Page;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

@RestController
public class Crawler {

    Logger logger = LoggerFactory.getLogger(Crawler.class);

    @Autowired
    private WebDao webDao;

    @GetMapping("/crawl")
    public Page crawl(@RequestParam(value = "url") String url) throws URISyntaxException {
        String host = new URI(url).getHost();
        Set<String> visited = new HashSet<>();
        Queue<String> toVisit = new LinkedList<>();
        Page page = new Page(url);

        toVisit.add(url);
        while (!toVisit.isEmpty()) {
            String currentLink = formatLink(toVisit.remove());
            visited.add(currentLink);
            Document document = webDao.get(currentLink);

            if (document != null) {
                for (Element pageLink : document.select("a[href]")) {
                    String href = formatLink(pageLink.attr("abs:href"));

                    if (!visited.contains(href)) {
                        if (host.equals(new URI(href).getHost())) {
                            page.addInternalLink(href);
                            toVisit.add(href);
                        } else {
                            page.addExternalLink(href);
                            visited.add(href);
                        }
                    }

                    for (Element image : document.select("img")) {
                        List<String> pageImages = page.getImages();
                        String src = image.attr("src");

                        if (!pageImages.contains(src)) {
                            page.addImage(src);
                        }
                    }
                }
            }
        }

        logger.info("Visited: {}", visited);

        return page;
    }

    private String formatLink(String linkToFormat) throws URISyntaxException {
        URI link = new URI(linkToFormat.replace(" ", "%20"));

        return link.getScheme() + "://" + link.getHost() + link.getPath();
    }

}
