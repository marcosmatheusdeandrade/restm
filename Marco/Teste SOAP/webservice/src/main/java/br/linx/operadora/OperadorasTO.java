package br.linx.operadora;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OperadorasTO {

	@XmlElement(nillable=false, required=false)
	private List<OperadoraTO> operadora = new ArrayList<>();

	public List<OperadoraTO> getOperadora() {
		return operadora;
	}

	public void setOperadora(List<OperadoraTO> operadoras) {
		this.operadora = operadoras;
	}
}
