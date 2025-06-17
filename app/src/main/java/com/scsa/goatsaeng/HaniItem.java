package com.scsa.goatsaeng;

import java.util.Date;

public class HaniItem {
    long id;
    String title;
    String link;
    String description;
    Date pubDate;
    String subject;
    String category;

    @Override
    public String toString() {
        return "HaniItem{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", pubDate=" + pubDate +
                ", subject=" + subject +
                ", category=" + category +
                '}';
    }
}
