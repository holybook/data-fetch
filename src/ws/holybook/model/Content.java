package ws.holybook.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Content {

    private List<Section> text;

    public Content() {
        this.text = new ArrayList<>();
    }

    @XmlElement(name = "section")
    public List<Section> getText() {
        return text;
    }

}
