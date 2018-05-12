package net.kimptoc.pinboard;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Hello world!
 */
public class PinboardApi {
    PinboardRequest pinboard;

    PinboardApi() {
        pinboard = new PinboardRequest();
    }

    public List<Tag> getTags(String user, String token) {

        return getStuff(pinboard.getTagsXml(user, token), "tag", XmlConverter::createTagFromXml);
    }

    private <T> List<T> getStuff(Document xml, String stuffElement, Function<Element, T> ctor) {
        List<T> stuff = new ArrayList<>();
        NodeList elements = xml.getElementsByTagName(stuffElement);
        for (int i = 0; i < elements.getLength(); i++) {
            Node node = elements.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                stuff.add(ctor.apply(element));
            } else {
                System.out.println("Other node:" + node.getNodeType() + "/" + node.getNodeName());
            }
        }
        return stuff;
    }

    public List<Post> getPosts(String user, String token, String tag) {
        return getStuff(pinboard.getPostsXml(user, token, tag), "post", XmlConverter::createPostFromXml);
    }
}
