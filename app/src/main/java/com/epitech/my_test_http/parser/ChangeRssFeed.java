package com.epitech.my_test_http.parser;

import android.content.Context;
import android.content.Intent;

import com.epitech.my_test_http.ListActivity;
import com.epitech.my_test_http.util.LoadRSSFeed;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Demande l'adresse du flux Rss et lance le parseur
 *
 * Created by zero on 28/04/15.
 */
public class                ChangeRssFeed {
    // URL de test
    //public static String    _RssFeeDUrl = "www.androidpit.com/feed/main.xml";
    //public static String    _RssFeeDUrl = "http://rss.lemonde.fr/c/205/f/3050/index.rss";
    public static ArrayList<String> _RssFeedsUrl = new ArrayList<>(Arrays.asList("http://www.androidpit.com/feed/main.xml"));
    public static int              _feedCount = 1;

    // On change la source
    public static void      changeFeed(boolean refresh, Context context){
        for (int i = 0; i < _feedCount; ++i)
            new LoadRSSFeed(context, _RssFeedsUrl.get(i)).execute();
        context.startActivity(new Intent(context, ListActivity.class));
    }

    public static           String getFeedName(int position){
        return getDomainName(_RssFeedsUrl.get(position));
    }

    // On recupere le domaine à partir de l'url
    private static          String getDomainName(String url) {
        String domain = null;
        try {
            domain = new URI(url).getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
