package com.epitech.my_test_http.parser;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Parseur de XML c'est ici que la magie se produit.
 * Created by zero on 28/04/15.
 */
public class        DomParser  {
    private RssFeed _feed = new RssFeed();

    public RssFeed  parseXml(String rssUrl) {
        URL         url = null;

        // On construit l'url et on verifie qu'elle est valide.
        try {
            url = new URL(rssUrl);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            Log.v("Url exception", e.getMessage());
        }
        try {
            // Cette classe sert à obtenir des documents à partir d'un xml
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Ici on parse le xml
            Document doc = builder.parse(new InputSource(url.openStream()));
            // Normalisation du doc
            doc.getDocumentElement().normalize();
            NodeList listChannel = doc.getElementsByTagName("channel");
            for (int i = 0; i < listChannel.getLength(); ++i) {
                Node channelTitle = listChannel.item(i);
                NodeList nodeChild = channelTitle.getChildNodes();
                for (int j = 0; j < nodeChild.getLength(); ++j) {
                    String nodeName = nodeChild.item(j).getNodeName();
                    if (nodeName.equals("title"))
                        Log.v("nodeName:", nodeName);
                }
            }
            // On récupère tous les éléments avec le tag item
            NodeList listItems = doc.getElementsByTagName("item");
            for (int i = 0; i < listItems.getLength(); ++i) {
                // On se positionne sur le node courant
                Node currentNode = listItems.item(i);
                RssItem item = new RssItem();
                // On recupere les "enfants" du node courant
                NodeList nodeChild = currentNode.getChildNodes();
                for (int j = 0; j < nodeChild.getLength(); ++j) {
                    // On recupere le nom de l'"enfant"
                    String nodeName = nodeChild.item(j).getNodeName();
                    String nodeString = null;

                    // On verifie l'existance d'au moins un "enfant"
                    if (nodeChild.item(j).getFirstChild() != null) {
                        // On recupere la valeur du node
                        nodeString = nodeChild.item(j).getFirstChild().getNodeValue();
                    }
                    // On recupere les bonnes valeurs et on les assignes.
                    if (nodeString != null) {
                        if ("title".equals(nodeString)) {
                            item.setTitle(nodeString);
                        }
                        else if ("content:encoded".equals(nodeName)) {
                            item.setDescription(nodeString);
                        }
                        else if ("pubDate".equals(nodeName)) {
                            item.setDate(nodeString.replace(" +0000", ""));
                        }
                        else if ("author".equals(nodeName) || "dc:creator".equals(nodeName)) {
                            item.setAuthor(nodeString);
                        }
                        else if ("link".equals(nodeName)){
                            item.setUrl(nodeString);
                        }
                        else if ("thumbnail".equals(nodeName)){
                            item.setImg(nodeString);
                        }
                    }
                }
                _feed.addItem(item);
            }
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return _feed;
    }
}
