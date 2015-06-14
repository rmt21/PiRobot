package JAXB;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class XmlLED {

	
	
	String type;
	String colour;
	byte[] redResult;
	byte[] greenResult;
	int LEDLineCount;
	
	public String getType() {
		return type;
	}
	@XmlElement
	public void setType(String type) {
		this.type = type;
	}
	public String getColour() {
		return colour;
	}
	@XmlElement
	public void setColour(String colour) {
		this.colour = colour;
	}
	public byte[] getRedResult() {
		return redResult;
	}
	@XmlElement
	public void setRedResult(byte[] redResult) {
		this.redResult = redResult;
	}
	public byte[] getGreenResult() {
		return greenResult;
	}
	@XmlElement
	public void setGreenResult(byte[] greenResult) {
		this.greenResult = greenResult;
	}
	public int getLEDLineCount() {
		return LEDLineCount;
	}
	@XmlElement
	public void setLEDLineCount(int lEDLineCount) {
		LEDLineCount = lEDLineCount;
	}
	
	
	
}
