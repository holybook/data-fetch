package ws.holybook.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class to represent an author in the database.
 * 
 * @author Hannes Widmoser
 */
public class Religion {

	private String id;
	private String name;

	public Religion(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

}
