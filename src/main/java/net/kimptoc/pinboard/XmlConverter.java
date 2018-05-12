package net.kimptoc.pinboard;

import org.w3c.dom.Element;

import java.util.function.Consumer;

public class XmlConverter {
    public static Tag createTagFromXml(Element element) {
        Tag tagItem = new Tag();
        set(element, tagItem::setTagName, "tag");
        set(element, tagItem::setCount, "count");
        return tagItem;
    }

    private static void set(Element element, Consumer<String> mCall, String attribute) {
        String attributeValue = element.getAttribute(attribute);
        mCall.accept(attributeValue);
    }

    /**
     * <post href="http://www.weather.com/" description="weather.com"
     * hash="6cfedbe75f413c56b6ce79e6fa102aba" tag="weather reference"
     * time="2005-11-29T20:30:47Z" />
     *
     * @param element
     * @return
     */
    public static Post createPostFromXml(Element element) {
        Post p = new Post();
        set(element, p::setHref, "href");
        set(element, p::setDescription, "description");
        set(element, p::setHash, "hash");
        set(element, p::setTag, "tag");
        set(element, p::setTime, "time");
        return p;
    }
}
