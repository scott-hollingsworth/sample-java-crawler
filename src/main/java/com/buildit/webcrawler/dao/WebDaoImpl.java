package com.buildit.webcrawler.dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebDaoImpl implements WebDao {

    @Override
    public Document get(String url) {
        Document response;

        try {
            response = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            response = new Document(url);
        }

        return response;
    }

}
