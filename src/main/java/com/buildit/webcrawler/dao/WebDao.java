package com.buildit.webcrawler.dao;

import org.jsoup.nodes.Document;

public interface WebDao {

    Document get(String url);

}
