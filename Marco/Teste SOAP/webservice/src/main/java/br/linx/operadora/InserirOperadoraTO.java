package br.linx.operadora;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InserirOperadoraTO {

	@XmlElement(required=true, nillable=true)
	private OperadoraTO operadoraTO;

	public OperadoraTO getOperadoraTO() {
		return operadoraTO;
	}

	public void setOperadoraTO(OperadoraTO operadoraTO) {
		this.operadoraTO = operadoraTO;
	}
}
