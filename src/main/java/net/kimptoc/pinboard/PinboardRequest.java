package net.kimptoc.pinboard;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

public class PinboardRequest {

    private final String pinboardRootUrl;

    public PinboardRequest() {
        pinboardRootUrl = System.getProperty("pinboard.root.url", "https://api.pinboard.in/");
    }

    public PinboardRequest(String url) {
        pinboardRootUrl = url;
    }

    public Document getTagsXml(String user, String token) {
        return getXml(user, token, "v1/tags/get", null);
    }

    public Document getPostsXml(String user, String token, String tag) {
        String queryParams = null;
        if (tag != null && tag.length() > 0) queryParams = "tag=" + tag;
        return getXml(user, token, "v1/posts/all", queryParams);
    }

    public Document getXml(String user, String token, String apiMethod, String queryParams) {
        String content;
        Document document;
        try {
            content = queryPinboard(user, token, apiMethod, queryParams);

            document = convertStringToDocument(content);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return document;
    }


    public String queryPinboard(String user, String token, String apiMethod, String queryParams) throws IOException {
        InputStream is = queryPinboardForInputStream(user, token, apiMethod, queryParams);
        String content;
        content = convertStreamToString(is);
        return content;
    }

    public InputStream queryPinboardForInputStream(String user, String token, String apiMethod, String queryParams) throws IOException {
        String url = pinboardRootUrl + apiMethod + "?auth_token=" + user + ":" + token;
        if (queryParams != null) url += "&" + queryParams;
        URL myURL = new URL(url);
        URLConnection myURLConnection = myURL.openConnection();

        myURLConnection.connect();
        return myURLConnection.getInputStream();
    }

    String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
