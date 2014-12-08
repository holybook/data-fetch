package ws.holybook.model;

import javax.xml.bind.annotation.XmlElement;

public class Meta {

    private Source source;
    private String title;
    private String language;
    private Author author;
    private Religion religion;
    private int pages;

    @XmlElement(name = "source")
    public Source getSource() {
        return source;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    @XmlElement(name = "language")
    public String getLanguage() {
        return language;
    }

    @XmlElement(name = "author")
    public Author getAuthor() {
        return author;
    }

    @XmlElement(name = "religion")
    public Religion getReligion() {
        return religion;
    }

    @XmlElement(name = "pages")
    public int getPages() {
        return pages;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
