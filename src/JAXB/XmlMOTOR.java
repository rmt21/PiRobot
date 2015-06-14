package JAXB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlMOTOR {
	
	String type;
	String direction;
	
	public String getType() {
		return type;
	}
	@XmlElement
	public void setType(String type) {
		this.type = type;
	}
	public String getDirection() {
		return direction;
	}
	@XmlElement
	public void setDirection(String direction) {
		this.direction = direction;
	}

}
