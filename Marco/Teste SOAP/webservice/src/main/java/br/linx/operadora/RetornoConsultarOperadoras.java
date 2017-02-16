package br.linx.operadora;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.linx.enums.ETipoRetorno;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetornoConsultarOperadoras {
	
	@XmlElement(required=true, nillable=false)
	private ETipoRetorno tipoRetorno;
	
	@XmlElement(required=true, nillable=false)
	private OperadorasTO operadorasTO;
	
	public ETipoRetorno getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(ETipoRetorno tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public OperadorasTO getOperadorasTO() {
		return operadorasTO;
	}

	public void setOperadorasTO(OperadorasTO operadorasTO) {
		this.operadorasTO = operadorasTO;
	}
}
