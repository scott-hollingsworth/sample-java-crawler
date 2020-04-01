package com.buildit.webcrawler.controller;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.buildit.webcrawler.dao.WebDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CrawlerTest {

    @MockBean
    private WebDao webDao;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnsCorrectJson() throws Exception {
        String expected = loadString("expected.json");
        String url = "http://www.test-site.com";
        Document indexResponse = loadDocument("index.html");
        indexResponse.setBaseUri(url);
        when(webDao.get(eq(url))).thenReturn(indexResponse);

        Logger logger = (Logger) LoggerFactory.getLogger(Crawler.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        String expectedLogs = loadString("expectedLogs.txt");

        String contactUrl = url + "/contact.html";
        Document contactResponse = loadDocument("contact.html");
        contactResponse.setBaseUri(contactUrl);
        when(webDao.get(eq(contactUrl))).thenReturn(contactResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/crawl")
                .param("url", url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expected));

        List<ILoggingEvent> logs = listAppender.list;
        assertEquals(logs.toString(), expectedLogs);
    }

    private Document loadDocument(String filename) throws IOException {
        return Jsoup.parse(loadString(filename));
    }

    private String loadString(String filename) throws IOException {
        return new String(
                Files.readAllBytes(
                        ResourceUtils.getFile("classpath:" + filename).toPath()
                )
        );
    }

}
