package com.epitech.my_test_http.parser;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * DOMParser cre une instance de cette classe
 * elle contient des RssItem, on peut en rajouter d'autres au besoin
 *
 * Created by zero on 28/04/15.
 */
public class                    RssFeed implements Serializable {
    private List<RssItem>       _rssList;
    private int                 _itemCount;
    private static final long   serialVersionUID = 1L;

    RssFeed() {
        _rssList = new Vector<RssItem>(0);
    }

    public void                 addItem(RssItem newItem) {
        _rssList.add(newItem);
        _itemCount++;
    }

    public RssItem              getItem(int position) {
        return _rssList.get(position);
    }

    public int                  getItemCount() {
        return _itemCount;
    }
}
