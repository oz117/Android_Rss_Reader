package com.epitech.my_test_http.parser;

import java.io.Serializable;

/**
 * Simple classe qui contient toutes les informations utilses sur une feed Rss.
 * Created by zero on 28/04/15.
 */
public class                    RssItem implements Serializable {
    private String              _title;
    private String              _description;
    private String              _date;
    private String              _author;
    private String              _Url;
    private String              _img;
    private static final long   serialVersionUID = 1L;

    public String               getTitle() {
        return _title;
    }

    public void                 setTitle(String newTitle) {
        this._title = newTitle;
    }

    public String               getDescription() {
        return _description;
    }

    public void                 setDescription(String newDescription) {
        this._description = newDescription;
    }

    public String               getDate() {
        return _date;
    }

    public void                 setDate(String newDate) {
        this._date = newDate;
    }

    public                      String getAuthor() {
        return _author;
    }

    public void                 setAuthor(String newAuthor) {
        this._author = newAuthor;
    }

    public                      String getUrl() {
        return _Url;
    }

    public void                 setUrl(String newUrl) {
        this._Url = newUrl;
    }

    public                      String getImg() {
        return _img;
    }

    public void                 setImg(String newImg) {
        this._img = newImg;
    }
}
