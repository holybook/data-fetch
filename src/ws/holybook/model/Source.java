package ws.holybook.model;

import javax.xml.bind.annotation.XmlElement;

public class Source {

    private String name;

    public Source(String name) {
        this.name = name;
    }

    @XmlElement
    public String getName() {
        return name;
    }

}
