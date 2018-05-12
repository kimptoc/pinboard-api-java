package net.kimptoc.pinboard;

/**
 * <post href="http://www.weather.com/" description="weather.com"
 * hash="6cfedbe75f413c56b6ce79e6fa102aba" tag="weather reference"
 * time="2005-11-29T20:30:47Z" />
 */
public class Post {
    private String href;
    private String description;
    private String hash;
    private String tag;
    private String time;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
