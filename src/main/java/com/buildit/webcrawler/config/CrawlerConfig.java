package com.buildit.webcrawler.config;

import com.buildit.webcrawler.dao.WebDao;
import com.buildit.webcrawler.dao.WebDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrawlerConfig {

    @Bean
    public WebDao getWebDao() {
        return new WebDaoImpl();
    }

}
