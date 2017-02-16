package br.experience.angular.server.webservice.model.operadora;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OperadorasTO {
	
	@XmlElement(required=true, nillable=true)
	private ArrayList<OperadoraTO> operadora = new ArrayList<>();

	public ArrayList<OperadoraTO> getOperadora() {
		return operadora;
	}

	public void setOperadora(ArrayList<OperadoraTO> operadora) {
		this.operadora = operadora;
	}

}
