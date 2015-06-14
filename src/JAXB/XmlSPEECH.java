package JAXB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlSPEECH {

	String type;
	String speech;
	public String getType() {
		return type;
	}
	@XmlElement
	public void setType(String type) {
		this.type = type;
	}
	public String getSpeech() {
		return speech;
	}
	@XmlElement
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	
}
